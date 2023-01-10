package com.example.jamminbaru.Model;

public class ModelProfileUser {
    private String imageUrl;

    public ModelProfileUser(String imageUrl){
        this.imageUrl = imageUrl;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
