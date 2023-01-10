package com.example.jamminbaru.Model;

public class ModelHomeCreator{
        private String soundUrl;

        public ModelHomeCreator(String imageUrl) {
            this.soundUrl = imageUrl;
        }

        public String getImageUrl() {
            return soundUrl;
        }

        public void setImageUrl(String imageUrl) {
            this.soundUrl = imageUrl;
        }
}
