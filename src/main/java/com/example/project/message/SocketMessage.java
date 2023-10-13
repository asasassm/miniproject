package com.example.project.message;

import java.net.Socket;

public class SocketMessage extends Message {
    Socket socket;

    public SocketMessage(Socket socket) {
        this.socket = socket;
    }

    public Socket getSocket() {
        return socket;
    }

}
