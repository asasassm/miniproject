package com.example.project.wire;

import java.util.LinkedList;
import java.util.Queue;
import com.example.project.message.Message;

/**
 * Socket Wire로 바꿨음
 */
public class BufferedWire implements Wire<Message> {
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
