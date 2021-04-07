package com.jaikeerthick.feedsample.Model;

public class FeedModel {

    private String name;
    private String imageUrl;
    private String avatarUrl;
    private String time;
    private String date;
    private String timestamp;
    private String caption;
    private String comments;
    private String likes;

    public FeedModel() {
    }

    public FeedModel(String name, String imageUrl, String avatarUrl, String time, String date, String timestamp, String caption, String comments, String likes) {
        this.name = name;
        this.imageUrl = imageUrl;
        this.avatarUrl = avatarUrl;
        this.time = time;
        this.date = date;
        this.timestamp = timestamp;
        this.caption = caption;
        this.comments = comments;
        this.likes = likes;
    }

    public String getName() {
        return name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public String getTime() {
        return time;
    }

    public String getDate() {
        return date;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public String getCaption() {
        return caption;
    }

    public String getComments() {
        return comments;
    }

    public String getLikes() {
        return likes;
    }
}
