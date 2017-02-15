package cc.shawn.zzj;

import android.app.Application;
import android.content.Context;
import android.os.Environment;

import java.io.File;

/**
 * Created by shawn on 17/2/15.
 */

public class MyApp extends Application {

    // 默认存放图片的路径
    public final static String DEFAULT_SAVE_IMAGE_PATH = Environment.getExternalStorageDirectory() + File.separator + "CircleDemo" + File.separator + "Images"
            + File.separator;

    private static Context mContext;
    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
        //LeakCanary.install(this);
//        QPManager.getInstance(getApplicationContext()).initRecord();

//        MultiDex.install(this);
    }

    public static Context getContext(){
        return mContext;
    }
}
