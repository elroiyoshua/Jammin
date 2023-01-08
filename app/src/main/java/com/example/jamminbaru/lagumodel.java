package com.example.jamminbaru;

public class lagumodel {
    String judul,band,url;
    int image;//,posisi;

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

//        public int getPosisi() {
//            return posisi;
//        }

    public lagumodel(String judul, String band, int image, String url){//,int posisi) {
        this.judul = judul;
        this.band = band;
        this.image = image;
        this.url = url;
        //this.posisi = posisi;
    }
}
