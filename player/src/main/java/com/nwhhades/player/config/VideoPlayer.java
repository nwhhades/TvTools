package com.nwhhades.player.config;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class VideoPlayer implements Serializable {

    private static final long serialVersionUID = -809698399459231364L;

    private String name;
    private String className;

    public VideoPlayer(String name, String className) {
        this.name = name;
        this.className = className;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    @NonNull
    @Override
    public String toString() {
        return "VideoPlayer{" +
                "name='" + name + '\'' +
                ", className='" + className + '\'' +
                '}';
    }

}
