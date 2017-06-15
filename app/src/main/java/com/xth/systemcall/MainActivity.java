package com.xth.systemcall;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.SimpleAdapter;

import com.xth.systemcall.base.BaseActivity;
import com.xth.systemcall.base.LogUtil;
import com.xth.systemcall.customview.XGridView;
import com.xth.systemcall.db.AppData;
import com.xth.systemcall.db.DbDeal;
import com.xth.systemcall.hardware.Light;
import com.xth.systemcall.hardware.Touch;
import com.xth.systemcall.utils.Constant;
import com.xth.systemcall.utils.ShareRef;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends BaseActivity implements XGridView.OnItemChangeListener, AdapterView.OnItemClickListener, View.OnTouchListener {
    private XGridView xGridView;
    private LinearLayout linearLayout;
    private List<HashMap<String, Object>> dataSourceList;
    private SimpleAdapter adapter;
    private DbDeal dbDeal;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();
        initHardWare();
        initUI();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private void initHardWare(){
        Light.getInstance(this).turnOnFlashLight();
    }
    private void initUI() {
        linearLayout = (LinearLayout) findViewById(R.id.main_view);
        xGridView = (XGridView) findViewById(R.id.x_grid_view);

        xGridView.setAdapter(adapter);
        linearLayout.setOnTouchListener(this);
        xGridView.setOnItemChangeListener(this);
        xGridView.setOnItemClickListener(this);
    }

    private void initData() {
        dataSourceList = new ArrayList<>();
        dbDeal = new DbDeal();
        dataSourceList.add(addHashMap(0));
        dataSourceList.add(addHashMap(1));
        adapter = new SimpleAdapter(this, dataSourceList, R.layout.item_grid_view
                , new String[]{Constant.PICTURE, Constant.TEXT}, new int[]{R.id.item_imv, R.id.item_tv});
    }
    private HashMap addHashMap(int position){
        HashMap<String, Object> itemMap = new HashMap<>();
        AppData appData = dbDeal.searchName(position);
        itemMap.put(Constant.PICTURE, appData.getImage());
        itemMap.put(Constant.TEXT, appData.getName());
        return itemMap;
    }


    @Override
    public void onChange(int from, int to) {
        LogUtil.v("from:"+from+"--to:"+to);
        HashMap<String, Object> temp = dataSourceList.get(from);
        //直接交互
        //Collections.swap(dataSourceList,from,to);

        //非直接交互 这里的处理需要注意下 排序交换
        if (from < to) {
            for (int i = from; i < to; i++) {
                Collections.swap(dataSourceList, i, i + 1);
            }
        } else if (from > to) {
            for (int i = from; i > to; i--) {
                Collections.swap(dataSourceList, i, i - 1);
            }
        }
        dataSourceList.set(to, temp);
        dbDeal.positionSwap(from,to);
        adapter.notifyDataSetChanged();
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        LogUtil.v("position:" + position + "-id:" + id);
        dataSourceList.get(position).get();

    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        Touch.getInstance().touchEvent(event);
        if(Touch.getInstance().getResult() == Touch.TWO_TO_FAR){
            finish();
        }
        return true;
    }

}
