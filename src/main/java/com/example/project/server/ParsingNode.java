package com.example.project.server;

import java.net.Socket;

import com.example.project.message.Message;
import com.example.project.message.WrappingMessage;
import com.example.project.node.InputOutputNode;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ParsingNode extends InputOutputNode{
    String headMessage;
    String fieldMessage;


    protected ParsingNode(int inCount, int outCount) {
        super(inCount, outCount);
    }

    public void splitMessage(String message){
        String[] parsingMessage = message.split("\n");

        headMessage = parsingMessage[0];
        fieldMessage = parsingMessage[1];

        log.info("head : {}",headMessage);
        log.info("head : {}", fieldMessage);
    }

    public String getHeadMessage() {
        return headMessage;
    }

    public String getFieldMessage() {
        return fieldMessage;
    }

    @Override
    protected void process() {

        Message insocketMessage = getInputWire(0).get();

        if(insocketMessage instanceof WrappingMessage){//소켓정보, Message WrappingMessage인지 체크
            WrappingMessage wrappingMessage = (WrappingMessage)insocketMessage;
            Socket socket = wrappingMessage.getSocket();
            String message = wrappingMessage.getMessage();

            log.info("message"); //나중에 생략 -> debuggerNode로 바꿀 계획

            


        }





    }
    
    
}
