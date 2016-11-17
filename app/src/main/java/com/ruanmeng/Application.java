package com.ruanmeng;

import android.content.Context;
import android.graphics.Bitmap;

import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.LruMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.download.BaseImageDownloader;
import com.ruanmeng.project_model.R;
import com.ruanmeng.project_model.mycrash.CrashManager;
import com.yolanda.nohttp.Logger;
import com.yolanda.nohttp.NoHttp;

public class Application extends android.app.Application {

    private static Context mContext;
private  static  Application instance;


    public  static  synchronized  Application getInstance(){

        return instance;
    }
    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
        instance = this;

//
        CrashManager.install(this);
        NoHttp.initialize(this);
        // 开始NoHttp的调试模式, 这样就能看到请求过程和日志
        Logger.setTag("Business_Loan");
        Logger.setDebug(true);
        initImageLoader(this);
    }


    public static void initImageLoader(Context context) {
        DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.drawable.ic_launcher)
                .showImageForEmptyUri(R.drawable.ic_launcher)
                .showImageOnFail(R.drawable.ic_launcher)
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .imageScaleType(ImageScaleType.IN_SAMPLE_INT)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .build();

        // This configuration tuning is custom. You can tune every option, you may tune some of them,
        // or you can create default configuration by ImageLoaderConfiguration.createDefault(this);
        // method.
        ImageLoaderConfiguration.Builder config = new ImageLoaderConfiguration.Builder(context)
                .threadPoolSize(3) //线程池内加载的数量
                .threadPriority(Thread.NORM_PRIORITY - 2)
                .denyCacheImageMultipleSizesInMemory()
                .memoryCache(new LruMemoryCache(5 * 1024 * 1024)) //建议内存设在5-10M,可以有比较好的表现
                .memoryCacheSize(5 * 1024 * 1024)
                .diskCacheSize(50 * 1024 * 1024) // 50 MB
                .diskCacheFileNameGenerator(new Md5FileNameGenerator())
                .tasksProcessingOrder(QueueProcessingType.LIFO)
                .defaultDisplayImageOptions(defaultOptions)
                .imageDownloader(new BaseImageDownloader(context, 5 * 1000, 30 * 1000))
                .writeDebugLogs(); // Remove for release app

        // Initialize ImageLoader with configuration.
        ImageLoader.getInstance().init(config.build());
    }

    public  static  Context getContext(){

        return mContext;
    }

}
