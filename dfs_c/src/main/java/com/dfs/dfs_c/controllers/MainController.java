package com.dfs.dfs_c.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.dfs.dfs_c.core.DfsCServices;
import com.dfs.dfs_c.models.dto.FileDto;
import com.dfs.dfs_c.models.exceptions.BadGatewayException;
import com.dfs.dfs_c.models.exceptions.BadRequestException;

@RestController
public class MainController {
    
    private DfsCServices cServices;

    @PostMapping("/salvarArquivo")
    public ResponseEntity<Void> salvarArquivo(@RequestBody FileDto dto) throws BadRequestException, BadGatewayException{
        if(dto == null) throw new BadGatewayException("file not found", 400);
        return cServices.writeFile(dto.getFileName(), dto.getFile64());
    }

}
