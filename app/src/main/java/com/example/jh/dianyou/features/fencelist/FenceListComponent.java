package com.example.jh.dianyou.features.fencelist;



import com.example.jh.data.PerActivity;
import com.example.jh.data.fence.FenceModule;
import com.example.jh.dianyou.di.modules.ActivityModule;

import dagger.Subcomponent;

/**
 * Created by flamingo on 2017/4/18.
 */
@PerActivity
@Subcomponent(modules = {ActivityModule.class, FenceModule.class})
public interface FenceListComponent {
    void inject(FenceListActivity fenceListActivity);
}
