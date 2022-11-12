package com.apothekenlager;

public class Medikament {
    private String pzn = "";
    private int menge;

    public Medikament(String pzn, int menge) {
        this.pzn = pzn;
        this.menge = menge;
    }


    public String getPzn() {
        return pzn;
    }

    public int getMenge() {
        return menge;
    }


    public void setPzn(String pzn) {
        this.pzn = pzn;
    }

    public void setMenge(int menge) {
        this.menge = menge;
    }

    @Override
    public String toString() {
        return pzn + " " + menge;
    }
}