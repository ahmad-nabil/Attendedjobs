package com.ahmad.attendedjobs.customlists;

public class recyclelistMainActivity {
    String Titlecourse;
    String time;
    String days;
    int img;

    public recyclelistMainActivity(String titlecourse, String time, String days, int img) {
        Titlecourse = titlecourse;
        this.time = time;
        this.days = days;
        this.img = img;
    }

    public String getTitlecourse() {
        return Titlecourse;
    }

    public void setTitlecourse(String titlecourse) {
        Titlecourse = titlecourse;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDays() {
        return days;
    }

    public void setDays(String days) {
        this.days = days;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }
}
