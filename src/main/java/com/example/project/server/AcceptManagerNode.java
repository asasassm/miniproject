package com.example.project.server;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.Socket;
import com.example.project.message.Message;
import com.example.project.message.SocketMessage;
import com.example.project.node.ActiveNode;
import com.example.project.node.InputOutputNode;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AcceptManagerNode extends InputOutputNode {
    // SocketWire inputWire; // 받아오고
    // SocketWire outputWire; // 내가 주는 거
    boolean wireCheck = false;

    BlackList blackList;

    public AcceptManagerNode() {
        super(1, 1);
        // this.outputWire = new SocketWire(); // 미리 만들어놔야함
        blackList = new BlackList();

        // setInterval(1000);
    }

    public boolean isConnected() { // 와이엉 연결확인 이다, 소켓 연결이 아니다
        return ((getInputWire(0) != null) && getInputWire(0).hasMessage());
    }

    // public void connect(SocketWire inputWire) {
    // // server에서 보낸 output을 wire로 받아와서 input 연결한다.
    // this.inputWire = inputWire;

    // }

    // @Override
    // public void run() {



    // while (!Thread.currentThread().isInterrupted()) {
    // preprocess();
    // while (wireCheck) { // 와이어 연결
    // // try {
    // // Thread.sleep(1000); // TODO 쓰레드가 자원을 다 잡고 있어서 그럴수 있다
    // // } catch (InterruptedException e) {
    // // e.printStackTrace();
    // // }
    // // if (inputWire.hasMessage()) {
    // // log.info("ACcept 성공");
    // // socket = inputWire.get(); // 소켓 연결
    // process();
    // // log.info(socket.getInetAddress().getHostAddress()); // 이렇게 확인해서 블랙리스트 안에 있으면

    // // outputWire.put(socket); // 소켓 검사 후 output으로 넘겨줌
    // // try {
    // // socket.close();
    // // } catch (IOException e) {
    // // e.printStackTrace();
    // // }
    // // }
    // }

    // }
    // }

    @Override
    protected void preprocess() {
        // if (isConnected()) {
        // // try {
        // // Thread.sleep(100); // TODO 쓰레드가 자원을 다 잡고 있어서 그럴수 있다
        // // } catch (InterruptedException e) {
        // // e.printStackTrace();
        // // }

        // log.info("ACcept 성공");
        // Message message = getInputWire(0).get(); // 소켓 연결
        // if (message instanceof SocketMessage) { // 메세지가 소켓메세지 객체면
        // SocketMessage message2 = (SocketMessage) message; // 이걸 해라
        // Socket socket = message2.getSocket();
        // }


        // wireCheck = true;

        // } else {
        // wireCheck = false;
        // }
    }

    @Override
    protected void process() {

        if (isConnected()) {
            // try {
            // Thread.sleep(100); // TODO 쓰레드가 자원을 다 잡고 있어서 그럴수 있다
            // } catch (InterruptedException e) {
            // e.printStackTrace();
            // }

            log.info("ACcept 성공");
            Message message = getInputWire(0).get(); // 소켓 연결
            if (message instanceof SocketMessage) { // 메세지가 소켓메세지 객체면
                SocketMessage message2 = (SocketMessage) message; // 이걸 해라
                Socket socket = message2.getSocket();


                log.debug("Accept Sokcet Info : {}", socket.toString());
                if (!blackList.contain(socket.getInetAddress().getHostAddress())) {
                    output(new SocketMessage(socket)); // 소켓 검사 후 output으로 넘겨줌
                    log.info(socket.getInetAddress().getHostAddress() + " 입장");
                } else {
                    try {
                        log.info(socket.getInetAddress().getHostAddress() + "는 입장불가");
                        socket.close();
                    } catch (IOException e) {
                        log.info("Accept procces " + e);
                    }
                }
            }
        }

    }

    @Override
    protected void postprocess() {

    }


    public BlackList getBlackList() {
        return blackList;
    }

}
