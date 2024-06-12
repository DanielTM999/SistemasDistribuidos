package com.dfs.dfs_b.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.dfs.dfs_b.core.DfsBServices;
import com.dfs.dfs_b.models.dto.FileDto;
import com.dfs.dfs_b.models.exceptions.BadGatewayException;
import com.dfs.dfs_b.models.exceptions.BadRequestException;

@RestController
public class MainController {
    
    private DfsBServices bServices;

    @PostMapping("/salvarArquivo")
    public ResponseEntity<Void> salvarArquivo(@RequestBody FileDto dto) throws BadRequestException, BadGatewayException{
        if(dto == null) throw new BadGatewayException("file not found", 400);
        return bServices.writeFile(dto.getFileName(), dto.getFile64());
    }

}
