package com.ruanmeng.utils;

import android.graphics.Bitmap;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.ruanmeng.project_model.R;


/**
 * Created by 小卷毛 on 2016/5/4 0004.
 * imageloader工具类
 */
public class ImageLoader extends com.nostra13.universalimageloader.core.ImageLoader {

    public ImageLoader() {

    }

    public static void showImage(String uri, ImageView imageView) {
        //显示图片的配置
        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.drawable.not_2)        //加载图片时的图片
                .showImageForEmptyUri(R.drawable.not_2)      //没有图片资源时的默认图片
                .showImageOnFail(R.drawable.not_2)           //加载失败时的图片
                .cacheInMemory(true)                               //启用内存缓存
                .cacheOnDisk(true)                                 //启用外存缓存
                .imageScaleType(ImageScaleType.IN_SAMPLE_INT)      //设置图片以如何的编码方式显示
                .bitmapConfig(Bitmap.Config.RGB_565)                      //设置图片的解码类型
                .build();

        /**
         * "http://site.com/image.png" // from Web
         * "file:///mnt/sdcard/image.png" // from SD card
         * "file:///mnt/sdcard/video.mp4" // from SD card (video thumbnail)
         * "content://media/external/images/media/13" // from content provider
         * "content://media/external/video/media/13" // from content provider (video thumbnail)
         * "assets://image.png" // from assets
         * "drawable://" + R.drawable.img // from drawables (non-9patch images)
         */

        com.nostra13.universalimageloader.core.ImageLoader
                .getInstance()
                .displayImage(uri, imageView, options);
    }

    public static void showSDImage(String uri, ImageView imageView) {
        //显示图片的配置
        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.drawable.not_2)        //加载图片时的图片
                .showImageForEmptyUri(R.drawable.not_2)      //没有图片资源时的默认图片
                .showImageOnFail(R.drawable.not_2)           //加载失败时的图片
                .cacheInMemory(true)                               //启用内存缓存
                .cacheOnDisk(true)                                 //启用外存缓存
                .imageScaleType(ImageScaleType.IN_SAMPLE_INT)      //设置图片以如何的编码方式显示
                .bitmapConfig(Bitmap.Config.RGB_565)                      //设置图片的解码类型
                .build();

        com.nostra13.universalimageloader.core.ImageLoader
                .getInstance()
                .displayImage("file://" + uri, imageView, options);
    }

    public static void showDrawableImage(int drawable, ImageView imageView, int defaultDrawable) {
        //显示图片的配置
        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .showImageOnLoading(defaultDrawable)        //加载图片时的图片
                .showImageForEmptyUri(defaultDrawable)      //没有图片资源时的默认图片
                .showImageOnFail(defaultDrawable)           //加载失败时的图片
                .cacheInMemory(true)                               //启用内存缓存
                .cacheOnDisk(true)                                 //启用外存缓存
                .imageScaleType(ImageScaleType.IN_SAMPLE_INT)      //设置图片以如何的编码方式显示
                .bitmapConfig(Bitmap.Config.RGB_565)                      //设置图片的解码类型
                .build();

        com.nostra13.universalimageloader.core.ImageLoader
                .getInstance()
                .displayImage("drawable://" + drawable, imageView, options);
    }

    public static void showImage(String uri, ImageView imageView, int drawable) {
        //显示图片的配置
        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .showImageOnLoading(drawable)        //加载图片时的图片
                .showImageForEmptyUri(drawable)      //没有图片资源时的默认图片
                .showImageOnFail(drawable)           //加载失败时的图片
                .cacheInMemory(true)                               //启用内存缓存
                .cacheOnDisk(true)                                 //启用外存缓存
                .imageScaleType(ImageScaleType.IN_SAMPLE_INT)      //设置图片以如何的编码方式显示
                .bitmapConfig(Bitmap.Config.RGB_565)                      //设置图片的解码类型
                .build();

        /**
         * "http://site.com/image.png" // from Web
         * "file:///mnt/sdcard/image.png" // from SD card
         * "file:///mnt/sdcard/video.mp4" // from SD card (video thumbnail)
         * "content://media/external/images/media/13" // from content provider
         * "content://media/external/video/media/13" // from content provider (video thumbnail)
         * "assets://image.png" // from assets
         * "drawable://" + R.drawable.img // from drawables (non-9patch images)
         */

        com.nostra13.universalimageloader.core.ImageLoader
                .getInstance()
                .displayImage(uri, imageView, options);
    }
}
