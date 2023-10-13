package com.example.project.message;

import java.net.Socket;

public class WrappingMessage extends Message{
    Socket socket;
    String message;

    public WrappingMessage(Socket socket, String message){
        this.socket = socket;
        this.message = message;
    }

    public Socket getSocket() {
        return socket;
    }

    public String getMessage() {
        return message;
    }
}
