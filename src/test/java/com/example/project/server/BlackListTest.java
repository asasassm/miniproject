package com.example.project.server;

import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

public class BlackListTest {
    @Test
    void testAddBlcakList() {
        BlackList blackList = new BlackList();
        if (blackList.getList().size() == 0) {
            blackList.addBlcakList("127.0.0.1");
            assertTrue(blackList.contain("127.0.0.1"));
        }
    }


    @Test
    void testContain() {


    }

    @Test
    void testRemove() {
        BlackList blackList = new BlackList();

    }



}
