package com.example.project.server;

import java.io.BufferedInputStream;
import java.net.Socket;
import com.example.project.node.ActiveNode;
import com.example.project.wire.SocketWire;
import com.example.project.wire.Wire;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AcceptManagerNode extends ActiveNode {
    SocketWire inputWire; // 받아오고
    SocketWire outputWire; // 내가 주는 거
    boolean run = false;
    int count = 0;

    public AcceptManagerNode() {
        this.outputWire = new SocketWire(); // 미리 만들어놔야함
    }

    public boolean isConnected() { // 와이엉 연결확인 이다, 소켓 연결이 아니다
        return inputWire != null;
    }

    public void connect(SocketWire inputWire) {
        // server에서 보낸 output을 wire로 받아와서 input 연결한다.
        this.inputWire = inputWire;

    }

    @Override
    public void run() {



        while (!Thread.currentThread().isInterrupted()) {

            while (isConnected()) { // 와이어 연결
                // try {
                // Thread.sleep(100); //TODO 쓰레드가 자원을 다 잡고 있어서 그럴수 있다
                // } catch (InterruptedException e) {
                // e.printStackTrace();
                // }
                if (inputWire.hasMessage()) {
                    log.info("ACcept 성공");
                    Socket socket = inputWire.get(); // 소켓 연결

                    socket.getInetAddress().getHostAddress(); // 이렇게 확인해서 블랙리스트 안에 있으면
                                                              // socket.close()

                    outputWire.put(socket); // 소켓 검사 후 output으로 넘겨줌
                }
            }
        }
    }

    @Override
    protected void preprocess() {
        if (isConnected()) {

        }

    }

    @Override
    protected void process() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'process'");
    }

    @Override
    protected void postprocess() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'postprocess'");
    }

}
