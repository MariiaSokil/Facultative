package com.epam.model;

public class Topic {
    private Long id;
    private String category;

    public Topic() {

    }

    public Topic(Long id, String category) {
        this.id = id;
        this.category = category;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
