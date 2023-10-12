package com.example.project.node;

import java.util.Scanner;
import com.example.project.message.Message;
import com.example.project.message.StringMessage;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TerminalInNode extends InputOutNode {
    Scanner scanner;

    protected TerminalInNode() {
        this(1, 1);
    }

    protected TerminalInNode(int inCount, int outCount) {
        super(inCount, outCount);
    }

    @Override
    void preprocess() {
        scanner = new Scanner(System.in);
        setInterval(1);
    }

    @Override
    void process() {
        String line = scanner.nextLine();
        StringMessage message = new StringMessage(line);

        output(message);

        // for (int i = 0; i < getInputPortCount(); i++) {
        // if (getInputPort(i).hasMessage()) {
        // log.trace("Message :{}", i);
        // Message message = getInputPort(i).poll();

        // if (message instanceof StringMessage) {
        // System.out.println(((StringMessage) message).getPayload());
        // } else if (message instanceof LongMessage) {
        // System.out.println(((LongMessage) message).getPayload());
        // }
        // }
        // }

    }

    @Override
    void postprocess() {
        scanner = null;
    }

}
