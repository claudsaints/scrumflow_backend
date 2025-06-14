package com.claudsaints.scrumflow.controllers.exceptions;

public class ObjectNotFound extends RuntimeException{
    public ObjectNotFound(String msg){
        super(msg);
    }
}
