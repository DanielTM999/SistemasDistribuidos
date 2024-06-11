package com.isp_server.isp.core;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import com.isp_server.isp.models.exceptions.BadGatewayException;
import com.isp_server.isp.models.exceptions.BadRequestException;

public interface IspServices {
    ResponseEntity<Void> salvarArquivo(String fileName, MultipartFile fileBytes) throws BadRequestException, BadGatewayException;
}
