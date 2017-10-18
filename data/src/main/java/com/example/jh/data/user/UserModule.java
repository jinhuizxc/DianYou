/**
 * Copyright (C) 2015 Fernando Cejas Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.jh.data.user;



import com.example.jh.data.PerActivity;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

/**
 * Dagger module that provides user related collaborators.
 */
@Module
public class UserModule {

  @PerActivity
  @Provides
  UserApi provideUserApi(final Retrofit retrofit) {
    return retrofit.create(UserApi.class);
  }

//  @PerActivity
//  @Provides
//  UserDelegate provideUserDelegate(final UserDelegateImpl userDelegate) {
//    return userDelegate;
//  }
}