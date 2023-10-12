package com.example.project.wire;

import com.example.project.message.Message;

public interface Wire<T> {
    public void put(T message);

    public boolean hasMessage();

    public T get();
}
