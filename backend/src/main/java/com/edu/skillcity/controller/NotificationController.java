package com.edu.skillcity.controller;

import com.edu.skillcity.exceptions.CustomException;
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
    public ResponseEntity<NotificationsModel> GetRequest(@RequestParam("id") Long request_id) {
        try {
            return new ResponseEntity(notificationsRepository.findById(request_id).get(), HttpStatus.OK);
        } catch (Exception ex) {
            throw new CustomException(HttpStatus.INTERNAL_SERVER_ERROR, "Ошибка при обработке запроса find", ex.getMessage(), ex.getCause().getCause().getMessage());
        }
    }

    @RequestMapping("add")
    public ResponseEntity addRequest(@RequestBody NotificationsModel notificationsModel) {
        try {
            if (notificationsModel.getId() == null) {
                notificationsRepository.save(notificationsModel);
            } else {
                NotificationsModel model = notificationsRepository.findById(notificationsModel.getId()).get();
                model.setStatus(notificationsModel.getStatus());
                notificationsRepository.save(model);
            }
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception ex) {
            throw new CustomException(HttpStatus.INTERNAL_SERVER_ERROR, "Ошибка при обработке запроса add", ex.getMessage(), ex.getCause().getCause().getMessage());
        }
    }
}
