package com.ahmad.attendedjobs.customlists;

public class customevntviewpager {
String eventtitle,timeevent,dayevent,dateevent;

    public customevntviewpager(String eventtitle, String timeevent, String dayevent, String dateevent) {
        this.eventtitle = eventtitle;
        this.timeevent = timeevent;
        this.dayevent = dayevent;
        this.dateevent = dateevent;
    }

    public String getEventtitle() {
        return eventtitle;
    }

    public void setEventtitle(String eventtitle) {
        this.eventtitle = eventtitle;
    }

    public String getTimeevent() {
        return timeevent;
    }

    public void setTimeevent(String timeevent) {
        this.timeevent = timeevent;
    }

    public String getDayevent() {
        return dayevent;
    }

    public void setDayevent(String dayevent) {
        this.dayevent = dayevent;
    }

    public String getDateevent() {
        return dateevent;
    }

    public void setDateevent(String dateevent) {
        this.dateevent = dateevent;
    }
}
