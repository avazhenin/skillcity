package com.edu.skillcity.controller;

import com.edu.skillcity.dto.PageDTO;
import com.edu.skillcity.model.PagesModel;
import com.edu.skillcity.service.PagesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/page")
public class PagesController {

    @Autowired
    PagesService pagesService;

    @RequestMapping(
            value = "upload",
            method = RequestMethod.POST,
            consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<PagesModel> uploadCourse(@ModelAttribute PageDTO pageDTO) {
        try {
            PagesModel pagesModel = pagesService.savePage(pageDTO);
            return new ResponseEntity<>(pagesModel, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getCause(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping("getall")
    public ResponseEntity<List<PagesModel>> getAll(@RequestParam("page_type") String page_type) {
        try {
            return new ResponseEntity<List<PagesModel>>(pagesService.getAll(page_type), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getCause(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping("delete")
    public ResponseEntity<String> deleteCourse(@RequestParam("id") PagesModel pagesModel) {
        try {
            pagesService.remove(pagesModel);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getCause(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
