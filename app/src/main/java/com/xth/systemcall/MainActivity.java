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
import com.xth.systemcall.utils.Constant;
import com.xth.systemcall.utils.Touch;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends BaseActivity implements XGridView.OnItemChangeListener, AdapterView.OnItemClickListener, View.OnTouchListener {
    private XGridView xGridView;
    private LinearLayout linearLayout;
    private List<HashMap<String, Object>> dataSourceList;
    private SimpleAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();
        initUI();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
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
        HashMap<String, Object> itemMap = new HashMap<>();
        itemMap.put("item_image", R.drawable.gridview_add);
        itemMap.put("item_text", Constant.ADDAPP);
        dataSourceList.add(itemMap);
//        for (int i = 0; i < 30;i++){
//            HashMap<String,Object> itemMap1 = new HashMap<>();
//            itemMap1.put("item_image",R.drawable.gridview_add);
//            itemMap1.put("item_text","拖拽" + i);
//            dataSourceList.add(itemMap1);
//        }
        adapter = new SimpleAdapter(this, dataSourceList, R.layout.item_grid_view
                , new String[]{"item_image", "item_text"}, new int[]{R.id.item_imv, R.id.item_tv});
    }


    @Override
    public void onChange(int from, int to) {
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

        adapter.notifyDataSetChanged();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        LogUtil.v("position:" + position + "-id:" + id);
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
