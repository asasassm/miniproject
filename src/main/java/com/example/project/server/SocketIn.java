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
