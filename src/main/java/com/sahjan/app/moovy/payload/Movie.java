package com.sahjan.app.moovy.payload;

public class Movie {

    private String name;
    private String path;
    private String rating;
    private int acValue;
    private int hsValue;
    private int twaValue;
    private int sfValue;
    private int totalDiff;

    public Movie() {
    }

    public Movie(String name, String path, String rating, int acValue, int hsValue, int twaValue, int sfValue) {
        this.name = name;
        this.path = path;
        this.rating = rating;
        this.acValue = acValue;
        this.hsValue = hsValue;
        this.twaValue = twaValue;
        this.sfValue = sfValue;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public int getAcValue() {
        return acValue;
    }

    public void setAcValue(int acValue) {
        this.acValue = acValue;
    }

    public int getHsValue() {
        return hsValue;
    }

    public void setHsValue(int hsValue) {
        this.hsValue = hsValue;
    }

    public int getTwaValue() {
        return twaValue;
    }

    public void setTwaValue(int twaValue) {
        this.twaValue = twaValue;
    }

    public int getSfValue() {
        return sfValue;
    }

    public void setSfValue(int sfValue) {
        this.sfValue = sfValue;
    }

    public int getTotalDiff() {
        return totalDiff;
    }

    public void setTotalDiff(int totalDiff) {
        this.totalDiff = totalDiff;
    }

}
