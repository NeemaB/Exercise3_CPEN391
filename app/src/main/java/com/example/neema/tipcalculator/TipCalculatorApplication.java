package com.example.neema.tipcalculator;

import android.app.Application;

import com.codekrypt.greendao.db.DaoMaster;
import com.codekrypt.greendao.db.DaoSession;
import com.joanzapata.iconify.Iconify;
import com.joanzapata.iconify.fonts.FontAwesomeModule;

import org.greenrobot.greendao.database.Database;

/**
 * Created by neema on 2017-02-28.
 */
public class TipCalculatorApplication extends Application {

    private static DaoSession daoSession;

    @Override
    public void onCreate() {
        super.onCreate();

        Iconify.
                with(new FontAwesomeModule());


        // do this once, for example in your Application class
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "notes-db", null);
        Database db = helper.getWritableDb();
        DaoMaster daoMaster = new DaoMaster(db);
        daoSession = daoMaster.newSession();

    }

    public static DaoSession getDaoSession(){
        return daoSession;
    }
}
