package com.example.project.node;

import java.util.UUID;
import com.example.project.exception.AlreadyStartedException;

public abstract class ActiveNode extends Node implements Runnable {
    private static final long DEFAULT_INTERVAL = 1000;
    Thread thread;

    long interval = DEFAULT_INTERVAL;

    ActiveNode() {
        super();
    }

    ActiveNode(String name) {
        super(name);
    }

    ActiveNode(String name, UUID id) {
        super(name, id);
    }

    public long getInterval() {
        return interval;
    }

    public void setInterval(int interval) {
        this.interval = interval;
    }

    void preprocess() {
    }

    void process() {
    }

    void postprocess() { // 마무리에서 스레드 제거
        thread = null;
    }

    @Override
    public void run() {
        preprocess();

        long startTime = System.currentTimeMillis();
        long previousTime = startTime;

        while (isAlive()) {
            long currentTime = System.currentTimeMillis();
            long elapsedTime = currentTime - previousTime;
            if (elapsedTime < interval) {
                try {
                    process();
                    Thread.sleep(interval - elapsedTime);
                } catch (InterruptedException e) {
                    stop();
                }
            }
            previousTime = startTime + (System.currentTimeMillis() - startTime) / interval * interval;
        }
        postprocess();
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
