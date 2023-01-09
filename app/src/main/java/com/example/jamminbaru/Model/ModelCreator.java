package com.example.jamminbaru.Model;

public class ModelCreator {
    private String phoneTxt = "nama";

    public ModelCreator(String phoneTxt) {
        this.phoneTxt = phoneTxt;
    }

    public String getPhoneTxt() {
        return phoneTxt;
    }

    public void setPhoneTxt(String phoneTxt) {
        this.phoneTxt = phoneTxt;
    }
}
