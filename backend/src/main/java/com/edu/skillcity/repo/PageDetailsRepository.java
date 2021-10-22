package com.edu.skillcity.repo;

import com.edu.skillcity.model.PageContentModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface PageDetailsRepository extends JpaRepository<PageContentModel, Long> {

    @Query("select c from PageContentModel c where c.page_id = :page_id order by c.id")
    List<PageContentModel> findAllByPage_id(@Param("page_id") Long page_id);

    @Transactional
    @Modifying
    @Query("delete from PageContentModel c where c.page_id = :page_id")
    void deleteAllByPage_id(@Param("page_id") Long page_id);
}
