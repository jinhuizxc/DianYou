/**
 * Copyright (C) 2015 Fernando Cejas Open Source Project
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.jh.dianyou.di.components;



import com.example.jh.dianyou.di.modules.ActivityModule;
import com.example.jh.dianyou.di.modules.ApplicationModule;
import com.example.jh.dianyou.di.modules.ProviderModule;
import com.example.jh.dianyou.features.adddevice.AddDeviceComponent;
import com.example.jh.dianyou.features.fencelist.FenceListComponent;
import com.example.jh.dianyou.features.fencelist.fence.FenceComponent;
import com.example.jh.dianyou.features.history.HistoryComponent;
import com.example.jh.dianyou.features.local.LocalComponent;
import com.example.jh.dianyou.features.login.UserComponent;
import com.example.jh.dianyou.features.manager.ManagerComponent;
import com.example.jh.dianyou.features.manager.disturb.DisturbComponent;
import com.example.jh.dianyou.features.message.MessageComponent;
import com.example.jh.dianyou.features.mine.my.MineComponent;
import com.example.jh.dianyou.features.talk.TalkComponent;

import javax.inject.Singleton;

import dagger.Component;

/**
 * A component whose lifetime is the life of the application.
 */
@Singleton // Constraints this component to one-per-application or unscoped bindings.
@Component(modules = {ApplicationModule.class, ProviderModule.class})
public interface ApplicationComponent {
    UserComponent userComponent(ActivityModule activityModule);

    LocalComponent localComponent(ActivityModule activityModule);

    MineComponent mineComponent(ActivityModule activityModule);

    HistoryComponent historyComponent(ActivityModule activityModule);

    FenceListComponent fenceListComponent(ActivityModule activityModule);

    FenceComponent fenceComponent(ActivityModule activityModule);

    AddDeviceComponent addDeviceComponent(ActivityModule activityModule);

    ManagerComponent managerComponent(ActivityModule activityModule);

    DisturbComponent disturbComponent(ActivityModule activityModule);

    TalkComponent talkComponent(ActivityModule activityModule);

    MessageComponent messageComponent(ActivityModule activityModule);
}
