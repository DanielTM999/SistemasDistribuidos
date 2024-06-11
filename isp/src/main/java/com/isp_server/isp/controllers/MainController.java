package com.isp_server.isp.controllers;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.isp_server.isp.core.IspServices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
public class MainController {

    @Autowired
    private IspServices ispServices;
    
    @PostMapping("/salvarArquivo/{archiverName}")
    public void salvarArquivo(@PathVariable(name = "archiverName") String archiverName, @RequestParam("file") MultipartFile file) throws Exception{
        ispServices.salvarArquivo(archiverName, file);
    }

}
