package com.example.project.node;

import com.example.project.exception.AlreadyExistsException;
import com.example.project.exception.InvalidArgumentException;
import com.example.project.exception.OutOfBoundsException;
import com.example.project.wire.Wire;

public class OutputNode extends ActiveNode {
    Wire[] inputWires;

    protected OutputNode(String name, int count) {
        super(name);
        if (count <= 0) {
            throw new InvalidArgumentException();
        }
        inputWires = new Wire[count];
    }

    protected OutputNode(int count) {
        super();
        if (count <= 0) {
            throw new InvalidArgumentException();
        }
        inputWires = new Wire[count];
    }

    public void connectInputWire(int index, Wire wire) {
        if (inputWires.length <= index) {
            throw new OutOfBoundsException();
        }
        if (inputWires[index] != null) {
            throw new AlreadyExistsException();
        }
        inputWires[index] = wire;
    }

    public int getInputWireCount() {
        return inputWires.length;
    }

    public Wire getInpuWire(int index) {
        if (index < 0 || inputWires.length <= index) {
            throw new OutOfBoundsException();
        }
        return inputWires[index];
    }
}
