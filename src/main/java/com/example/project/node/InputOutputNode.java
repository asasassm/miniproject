package com.example.project.node;

import com.example.project.wire.Wire;

import com.example.project.exception.OutOfBoundsException;
import com.example.project.message.Message;

public abstract class InputOutputNode extends ActiveNode {
    Wire[] inputWires; // 생성된 포트
    Wire[] outputWires; // 들어 오길 기다리는 포트,

    protected InputOutputNode(String name, int inCount, int outCount) {
        super(name);
        inputWires = new Wire[inCount];
        outputWires = new Wire[outCount];
    }

    protected InputOutputNode(int inCount, int outCount) {
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

    public void connectInputWire(int index, Wire wire) {
        if (index < 0 || inputWires.length <= index) {
            throw new OutOfBoundsException();
        }
        inputWires[index] = wire;
    }

    public int getInputWireCount() {
        return inputWires.length;
    }

    public int getOutputWireCount() {
        return outputWires.length;
    }

    public Wire getInputWire(int index) {
        if (index < 0 || inputWires.length <= index) {
            throw new OutOfBoundsException();
        }
        return inputWires[index];
    }

    public Wire getOutputWire(int index) {
        if (index < 0 || outputWires.length <= index) {
            throw new OutOfBoundsException();
        }
        return outputWires[index];
    }

    public void output(Message message) {
        for (Wire Wire : outputWires) {
            if (Wire != null) {
                Wire.put(message);
            }
        }
    }
}
