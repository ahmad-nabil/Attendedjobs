package com.ahmad.attendedjobs.customlists;

public class customlistrecycleviewlatestevent {
String firstline, secondline;

    public customlistrecycleviewlatestevent(String firstline, String secondline) {
        this.firstline = firstline;
        this.secondline = secondline;
    }

    public String getFirstline() {
        return firstline;
    }

    public void setFirstline(String firstline) {
        this.firstline = firstline;
    }

    public String getSecondline() {
        return secondline;
    }

    public void setSecondline(String secondline) {
        this.secondline = secondline;
    }
}
