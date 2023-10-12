package com.example.project.message;

public class StringMessage extends Message {
    String payLoad;

    public StringMessage(String payload) {
        this.payLoad = payLoad;
    }

    public String getPayload() {
        return payLoad;
    }
}
