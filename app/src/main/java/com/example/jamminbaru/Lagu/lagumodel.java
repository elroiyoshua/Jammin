package com.example.jamminbaru.Lagu;

public class lagumodel {
    String judul,band,url;
    int image;

    public String getJudul() {
        return judul;
    }

    public String getBand() {
        return band;
    }

    public int getImage() {
        return image;
    }

    public String getUrl() {
        return url;
    }

    public lagumodel(String judul, String band, String url, int image) {
        this.judul = judul;
        this.band = band;
        this.image = image;
        this.url = url;
    }
}
