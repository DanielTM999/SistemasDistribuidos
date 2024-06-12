package com.dfs.dfs_c.services;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.dfs.dfs_c.core.DfsCServices;

@Service
public class DfsCServicesData implements DfsCServices{
    private static final String STARAGE_DIR = System.getProperty("user.dir");

    @Override
    public ResponseEntity<Void> writeFile(String fileName, String fileBase64) {
       System.out.println(STARAGE_DIR);
       return ResponseEntity.ok().build();
    }
    
}
