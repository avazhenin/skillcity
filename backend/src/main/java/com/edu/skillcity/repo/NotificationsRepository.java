package com.edu.skillcity.repo;

import com.edu.skillcity.model.NotificationsModel;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface NotificationsRepository extends CrudRepository<NotificationsModel, Long> {

    @Query("select c from NotificationsModel c where c.status = 0")
    List<NotificationsModel> findAll();
}
