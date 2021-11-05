package com.edu.skillcity.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Table(name = "notifications")
public class NotificationsModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String type;
    @Column
    private Date insert_date;
    @Column
    private Date viewed_date;
    @Column
    private int status;
    @Column(name = "cell_phone")
    private String cell_phone;
    @Column(name = "email")
    private String email;
}
