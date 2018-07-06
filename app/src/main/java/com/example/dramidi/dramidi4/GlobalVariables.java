package com.example.dramidi.dramidi4;

import android.app.Application;

/**
 * Created by dramidi on 4/6/2017.
 */

public class GlobalVariables extends Application {


    private String shape;
    private int Height;
    private int width;

    public String getShape() {
        return shape;
    }

    public void setShape(String shape) {
        this.shape = shape;
    }
    public int getHeight() {
        return Height;
    }

    public void setHeight(int Height) {
        this.Height = Height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }
}
