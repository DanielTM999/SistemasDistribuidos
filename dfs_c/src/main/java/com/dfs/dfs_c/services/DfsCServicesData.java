package com.dfs.dfs_c.services;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Base64;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.dfs.dfs_c.core.DfsCServices;

@Service
public class DfsCServicesData implements DfsCServices{
    private static final String STORAGE_DIR = System.getProperty("user.dir")+"/"+"uploads";

    @Override
    public ResponseEntity<Void> writeFile(String fileName, String fileBase64) {
        Path filePath = Paths.get(STORAGE_DIR, fileName);
        byte[] fileBytes = base64ToByte(fileBase64);

        try {
            if (!Files.exists(Paths.get(STORAGE_DIR))) {
                Files.createDirectories(Paths.get(STORAGE_DIR));
            }

            Files.write(filePath, fileBytes, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
            return ResponseEntity.ok().build();
        } catch (IOException e) {
            return ResponseEntity.status(500).build();
        }

    }

    private byte[] base64ToByte(String fileBase64){
        return Base64.getDecoder().decode(fileBase64);
    }
}
