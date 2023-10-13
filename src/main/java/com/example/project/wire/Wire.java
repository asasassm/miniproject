package com.example.project.wire;

import com.example.project.message.Message;

public interface Wire {
    public void put(Message message);

    public boolean hasMessage();

    public Message get();

    // git test
}
