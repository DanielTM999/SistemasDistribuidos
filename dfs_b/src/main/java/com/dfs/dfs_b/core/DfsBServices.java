package com.dfs.dfs_b.core;

import org.springframework.http.ResponseEntity;

public interface DfsBServices {
    ResponseEntity<Void> writeFile(String fileName, String fileBase64);
    ResponseEntity<String> obterArquivo(String fileName);
}
