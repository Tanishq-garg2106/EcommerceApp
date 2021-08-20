package com.tanishq.ecommerce;

public class SliderModel {

    private int poster;
    private String backGroundColor;

    public SliderModel(int poster , String backGroundColor) {
        this.poster = poster;
        this.backGroundColor = backGroundColor;
    }
    public int getPoster() {
        return poster;
    }

    public void setPoster(int poster) {
        this.poster = poster;
    }


    public String getBackGroundColor() {
        return backGroundColor;
    }

    public void setBackGroundColor(String backGroundColor) {
        this.backGroundColor = backGroundColor;
    }


}
