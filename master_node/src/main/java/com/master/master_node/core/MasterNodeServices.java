package com.master.master_node.core;

import org.springframework.http.ResponseEntity;

import com.master.master_node.models.exceptions.BadGatewayException;
import com.master.master_node.models.exceptions.BadRequestException;

public interface MasterNodeServices {
    ResponseEntity<Void> salvarArquivo(String fileName, String fileBase64) throws BadRequestException, BadGatewayException; 
    ResponseEntity<String> obterArquivo(String fileName) throws BadRequestException, BadGatewayException;

}
