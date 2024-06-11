package com.profiler_service.profiler.controllers;

import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.profiler_service.profiler.core.ProfilerService;
import com.profiler_service.profiler.models.dto.FileDto;

@RestController
public class MainController {
    
    @Autowired
    private ProfilerService profilerService;

    @PostMapping("/salvarArquivo/{archiverName}")
    public ResponseEntity<Void> salvarArquivo(@PathVariable(name = "archiverName") String archiverName, @RequestBody FileDto dto) throws Exception{
        if(dto == null){throw new BadRequestException("Arquivo invalido");}
        return profilerService.salvarArquivo(archiverName, dto.getFile64());
    }

}
