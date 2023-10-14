package com.example.project.server;

import static org.junit.Assert.assertEquals;
import org.junit.jupiter.api.Test;

public class ParsingNodeTest {

    @Test
    void testSplitMessage() {
        ParsingNode parsingNode = new ParsingNode(0, 0);
        parsingNode.splitMessage("hi hello \n happy");

        assertEquals("hi hello ", parsingNode.getHeadMessage());
        assertEquals(" happy", parsingNode.getFieldMessage());
    }
}
