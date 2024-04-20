package com.coconsult.pidevspring.DAO.Entities;





public class FileInfodoc  {

    private String name;
    private String url;

    public FileInfodoc(String name, String url) {
        this.name = name;
        this.url = url;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }


}
