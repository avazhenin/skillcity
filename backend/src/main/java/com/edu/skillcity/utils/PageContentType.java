package com.edu.skillcity.utils;

public enum PageContentType {

    text("text"),
    picture("picture"),
    youtube("youtube");

    private final String page_content_type;

    private PageContentType(String page_content_type) {
        this.page_content_type = page_content_type;
    }

    public String getPage_Content_Type() {
        return this.page_content_type;
    }
}
