package com.example.project.node;

import com.example.project.wire.Wire;

import com.example.project.exception.OutOfBoundsException;
import com.example.project.message.Message;
import com.example.project.wire.Wire;

public abstract class InputOutNode extends ActiveNode {
    Wire[] inputWires; // 생성된 포트
    Wire[] outputWires; // 들어 오길 기다리는 포트,

    InputOutNode(String name, int inCount, int outCount) {
        super(name);
        inputWires = new Wire[inCount];
        outputWires = new Wire[outCount];
    }

    public InputOutNode(int inCount, int outCount) {
        super();
        inputWires = new Wire[inCount];
        outputWires = new Wire[outCount];
    }

    public void connectOutputWire(int index, Wire wire) {
        if (index < 0 || outputWires.length <= index) {
            throw new OutOfBoundsException();
        }
        outputWires[index] = wire;
    }

    public int getInputWireCount() {
        return Wires.length;
    }

    public Wire getInputWire(int index) {
        if (index < 0 || peerWire.length <= index) {
            throw new OutOfBoundsException();
        }
        return Wires[index];
    }

    public void output(Message message) {
        for (Wire Wire : peerWire) {
            if (Wire != null) {
                Wire.add(message);
            }
        }
    }

}
