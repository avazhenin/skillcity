package com.edu.skillcity.service;

import com.edu.skillcity.dto.PageContentDTO;
import com.edu.skillcity.dto.PageDTO;
import com.edu.skillcity.model.PagesModel;
import com.edu.skillcity.model.PageContentModel;
import com.edu.skillcity.repo.PagesRepository;
import com.edu.skillcity.repo.PageDetailsRepository;
import com.edu.skillcity.utils.FileUtils;
import com.edu.skillcity.utils.PageContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.LinkedList;
import java.util.List;

@Service
public class PagesService {
    @Autowired
    private PagesRepository pagesRepository;
    @Autowired
    private PageDetailsRepository pageDetailsRepository;
    @Autowired
    private FileUtils fileUtils;

    public PagesModel savePage(PageDTO pageDTO) {

        Long page_id = pageDTO.getId();
        PagesModel pagesModel;

        if (page_id == null) {
            pagesModel = copyCourseDtoToEntity(pageDTO);
            pagesRepository.save(pagesModel);
        } else {
            PagesModel pagesModelOld = pagesRepository.findById(page_id).get();
            pagesModel = copyCourseDtoToEntity(pageDTO);
            if (pageDTO.getImage() == null) {
                pagesModel.setImage(pagesModelOld.getImage());
            }
            pagesRepository.save(pagesModel);
        }
        return pagesModel;
    }

    public List<PagesModel> getAll(String page_type) {
        List<PagesModel> pagesModels = new LinkedList<>();
        pagesRepository.findAllByPage_type(page_type).forEach(pagesModels::add);
        return pagesModels;
    }

    public List<PageContentModel> getPageData(Long course_id) {
        List<PageContentModel> pageContentModels = new LinkedList<>();
        pageDetailsRepository.findAllByPage_id(course_id).forEach(pageContentModels::add);
        return pageContentModels;
    }

    public PageContentModel savePageData(PageContentDTO pageContentDTO) {
        PageContentModel pageContentModel = new PageContentModel();

        pageContentModel.setId(pageContentDTO.getId());
        pageContentModel.setPage_id(pageContentDTO.getPage_id());
        pageContentModel.setEdit_fl(pageContentDTO.getEdit_fl());
        pageContentModel.setContent_type(pageContentDTO.getContent_type());

        if (pageContentDTO.getContent_type().equalsIgnoreCase(PageContentType.picture.getPage_Content_Type())) {
            String fileName = saveMultipartFile(pageContentDTO.getFile());
            if (fileName != null) pageContentModel.setText(fileName);
        } else {
            pageContentModel.setText(pageContentDTO.getText());
        }

        pageDetailsRepository.save(pageContentModel);

        return pageContentModel;
    }

    public void remove(PagesModel pagesModel) {
        Long page_id = pagesModel.getId();
        pagesRepository.deleteById(page_id);
        pageDetailsRepository.deleteAllByPage_id(page_id);
        fileUtils.deleteFile(pagesModel.getImage());
    }

    private PagesModel copyCourseDtoToEntity(PageDTO pageDTO) {
        PagesModel pagesModel = new PagesModel();
        pagesModel.setId(pageDTO.getId());
        pagesModel.setName(pageDTO.getName());
        pagesModel.setDscr(pageDTO.getDscr());
        pagesModel.setPage_type(pageDTO.getPage_type());

        String fileName = saveMultipartFile(pageDTO.getImage());
        if (fileName != null) pagesModel.setImage(fileName);

        return pagesModel;
    }

    private String saveMultipartFile(MultipartFile mfile) {
        if (mfile != null) {
            try {
                if (mfile.getBytes().length > 0) {
                    File file = fileUtils.saveFile(mfile);
                    return file.getName();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public PageContentModel addPageContent(PageContentDTO pageContentDTO) throws Exception {
        if (pageContentDTO.getId() != null) {
            PageContentModel pageContentModel = new PageContentModel();
            pageContentModel.setPage_id(pageContentDTO.getId());
            pageContentModel.setContent_type(pageContentDTO.getContent_type());

            if (pageContentDTO.getContent_type().equalsIgnoreCase(PageContentType.picture.getPage_Content_Type())) {
                MultipartFile mfile = pageContentDTO.getFile();
                if (mfile != null) {
                    try {
                        if (mfile.getBytes().length > 0) {
                            File file = fileUtils.saveFile(mfile);
                            // save picture name
                            pageContentModel.setText(file.getName());
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            } else {
                pageContentModel.setText(pageContentDTO.getText());
            }

            pageDetailsRepository.save(pageContentModel);
            return pageContentModel;
        } else {
            throw new Exception("Course ID is not specified");
        }
    }

    public void removePageText(PageContentModel pageContentModel) {
        pageDetailsRepository.deleteById(pageContentModel.getId());
    }
}
