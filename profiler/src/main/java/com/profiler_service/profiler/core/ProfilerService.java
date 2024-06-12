package com.profiler_service.profiler.core;

import org.springframework.http.ResponseEntity;

import com.profiler_service.profiler.models.exceptions.BadGatewayException;
import com.profiler_service.profiler.models.exceptions.BadRequestException;

public interface ProfilerService {
    ResponseEntity<Void> salvarArquivo(String fileName, String fileBase64) throws BadRequestException, BadGatewayException;
    ResponseEntity<String> obterArquivo(String fileName) throws BadRequestException, BadGatewayException;
}
