package com.example.jh.dianyou.view.activity;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.jh.base.YaMvpDiActivity;
import com.example.jh.base.YaPresenter;
import com.example.jh.base.YaView;
import com.example.jh.data.MessageEvent;
import com.example.jh.dianyou.AndroidApplication;
import com.example.jh.dianyou.R;
import com.example.jh.dianyou.di.components.ApplicationComponent;
import com.example.jh.dianyou.di.modules.ActivityModule;
import com.umeng.analytics.MobclickAgent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;


/**
 * Base {@link Activity} class for every Activity in this application.
 */
public abstract class BaseActivity<V extends YaView, P extends YaPresenter<V>, C> extends YaMvpDiActivity<V, P, C> {


    TextView title;
    ImageView back;

    protected abstract String getToolbarTitle();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initToolbar();
    }


    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        EventBus.getDefault().unregister(this);
        super.onStop();
    }

    protected void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }

    protected void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(MessageEvent event) {
        System.out.println("=======");
//        Intent intent = new Intent(this, LoginActivity.class);
//        startActivity(intent);
    }


    public ApplicationComponent getApplicationComponent() {
        return ((AndroidApplication) getApplication()).getApplicationComponent();
    }


    public ActivityModule getActivityModule() {
        return new ActivityModule(this);
    }

    public void initToolbar() {
        Typeface typeFace = Typeface.createFromAsset(getAssets(), "fonts/mini.TTF");
        title = (TextView) findViewById(R.id.tv_title);

        back = (ImageView) findViewById(R.id.iv_back);
        if (title == null) {

        } else {
            title.setText(getToolbarTitle());
            title.setTypeface(typeFace);
        }
        if (back == null) {

        } else {
            back.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onBackPressed();
                }
            });
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }


}
