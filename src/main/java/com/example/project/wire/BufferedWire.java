package com.example.project.wire;

import java.util.LinkedList;
import java.util.Queue;
import com.example.project.message.Message;

public class BufferedWire implements Wire {
    Queue<Message> messageQueue;

    public BufferedWire() {
        super();
        messageQueue = new LinkedList<>();
    }

    @Override
    public void put(Message message) {
        messageQueue.add(message);
    }

    @Override
    public boolean hasMessage() {
        return !messageQueue.isEmpty();
    }

    @Override
    public Message get() {
        return messageQueue.poll();
    }

}
