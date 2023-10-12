package com.example.project.server;

import java.io.BufferedInputStream;
import java.net.Socket;
import com.example.project.wire.SocketWire;
import com.example.project.wire.Wire;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AcceptManagerNode extends Thread {
    SocketWire inputWire; // 받아오고
    SocketWire outputWire; // 내가 주는 거
    boolean run = false;
    int count=0;
    public AcceptManagerNode() {
        this.outputWire = new SocketWire(); // 미리 만들어놔야함
    }
    public boolean isConnected() { //와이엉 연결확인 이다, 소켓 연결이 아니다
       return inputWire !=null;
    }

    public void connect(SocketWire inputWire) {
        // 밖에 있는 wire를 받아와서 나한테 연결한다.
        this.inputWire = inputWire;

    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            
            while(isConnected()) {
                      try {
                        Thread.sleep(100); //TODO  쓰레드가 자원을 다 잡고 있어서 그럴수 있다
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                if (inputWire.hasMessage()) {
                    log.info("ACcept 성공");
                    Socket socket = inputWire.get();
    
                    socket.getInetAddress().getHostAddress(); // 이렇게 확인해서 블랙리스트 안에 있으면 socket.close()
    
                    outputWire.put(socket); // 소켓 검사 후 output으로 넘겨줌
                }
            }
        }
    }

}
