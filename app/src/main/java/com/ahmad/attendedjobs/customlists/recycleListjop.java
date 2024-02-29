package com.ahmad.attendedjobs.customlists;

public class recycleListjop {
    String titlejop,expierence;
    int logoimgjob;

    public recycleListjop(String titlejop, String expierence, int logoimgjob) {
        this.titlejop = titlejop;
        this.expierence = expierence;
        this.logoimgjob = logoimgjob;
    }

    public String getTitlejop() {
        return titlejop;
    }

    public void setTitlejop(String titlejop) {
        this.titlejop = titlejop;
    }

    public String getExpierence() {
        return expierence;
    }

    public void setExpierence(String expierence) {
        this.expierence = expierence;
    }

    public int getLogoimgjob() {
        return logoimgjob;
    }

    public void setLogoimgjob(int logoimgjob) {
        this.logoimgjob = logoimgjob;
    }
}
