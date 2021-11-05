package com.edu.skillcity.repo;

import com.edu.skillcity.model.NotificationsModel;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface NotificationsRepository extends CrudRepository<NotificationsModel, Long> {
    List<NotificationsModel> findAll();
}
