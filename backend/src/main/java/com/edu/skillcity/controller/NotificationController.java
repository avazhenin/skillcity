package com.edu.skillcity.controller;

import com.edu.skillcity.model.NotificationsModel;
import com.edu.skillcity.repo.NotificationsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("find")
    public NotificationsModel GetRequest(@RequestParam("id") Long request_id) {
        return notificationsRepository.findById(request_id).get();
    }

    @RequestMapping("add")
    public ResponseEntity addRequest(@RequestBody NotificationsModel notificationsModel) {
        try {
            if (notificationsModel.getId() == null) {
                notificationsRepository.save(notificationsModel);
            } else {
                NotificationsModel model = notificationsRepository.findById(notificationsModel.getId()).get();
                System.out.println("notificationsModel.getStatus()="+notificationsModel.getStatus());
                model.setStatus(notificationsModel.getStatus());
                notificationsRepository.save(model);
            }
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.OK);
        }
    }
}
