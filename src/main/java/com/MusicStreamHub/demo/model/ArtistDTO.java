package com.MusicStreamHub.demo.model;
import java.util.List;

import java.util.List;

public class ArtistDTO {

        private int followers;
        private List<String> genres;
        private String id;
        private List<Image> images;
        private String name;
        private int popularity;



        public int getPopularity() {
        return popularity;
    }

        public void setPopularity(int popularity) {
        this.popularity = popularity;
    }

        public int getFollowers() {
            return followers;
        }

        public void setFollowers(int followers) {
            this.followers = followers;
        }

        public List<String> getGenres() {
            return genres;
        }

        public void setGenres(List<String> genres) {
            this.genres = genres;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public List<Image> getImages() {
            return images;
        }

        public void setImages(List<Image> images) {
            this.images = images;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }


    }




// Remember to create classes for Followers and Images and the rest of the information requrired.
