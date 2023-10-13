package com.example.project.server;

import java.io.BufferedReader;
import java.net.Socket;

import com.example.project.message.Message;
import com.example.project.message.SocketMessage;
import com.example.project.node.InputOutputNode;

// 소켓을 받아서 메세지 읽고 보내주기만
public class SocketIn extends InputOutputNode {
    Socket socket;
    BufferedReader bufferedReader;

    // 내가 올릴때 -> 로컬develop에가서 내 로컬 feature를 머지 한다. - > 그 로컬 develop을 푸쉬
    // 받을때 로컬 develop으로가서 풀 땡기고 내 로컬 feature로 가서 로컬 develop을 머지
    // git홈피에서 바로 pull request
    protected SocketIn(int inCount, int outCount) {
        super(inCount, outCount);

    }

    @Override
    protected void postprocess() {
        Message message = getInputWire(0).get();
        if (message instanceof SocketMessage) {
            SocketMessage socketMessage = (SocketMessage) message;
            socket = socketMessage.getSocket();
        }
    }

    @Override
    protected void preprocess() {
        
    }

    @Override
    protected void process() {

    }

}
