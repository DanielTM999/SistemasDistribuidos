package com.isp_server.isp.core;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import com.isp_server.isp.models.exceptions.BadGatewayException;
import com.isp_server.isp.models.exceptions.BadRequestException;

import jakarta.servlet.http.HttpServletResponse;

public interface IspServices {
    ResponseEntity<Void> salvarArquivo(String fileName, MultipartFile fileBytes) throws BadRequestException, BadGatewayException;
    void obterArquivo(String fileName, HttpServletResponse response) throws BadRequestException, BadGatewayException;
}
