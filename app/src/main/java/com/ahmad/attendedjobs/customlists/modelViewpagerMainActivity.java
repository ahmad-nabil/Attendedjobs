package com.ahmad.attendedjobs.customlists;

public class modelViewpagerMainActivity {
    String title;
    String details;
    int img;

    public modelViewpagerMainActivity(String title, String details, int img) {
        this.title = title;
        this.details = details;
        this.img = img;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getTitle() {
        return title;
    }

    public String getDetails() {
        return details;
    }

    public int getImg() {
        return img;
    }
}
