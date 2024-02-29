package com.ahmad.attendedjobs.customlists;

public class lastnewslist {
    String title,introductionnews,compleationnews;
    int img;

    public lastnewslist(String title, String introductionnews, String compleationnews, int img) {
        this.title = title;
        this.introductionnews = introductionnews;
        this.compleationnews = compleationnews;
        this.img = img;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIntroductionnews() {
        return introductionnews;
    }

    public void setIntroductionnews(String introductionnews) {
        this.introductionnews = introductionnews;
    }

    public String getCompleationnews() {
        return compleationnews;
    }

    public void setCompleationnews(String compleationnews) {
        this.compleationnews = compleationnews;
    }
}
