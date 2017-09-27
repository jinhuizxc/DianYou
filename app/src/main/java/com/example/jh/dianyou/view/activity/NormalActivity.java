package com.example.jh.dianyou.view.activity;


import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.jh.base.NormalDiActivity;
import com.example.jh.dianyou.R;

/**
 * Created by Administrator on 2017/9/26.
 */

public abstract class NormalActivity extends NormalDiActivity {

    TextView title;
    ImageView back;

    // 抽象方法
    protected abstract String getToolbarTitle();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 初始化toolbar
        initToolbar();
    }

    private void initToolbar() {
        Typeface typeFace = Typeface.createFromAsset(getAssets(), "fonts/mini.TTF");
        title = (TextView) findViewById(R.id.tv_title);
        back = (ImageView) findViewById(R.id.iv_back);
        if(title==null) {

        }else {
            title.setText(getToolbarTitle());
            title.setTypeface(typeFace);
        }
        if(back==null) {
        }else {
            back.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onBackPressed();
                }
            });
        }
    }

    @Override
    protected void initializeDi() {

    }
}
