package com.master.master_node.models.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.master.master_node.models.res.ErrorResponse;


@ControllerAdvice
public class GlobalAdviceHandlerController {
    
    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleException(Exception e) {
        return ResponseEntity.badRequest().body(new ErrorResponse(500, e.getMessage()));
    }

    @ExceptionHandler(BadGatewayException.class)
    public ResponseEntity<?> handleBadGatewayException(BadGatewayException e) {
        return ResponseEntity.status(e.getCode()).body(new ErrorResponse(e.getCode(), e.getMessage()));

    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<?> handleBadRequestException(BadRequestException e) {
        return ResponseEntity.status(e.getCode()).body(e.getMessage());

    }

}