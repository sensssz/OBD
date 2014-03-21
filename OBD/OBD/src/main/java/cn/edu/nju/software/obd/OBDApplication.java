package cn.edu.nju.software.obd;

import android.app.Application;

import cn.jpush.android.api.JPushInterface;

/**
 * Created by 家敏 on 14-3-21.
 * Initialize JPush at the creation of this application
 */
public class OBDApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        JPushInterface.setDebugMode(true);
        JPushInterface.init(this);
    }
}
