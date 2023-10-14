package com.example.project.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import com.example.project.message.SocketMessage;
import com.example.project.node.InputNode;
import com.example.project.wire.Wire;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ServerNode extends InputNode { // InputNode가 됨
    private ServerSocket serverSocket;
    private int port;

    public ServerNode(String name, int port) {
        super(name, 1);
        this.port = port;
    }

    @Override
    protected void preprocess() {
        try {
            serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            //
        }
    }

    @Override
    protected void process() {
        try {
            Socket socket = serverSocket.accept(); // 소켓이 연결될 때 까지 무한 대기
            // 이 소켓을 wire에 전달할 수 있게 만들어야 한다.
            output(new SocketMessage(socket));
            log.info("서버 연결 성공");
        } catch (IOException e) {
        }
    }

    @Override
    protected void postprocess() {
        try {
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
