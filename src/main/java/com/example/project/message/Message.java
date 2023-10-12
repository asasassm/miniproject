package com.example.project.message;

public abstract class Message {
    static int count;
    private long createTime;
    private final String id;

    Message() {
        count++;
        id = getClass().getSimpleName() + count;
        createTime = System.currentTimeMillis();
    }

    public String getId() {
        return id;
    }

    public long getCreateTime() {
        return createTime;
    }

    public int getCount() {
        return count;
    }

}
