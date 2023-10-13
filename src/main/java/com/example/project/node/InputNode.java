package com.example.project.node;

import com.example.project.exception.AlreadyExistsException;
import com.example.project.exception.InvalidArgumentException;
import com.example.project.exception.OutOfBoundsException;
import com.example.project.message.Message;
import com.example.project.wire.Wire;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class InputNode extends ActiveNode {
    Wire[] outputWires;

    protected InputNode(String name, int count) {
        super(name);

        if (count <= 0) {
            throw new InvalidArgumentException();
        }
        outputWires = new Wire[count];
    }

    protected InputNode(int count) {
        super();

        if (count <= 0) {
            throw new InvalidArgumentException();
        }
        outputWires = new Wire[count];
    }

    public void connectOutputWire(int index, Wire wire) {
        if (outputWires.length <= index) {
            throw new OutOfBoundsException();
        }

        if (outputWires[index] != null) {
            throw new AlreadyExistsException();
        }
        outputWires[index] = wire;
    }



    public int getOutputWireCount() {
        return outputWires.length;
    }

    public Wire getoutputWire(int index) {
        if (index < 0 || outputWires.length <= index) {
            throw new OutOfBoundsException();
        }
        return outputWires[index];
    }


    public void output(Message message) {
        log.trace("Message Out");
        for (Wire wire : outputWires) {
            if (wire != null) {
                wire.put(message);
            }
        }
    }

}
