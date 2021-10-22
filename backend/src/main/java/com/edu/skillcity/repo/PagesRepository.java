package com.edu.skillcity.repo;


import com.edu.skillcity.model.PageContentModel;
import com.edu.skillcity.model.PagesModel;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PagesRepository extends CrudRepository<PagesModel, Long> {

    @Query("select p from PagesModel p where p.page_type = :page_type")
    List<PagesModel> findAllByPage_type(@Param("page_type") String page_type);
}
