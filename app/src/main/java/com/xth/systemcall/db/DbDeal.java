package com.xth.systemcall.db;

import com.xth.systemcall.R;
import com.xth.systemcall.utils.Constant;

import org.litepal.crud.DataSupport;

/**
 * Created by XTH on 2017/6/15.
 */

public class DbDeal {
    public DbDeal() {
        if (DataSupport.findAll(AppData.class).size() == 0) {
            setAppData(0, R.drawable.light_off, Constant.LIGHT, Constant.OFF);
            setAppData(1, R.drawable.gridview_add, Constant.ADDAPP, Constant.OFF);
        }
    }

    public void setAppData(int position, int image, String name, String state) {
        AppData appData = new AppData();
        appData.setPosition(position);
        appData.setImage(image);
        appData.setName(name);
        appData.setState(state);
        appData.save();
    }

    public AppData searchName(int position) {
        for (AppData appData : DataSupport.findAll(AppData.class)) {
            if (appData.getPosition() == position) {
                return appData;
            }
        }
        return null;
    }
    public void positionSwap(int from,int to){
        AppData appData = searchName(from);
        appData.setPosition(-1);
        appData.save();

        appData = searchName(to);
        appData.setPosition(from);
        appData.save();

        appData = searchName(-1);
        appData.setPosition(to);
        appData.save();

    }
}
