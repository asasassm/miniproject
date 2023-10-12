package com.example.project.message;

public abstract class Message {
    static int count;
    private long createTime;
    private String id;

    Message() {
        count++;
        id = String.format("%s-%04d", getClass().getSimpleName(), count);
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
