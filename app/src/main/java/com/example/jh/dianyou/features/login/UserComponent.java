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
package com.example.jh.dianyou.features.login;


import com.example.jh.data.PerActivity;
import com.example.jh.data.user.UserModule;
import com.example.jh.dianyou.di.modules.ActivityModule;

import dagger.Subcomponent;


@PerActivity
@Subcomponent(modules = {ActivityModule.class, UserModule.class})
public interface UserComponent {
  void inject(LoginActivity loginActivity);
//  void inject(RegisterActivity registerActivity);
//  void inject(ForgetPasswordActivity forgetPasswordActivity);
//  void inject(ResetPasswordActivity resetPasswordActivity);
}
