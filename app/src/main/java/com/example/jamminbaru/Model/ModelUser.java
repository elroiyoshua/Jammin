package com.example.jamminbaru.Model;

public class ModelUser {
    private String phoneTxt = "nama";

    public ModelUser(String phoneTxt) {
        this.phoneTxt = phoneTxt;
    }

    public String getPhoneTxt() {
        return phoneTxt;
    }

    public void setPhoneTxt(String phoneTxt) {
        this.phoneTxt = phoneTxt;
    }
}
