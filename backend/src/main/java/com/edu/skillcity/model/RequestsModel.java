package com.edu.skillcity.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Table(name = "requests")
public class RequestsModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "sellphone")
    private String sellphone;
    @Column(name = "reviewed")
    private int reviewed;
    @Column(name = "posted_date")
    private Date posted_date;
}
