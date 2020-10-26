package com.example.shapelyapp.model;

public class Execrs {
    private int imageRcs;
    private String name;
    private String description;

    public Execrs (){ }

    public Execrs(int imageRcs, String name, String description) {
        this.imageRcs = imageRcs;
        this.name = name;
        this.description = description;
    }

    public int getImageRcs() {
        return imageRcs;
    }

    public void setImageRcs(int imageRcs) {
        this.imageRcs = imageRcs;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}