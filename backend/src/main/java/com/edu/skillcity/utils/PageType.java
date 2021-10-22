package com.edu.skillcity.utils;

public enum PageType {

    news("news"),
    about("about"),
    afisha("afisha"),
    courses("courses"),
    contacts("contacts"),
    teachers("teachers");

    private final String page_type;

    private PageType(String pageType) {
        this.page_type = pageType;
    }

    public String getPage_type() {
        return this.page_type;
    }
}
