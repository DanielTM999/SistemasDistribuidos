package com.master.master_node.models.dto;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class FileDto {
    private String file64;
    private String fileName;
}
