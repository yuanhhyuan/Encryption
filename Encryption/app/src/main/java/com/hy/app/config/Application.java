/*
 * Copyright 2009 Cedric Priscal
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License. 
 */

package com.hy.app.config;

import android.content.Context;

/**
 * Application class
 *
 * @author huangyuan
 * @data 2017/12/20
 */
public class Application extends android.app.Application {

    /**
     * 给组件提供应用运行过程中的
     */
    public static Context mContext;

    public static Context getContext() {
        return mContext;
    }

    /**
     * 重写Application生命周期
     */
    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;

    }


}
