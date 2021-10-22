package com.edu.skillcity.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "pages",
        indexes = {
                @Index(name = "page_id_pk", unique = true, columnList = "id"),
                @Index(name = "page_type_idx", unique = false, columnList = "page_type")
        })
public class PagesModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "dscr")
    private String dscr;
    @Column(name = "image")
    private String image;
    @Column(name = "page_type")
    private String page_type;
}

/*
* insert into pages(dscr, image, name, page_type) values ('о нас', null, 'о нас', 'about');
* insert into pages(dscr, image, name, page_type) values ('контакты', null, 'контакты', 'contacts');
* */