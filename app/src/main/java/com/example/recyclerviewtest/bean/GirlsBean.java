package com.example.recyclerviewtest.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * author: Administrator
 * date : 2020/12/9
 * desc :
 **/
public class GirlsBean extends GankBaseBean{

    private List<GankEntity> data;

    public List<GankEntity> getData() {
        if(data== null){
            data = new ArrayList<>();
        }
        return data;
    }

    public void setData(List<GankEntity> data) {
        this.data = data;
    }

    public static class DataEntity {
        /**
         * _id : 5e959250808d6d2fe6b56eda
         * author : 鸢媛
         * category : Girl
         * createdAt : 2020-05-25 08:00:00
         * desc : 与其安慰自己平凡可贵，
         不如拼尽全力活得漂亮。 ​ ​​​​
         * images : ["http://gank.io/images/f4f6d68bf30147e1bdd4ddbc6ad7c2a2"]
         * likeCounts : 5
         * publishedAt : 2020-05-25 08:00:00
         * stars : 1
         * title : 第96期
         * type : Girl
         * url : http://gank.io/images/f4f6d68bf30147e1bdd4ddbc6ad7c2a2
         * views : 10598
         */

        private String id;
        private String author;
        private String category;
        private String createdAt;
        private String desc;
        private int likeCounts;
        private String publishedAt;
        private int stars;
        private String title;
        private String type;
        private String url;
        private int views;
        private List<String> images;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getAuthor() {
            return author;
        }

        public void setAuthor(String author) {
            this.author = author;
        }

        public String getCategory() {
            return category;
        }

        public void setCategory(String category) {
            this.category = category;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public int getLikeCounts() {
            return likeCounts;
        }

        public void setLikeCounts(int likeCounts) {
            this.likeCounts = likeCounts;
        }

        public String getPublishedAt() {
            return publishedAt;
        }

        public void setPublishedAt(String publishedAt) {
            this.publishedAt = publishedAt;
        }

        public int getStars() {
            return stars;
        }

        public void setStars(int stars) {
            this.stars = stars;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public int getViews() {
            return views;
        }

        public void setViews(int views) {
            this.views = views;
        }

        public List<String> getImages() {
            return images;
        }

        public void setImages(List<String> images) {
            this.images = images;
        }
    }
}
