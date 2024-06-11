package com.master.master_node.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.master.master_node.core.MasterNodeServices;
import com.master.master_node.models.dto.FileDto;
import com.master.master_node.models.exceptions.BadGatewayException;
import com.master.master_node.models.exceptions.BadRequestException;

@RestController
public class MainController {
    
    @Autowired
    private MasterNodeServices nodeServices;

    public ResponseEntity<Void> salvarArquivo(@RequestBody FileDto dto) throws BadRequestException, BadGatewayException{
        return nodeServices.salvarArquivo(null, null);
    }

}
