package com.example.project.node;

import java.util.UUID;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONObject;

import com.github.f4b6a3.uuid.UuidCreator;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class Node {
    static int count;
    UUID id;
    String name;
    
    Node(String name, UUID id) {
        count++;
        this.id = id;
        this.name = name;
        log.trace("create node : {} ", id);
    }

    Node(UUID id) {
        this(id.toString(), id);
    }

    Node(String name) {
        this(name, UuidCreator.getTimeBased());
    }

    Node() {
        this(UuidCreator.getTimeBased());
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static int getCount() {
        return count;
    }
}
