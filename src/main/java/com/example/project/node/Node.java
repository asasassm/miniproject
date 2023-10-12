package com.example.project.node;

import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import com.github.f4b6a3.uuid.UuidCreator;

@Slf4j
public abstract class Node {
    private static int count;
    UUID id;
    String name;

    Node(String name, UUID id) {
        count++;
        this.id = id;
        this.name = name;
        log.trace("create node : {} ", id);
    }

    Node() {
        this(UuidCreator.getTimeBased());
    }

    Node(UUID id) {
        this(id.toString(), id);
    }

    Node(String name) {
        this(name, UuidCreator.getTimeBased());
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        log.trace("node name : {}", name);
    }

    public static int getCount() {
        return count;
    }
}
