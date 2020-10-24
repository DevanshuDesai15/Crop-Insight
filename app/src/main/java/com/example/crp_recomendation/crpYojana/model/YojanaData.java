package com.example.crp_recomendation.crpYojana.model;

public class YojanaData {

    String NameEng;
    String NameGuj;
    String DescEng;
    String DescGuj;
    String link;

    public YojanaData() {
        //pls keep no arg
    }

    public String getNameGuj() {
        return NameGuj;
    }

    public void setNameGuj(String nameGuj) {
        NameGuj = nameGuj;
    }

    public String getNameEng() {
        return NameEng;
    }

    public void setNameEng(String nameEng) {
        this.NameEng = nameEng;
    }

    public String getTitleGuj() {
        return NameGuj;
    }

    public void setTitleGuj(String titleGuj) {
        this.NameGuj = titleGuj;
    }

    public String getDescEng() {
        return DescEng;
    }

    public void setDescEng(String DescEng) {
        this.DescEng = DescEng;
    }

    public String getDescGuj() {
        return DescGuj;
    }

    public void setDescGuj(String DescGuj) {
        this.DescGuj = DescGuj;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
