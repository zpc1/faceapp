package com.deeocare.deepcare.bean;

import android.graphics.Color;

import java.util.HashMap;
import java.util.Map;

public class PingCeSingle {
    private Map<String, Float> names = new HashMap<>();
    private int score;
    private String des;
    private String title;
    private int titleColor;
    private int webColor;
    private int fillColor;

    public PingCeSingle(Map<String, Float> names, String title, int score, String des) {
        this.names = names;
        this.score = score;
        this.des = des;
        this.title = title;
    }

    public Map<String, Float> getNames() {
        return names;
    }

    public void setNames(Map<String, Float> names) {
        this.names = names;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getTitleColor() {
        return titleColor;
    }

    public void setTitleColor(int r, int g, int b) {

        this.titleColor =  Color.rgb(r,g,b);
    }

    public int getWebColor() {
        return webColor;
    }

    public void setWebColor(int r, int g, int b) {
        this.webColor = Color.rgb(r,g,b);
    }

    public int getFillColor() {
        return fillColor;
    }

    public void setFillColor(int r, int g, int b) {
        this.fillColor = Color.rgb(r,g,b);
    }
}
