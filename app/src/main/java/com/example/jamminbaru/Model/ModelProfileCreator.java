package com.example.jamminbaru.Model;

public class ModelProfileCreator {
    private String imageUrl;

    public ModelProfileCreator(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
