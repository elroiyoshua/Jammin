package com.example.jamminbaru.Model;

public class ModelHomeCreator{
    private String soundUrl;

    public ModelHomeCreator(String soundUrl) {
        this.soundUrl = soundUrl;
    }

    public String getSoundUrl() {
        return soundUrl;
    }

    public void setSoundUrl(String soundUrl) {
        this.soundUrl = soundUrl;
    }
}
