package com.master.master_node.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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

    @PostMapping("/salvarArquivo")
    public ResponseEntity<Void> salvarArquivo(@RequestBody FileDto dto) throws BadRequestException, BadGatewayException{
        if(dto == null) throw new BadGatewayException("file not found", 400);
        return nodeServices.salvarArquivo(dto.getFileName(), dto.getFile64());
    }

    @GetMapping("/obterArquivo/{archiverName}")
    public ResponseEntity<String> obterArquivo(@PathVariable(name = "archiverName") String archiverName) throws Exception{
        return nodeServices.obterArquivo(archiverName);
    }

}
