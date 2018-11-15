package com.sajidabdali.personalitydevelopmentapp;

public class Articles {

    private String Title;
    private String Desc;
    private String Image;

    private String Desce, Head;

    public Articles() {
    }

    public Articles(String desc, String head) {
        this.Desce = desc;
        this.Head = head;
    }


    public Articles(String title, String desc, String image) {
        Title = title;
        Desc = desc;
        Image = image;
    }


    public String getHead() {
        return Head;
    }

    public void setHead(String head) {
        this.Head = head;
    }

    public String getDesce() {
        return Desce;
    }

    public void setDesce(String desce) {
        this.Desce = desce;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getDesc() {
        return Desc;
    }

    public void setDesc(String desc) {
        Desc = desc;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }
}
