package com.dfs.dfs_b.models.exceptions;

public class BadRequestException extends Exception{
    private int code;

    public BadRequestException(String msg, int code){
        super(msg);
        this.code = code;
    }

    public int getCode(){
        return code;
    }
}
