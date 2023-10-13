package com.example.project.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

import com.example.project.message.Message;
import com.example.project.message.SocketMessage;
import com.example.project.message.WrappingMessage;
import com.example.project.node.InputOutputNode;

// 소켓을 받아서 메세지 읽고 보내주기만
public class SocketIn extends InputOutputNode {
    BufferedReader reader;
    Socket socket;
    String message;

    // 내가 올릴때 -> 로컬develop에가서 내 로컬 feature를 머지 한다. - > 그 로컬 develop을 푸쉬
    // 받을때 로컬 develop으로가서 풀 땡기고 내 로컬 feature로 가서 로컬 develop을 머지
    // git홈피에서 바로 pull request
    protected SocketIn(int inCount, int outCount) {
        super(inCount, outCount);
    }

    @Override
    protected void postprocess() {
        Message inSocketMessage = getInputWire(0).get();
        if (inSocketMessage instanceof SocketMessage) {
            SocketMessage socketMessage = (SocketMessage) inSocketMessage;
            socket = socketMessage.getSocket();
        }
    }

    @Override
    protected void preprocess() {
        try {
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            StringBuilder messageBuilder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.isBlank()) {
                    break;
                }
                messageBuilder.append(line); // 읽은 줄을 문자열 빌더에 추가
                messageBuilder.append("\n");
            }
            message = messageBuilder.toString();
            output(new WrappingMessage(socket, message));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
