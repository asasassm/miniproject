package com.example.project.server;

import java.io.IOException;
import java.net.Socket;
import com.example.project.message.Message;
import com.example.project.message.SocketMessage;
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



    @Override
    protected void process() {

        if (isConnected()) {


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
