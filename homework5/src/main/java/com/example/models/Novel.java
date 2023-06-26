package com.example.models;

import java.util.List;

/**
 * Новелла
 */
public class Novel {
    private static long secondUid = 1;
    private long uid;
    private String title;
    private String img;
    private String description;
    private List<String> tags;

    public Novel(String title, String description, List<String> tags) {
        uid = secondUid++;
        this.title = title;
        this.description = description;
        this.tags = tags;
        img = "/img/w.svg";
    }

    public Novel(String title, String description, String img, List<String> tags) {
        uid = secondUid++;
        this.title = title;
        this.description = description;
        this.tags = tags;
        this.img = img;
    }

    public long getUid() {
        return uid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }
}
