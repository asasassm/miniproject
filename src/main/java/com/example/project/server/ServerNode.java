package com.example.project.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import com.example.project.node.ActiveNode;
import com.example.project.wire.SocketWire;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ServerNode extends ActiveNode { // InputNode가 됨

    int port;
    ServerSocket serverSocket;
    SocketWire outWire;

    public ServerNode(int port) {
        super();
        this.port = port;
        this.outWire = new SocketWire();
    }

    public SocketWire getInputWire() {
        return this.outWire;
    }

    @Override
    protected void preprocess() {
        try {
            serverSocket = new ServerSocket(port);
        } catch (IOException e) {

        }
    }

    @Override
    public void process() {
        try {
            Socket socket = serverSocket.accept(); // 소켓이 연결될 때 까지 무한 대기
            // 이 소켓을 wire에 전달할 수 있게 만들어야 한다.
            outWire.put(socket);

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

    public static void main(String[] args) {
        ServerNode serverNode = new ServerNode(8888);
        AcceptManagerNode acceptManagerNode = new AcceptManagerNode();

        serverNode.start();
        acceptManagerNode.start();

        acceptManagerNode.connect(serverNode.getInputWire());
    }
}
