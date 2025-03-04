package com.tp.taskmanager.task_manager.exception;

public class InvalidAuthException extends RuntimeException{

    public InvalidAuthException(String message){
        super(message);
    }
}
