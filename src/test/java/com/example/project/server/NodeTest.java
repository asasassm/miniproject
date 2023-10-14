package com.example.project.server;

import com.example.project.wire.BufferedWire;
import com.example.project.wire.Wire;

public class NodeTest {
    // 인풋아웃풋 노드가 있고 와이어번호에따라 connect해주면 서로 연결
    public static void main(String[] args) {
        ServerNode serverNode = new ServerNode("서버노드", 8888);
        AcceptManagerNode acceptManagerNode = new AcceptManagerNode();
        SocketReader socketReader = new SocketReader(1, 1);
        SocketWriter socketWriter = new SocketWriter(1);

        Wire wire1 = new BufferedWire();
        Wire wire2 = new BufferedWire();
        Wire wire3 = new BufferedWire();

        acceptManagerNode.addBlackList("0:0:0:0:0:0:0:1"); // 블랙리스트 추가

        serverNode.connectOutputWire(0, wire1);
        // 서버노드 -> 접속관리노드
        acceptManagerNode.connectInputWire(0, wire1);
        acceptManagerNode.connectOutputWire(0, wire2);
        // 접속관리노드 -> 메세지읽는노드
        socketReader.connectInputWire(0, wire2);
        socketReader.connectOutputWire(0, wire3);
        // 메세지읽는노드 -> 메세지전송노드
        socketWriter.connectInputWire(0, wire3);
        // 메세지전송노드는 클라이언트로 메세지를 전송

        serverNode.start();
        acceptManagerNode.start();
        socketReader.start();
        socketWriter.start();
    }
}
