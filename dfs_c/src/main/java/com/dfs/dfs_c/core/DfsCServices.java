package com.dfs.dfs_c.core;

import org.springframework.http.ResponseEntity;

public interface DfsCServices {
    ResponseEntity<Void> writeFile(String fileName, String fileBase64);
}
