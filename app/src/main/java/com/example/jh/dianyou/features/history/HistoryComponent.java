package com.example.jh.dianyou.features.history;



import com.example.jh.data.PerActivity;
import com.example.jh.dianyou.di.modules.ActivityModule;

import dagger.Subcomponent;

/**
 * Created by fflamingogo on 2016/8/1.
 */
@PerActivity
@Subcomponent(modules = {ActivityModule.class})
public interface HistoryComponent{
    void inject(HistoryActivity historyActivity);

}
