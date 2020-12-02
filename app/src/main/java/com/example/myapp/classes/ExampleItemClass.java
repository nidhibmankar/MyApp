package com.example.myapp.classes;

public class ExampleItemClass {
    private String mimageUrl;
    private String mcreatorName;
    private int mlikes;

    public ExampleItemClass(String imageUrl, String creatorName, int likes) {
        mimageUrl = imageUrl;
        mcreatorName = creatorName;
        mlikes = likes;
    }

    public String getMimageUrl() {
        return mimageUrl;
    }

    public void setMimageUrl(String imageUrl) {
        mimageUrl = imageUrl;
    }

    public String getMcreatorName() {
        return mcreatorName;
    }

    public void setMcreatorName(String creatorName) {
        mcreatorName = creatorName;
    }

    public int getMlikes() {
        return mlikes;
    }

    public void setMlikes(int likes) {
        mlikes = likes;
    }
}
