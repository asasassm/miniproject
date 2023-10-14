package com.example.project.node;

import java.util.UUID;
import com.example.project.exception.AlreadyStartedException;

public abstract class ActiveNode extends Node implements Runnable {
    private Thread thread;
    private long interval = 1000;

    protected ActiveNode() {
        super();
    }

    protected ActiveNode(String name) {
        super(name);
    }

    protected ActiveNode(String name, UUID id) {
        super(name, id);
    }

    protected void preprocess() {}

    protected void process() {}

    protected void postprocess() {
        thread = null;
    } // 마무리에서 스레드 제거

    @Override
    public void run() {
        preprocess();

        while (isAlive()) {
            try {
                process();
                Thread.sleep(interval);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        postprocess();
    }

    public long getInterval() {
        return interval;
    }

    public void setInterval(int interval) {
        this.interval = interval;
    }

    public void stop() {
        if (thread != null) {
            thread.interrupt();
        }
    }

    public synchronized void start() {
        if (thread != null) { // 시작할때 들어있으면 안됨
            throw new AlreadyStartedException();
        }
        thread = new Thread(this, getName());
        thread.start();
    }

    public boolean isAlive() {
        return (thread != null) && thread.isAlive();
    }
}
