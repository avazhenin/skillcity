package com.edu.skillcity.controller;

import com.edu.skillcity.dto.PageContentDTO;
import com.edu.skillcity.dto.PageDTO;
import com.edu.skillcity.model.PageContentModel;
import com.edu.skillcity.service.PagesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/page/content")
public class PageContentController {

    @Autowired
    PagesService pagesService;

    @RequestMapping("get_page_data")
    public ResponseEntity<List<PageContentModel>> getAllPageData(@RequestParam("page_id") Long page_id) {
        return new ResponseEntity<>(pagesService.getPageData(page_id), HttpStatus.OK);
    }

    /* Добавление контента на страницу в зависимости от его типа */
    @RequestMapping(
            value = "add_content",
            method = RequestMethod.POST,
            consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<PageContentModel> addContent(@ModelAttribute PageContentDTO pageContentDTO) {
        try {
            return new ResponseEntity(pagesService.addPageContent(pageContentDTO), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /* Изменение текста на странице */
    @RequestMapping(value = "edit_data")
    public ResponseEntity<PageContentModel> changeText(@ModelAttribute PageContentDTO pageContentDTO) {
        try {
            return new ResponseEntity(pagesService.savePageData(pageContentDTO), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping("delete")
    public ResponseEntity deleteText(@RequestParam("id") PageContentModel pageContentModel) {
        pagesService.removePageText(pageContentModel);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
