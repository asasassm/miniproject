package com.example.project.server;

import java.io.IOException;
import java.net.Socket;
import com.example.project.message.Message;
import com.example.project.message.SocketMessage;
import com.example.project.node.InputOutputNode;
import com.example.project.wire.Wire;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AcceptManagerNode extends InputOutputNode {
    BlackList blackList;

    public AcceptManagerNode() {
        super(1, 1);
        blackList = new BlackList();
    }

    public boolean isConnected() { // 와이엉 연결확인 이다, 소켓 연결이 아니다
        return ((getInputWire(0) != null) && getInputWire(0).hasMessage());
    }

    public void addBlackList(String ip) {
        blackList.addBlcakList(ip);
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

    public BlackList getBlackList() {
        return blackList;
    }
}
