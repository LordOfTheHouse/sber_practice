package com.example.models;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
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
    private Date dateTo;

    public Novel(String title, String description, List<String> tags) {
        uid = secondUid++;
        this.title = title;
        this.description = description;
        this.tags = tags;
        img = "/img/w.svg";
        dateTo = Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    public Novel(String title, String description, String img, List<String> tags) {
        uid = secondUid++;
        this.title = title;
        this.description = description;
        this.tags = tags;
        this.img = img;
        dateTo = Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant());
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

    public Date getDateTo() {
        return dateTo;
    }
}
