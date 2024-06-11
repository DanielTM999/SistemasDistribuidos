package com.isp_server.isp.models.exceptions;

public class BadGatewayException extends Exception{
    
    private int code;

    public BadGatewayException(String msg, int code){
        super(msg);
        this.code = code;
    }

    public int getCode(){
        return code;
    }

}
