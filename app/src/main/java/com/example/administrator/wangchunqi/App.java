package com.example.administrator.wangchunqi;

import android.app.Application;

import com.example.administrator.wangchunqi.gen.DaoMaster;
import com.example.administrator.wangchunqi.gen.DaoSession;
import com.example.administrator.wangchunqi.gen.UserDao;


/**
 * Created by Administrator on 2018/1/15/015.
 */

public class App  extends Application {
    public static UserDao userDao;

    @Override
    public void onCreate() {
        super.onCreate();
        DaoMaster.DevOpenHelper devOpenHelper = new DaoMaster.DevOpenHelper(this, "lenvess.db", null);
        DaoMaster daoMaster = new DaoMaster(devOpenHelper.getWritableDb());
        DaoSession daoSession = daoMaster.newSession();
        userDao = daoSession.getUserDao();
    }
}
