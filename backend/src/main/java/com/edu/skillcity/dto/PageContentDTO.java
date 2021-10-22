package com.edu.skillcity.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class PageContentDTO {
    private Long id;
    private Long page_id;
    private String text;
    private MultipartFile file;
    private int edit_fl;
    private String content_type;
}
