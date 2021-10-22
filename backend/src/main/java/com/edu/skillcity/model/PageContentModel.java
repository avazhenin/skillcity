package com.edu.skillcity.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Data
@Table(name = "pages_content")
public class PageContentModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "page_id")
    @NotBlank(message = "Please fill 'course_id' value")
    private Long page_id;
    @Column(name = "text", columnDefinition = "TEXT")
    private String text;
    @Column(name = "content_type", nullable = false)
    @NotBlank(message = "Please fill 'content_type' value")
    private String content_type;
    @Column(name = "edit_fl", nullable = true)
    private int edit_fl = 0;
}
