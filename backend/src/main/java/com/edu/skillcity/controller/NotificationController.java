package com.edu.skillcity.controller;

import com.edu.skillcity.model.NotificationsModel;
import com.edu.skillcity.model.RequestsModel;
import com.edu.skillcity.repo.NotificationsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/notification")
public class NotificationController {
    @Autowired
    private NotificationsRepository notificationsRepository;

    @GetMapping("findAll")
    public List<NotificationsModel> getAllRequests() {
        return notificationsRepository.findAll();
    }
}
