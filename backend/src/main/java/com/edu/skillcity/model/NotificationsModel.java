package com.edu.skillcity.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Table(name = "notifications")
public class NotificationsModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column
    private String type;
    @Column
    private Date insert_date;
    @Column
    private Date viewed_date;
    @Column
    private int status;
    @Column
    private Long parent_id; // Notified object id (REQUESTS.ID for example)
}
