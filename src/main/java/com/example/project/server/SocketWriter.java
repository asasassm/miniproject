package com.example.project.server;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;

import com.example.project.message.Message;
import com.example.project.message.WrappingMessage;
import com.example.project.node.OutputNode;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SocketWriter extends OutputNode {
    private Socket socket;
    private String message;

    protected SocketWriter(int count) {
        super(count);
    }

    @Override
    protected void process() {
        Message inSocketMessage = getInpuWire(0).get();
        if (inSocketMessage instanceof WrappingMessage) {
            WrappingMessage wrappingMessage = (WrappingMessage) inSocketMessage;
            socket = wrappingMessage.getSocket();
            message = wrappingMessage.getMessage();
            if (socket != null && message != null) {
                try {
                    BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                    writer.write(message);
                    writer.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
