package com.xth.systemcall.db;

import com.xth.systemcall.utils.ShareRef;

import org.litepal.crud.DataSupport;

/**
 * Created by XTH on 2017/6/15.
 */

public class AppData extends DataSupport {
    private int position;
    private int image;
    private String name;
    private String state;

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
