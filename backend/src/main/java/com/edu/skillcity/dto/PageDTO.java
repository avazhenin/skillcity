package com.edu.skillcity.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class PageDTO {
    private Long id;
    private String name;
    private String dscr;
    private MultipartFile image;
    private String page_type;
}
