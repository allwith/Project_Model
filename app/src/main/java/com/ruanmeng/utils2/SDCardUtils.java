package com.ruanmeng.utils2;

import android.content.Context;
import android.os.Environment;
import android.os.StatFs;
import android.text.format.Formatter;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 
 */

// TODO: 2016/11/15     需要一个带内存卡的手机 测试一下 目前测试的为 无拓展卡的手机 
public class SDCardUtils {


    /**
     * 判断SD卡是否挂载
     *
     * @return
     */
    public static boolean isMounted() {
        return Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED);
    }
//
//    // 得到SD卡的 系统根路径
//    public static String getSDPath() {
//
//        if (isMounted()) {
//            return Environment.getExternalStorageDirectory().getAbsolutePath();
//        }
//        return null;
//    }

    /**
     * 获取SD卡 系统根路径
     *
     * @return
     */
    public static String getSDCardPath() {
        return Environment.getExternalStorageDirectory().getAbsolutePath()
                + File.separator;
    }

    /**
     * 获得SD卡已经使用的大小
     *
     * @return
     */
    public static String getSDTotalSize(Context comtext) {
        File path = Environment.getExternalStorageDirectory();
        StatFs stat = new StatFs(path.getPath());
        long blockSize = stat.getBlockSize();
        long totalBlocks = stat.getBlockCount();

        Log.d("--lfc 已经使用的 ","blockSize * totalBlocks  "+(double)blockSize * totalBlocks/1024/1024/1024);
        return Formatter.formatFileSize(comtext, blockSize * totalBlocks);
    }

    /**
     * 获得sd卡剩余容量，即可用大小
     *
     * @return
     */

//      准确度 比较高的一个
    public static String getSDAvailableSize(Context comtext) {
        File path = Environment.getExternalStorageDirectory();
        StatFs stat = new StatFs(path.getPath());
        long blockSize = stat.getBlockSize();
        long availableBlocks = stat.getAvailableBlocks();
        Log.d("--lfc  可用大小 ","blockSize * availableBlocks  "+(double)blockSize * availableBlocks/1024/1024/1024);
        return Formatter.formatFileSize(comtext, blockSize * availableBlocks);
    }

    public  static  String getSDCardSize(Context context){
        File path = Environment.getExternalStorageDirectory();
        StatFs stat = new StatFs(path.getPath());
        long blockSize = stat.getBlockSize();
        long totalBlocks = stat.getBlockCount();
        long availableBlocks = stat.getAvailableBlocks();
        Log.d("--lfc  总大小  "," blockSize * (availableBlocks+totalBlocks)  "+ (double)blockSize * (availableBlocks+totalBlocks)/1024/1024/1024);

        return Formatter.formatFileSize(context, blockSize * (availableBlocks+totalBlocks));
    }

//    /**
//     * 获得机身内存总大小
//     *
//     * @return
//     */
//    public static String getRomTotalSize(Context comtext) {
//        File path = Environment.getDataDirectory();
//        StatFs stat = new StatFs(path.getPath());
//        long blockSize = stat.getBlockSize();
//        long totalBlocks = stat.getBlockCount();
//        return Formatter.formatFileSize(comtext, blockSize * totalBlocks);
//    }
//
//    /**
//     * 获得机身可用内存
//     *
//     * @return
//     */
//    public static String getRomAvailableSize(Context comtext) {
//        File path = Environment.getDataDirectory();
//        StatFs stat = new StatFs(path.getPath());
//        long blockSize = stat.getBlockSize();
//        long availableBlocks = stat.getAvailableBlocks();
//        return Formatter.formatFileSize(comtext, blockSize * availableBlocks);
//    }

//    /**
//     * 获取SD卡的剩余容量 单位byte
//     *
//     * @return
//     */
//    public static String getSDCardAllSize(Context context) {
//        if (isMounted()) {
//            StatFs stat = new StatFs(getSDCardPath());
//            // 获取空闲的数据块的数量
//            long availableBlocks = (long) stat.getAvailableBlocks() - 4;
//            // 获取单个数据块的大小（byte）
//            long freeBlocks = stat.getAvailableBlocks();
////			return freeBlocks * availableBlocks;
//            return Formatter.formatFileSize(context, freeBlocks * availableBlocks);
//        }
//        return "0";
//    }

    /**
     * 获取指定路径所在空间的剩余可用容量字节数，单位byte
     *
     * @param filePath
     * @return 容量字节 SDCard可用空间，内部存储可用空间
     */
    public static long getFreeBytes(String filePath) {
        // 如果是sd卡的下的路径，则获取sd卡可用容量
        if (filePath.startsWith(getSDCardPath())) {
            filePath = getSDCardPath();
        } else {
            // 如果是内部存储的路径，则获取内存存储的可用容量
            filePath = Environment.getDataDirectory().getAbsolutePath();
        }
        StatFs stat = new StatFs(filePath);
        long availableBlocks = (long) stat.getAvailableBlocks() - 4;
        return stat.getBlockSize() * availableBlocks;
    }



    // 获取SD卡的根目录  获取到的是 系统默认的存储位置 的卡的路径
    public static String getSDCardBaseDir() {
//         StatFs sf=new StatFs("/mnt/sdcard2");  外置卡
        if (isMounted()) {
            return Environment.getExternalStorageDirectory().getAbsolutePath();
        }
        return null;
    }


    // 将文件保存到SD卡中
    public static boolean saveFileIntoSDCard(byte[] data, String path,
                                             String fileName) {

        if (isMounted()) {

            BufferedOutputStream bos = null;
            try {
                String filePath = getSDCardPath()  + path;
                File file = new File(filePath);
                if (!file.exists()) {
                    file.mkdirs();
                }

                bos = new BufferedOutputStream(new FileOutputStream(new File(
                        file, fileName)));
                bos.write(data, 0, data.length);
                bos.flush();

                return true;
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (bos != null) {
                    try {
                        bos.close();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
            }

        }

        return false;
    }

    // 从SD卡中取出存储的文件
    public static byte[] getFileFromSDCard(String filePath) {

        if (isMounted()) {
            File file = new File(filePath);
            BufferedInputStream bis = null;
            ByteArrayOutputStream baos = null;
            if (file.exists()) {
                try {
                    baos = new ByteArrayOutputStream();
                    bis = new BufferedInputStream(new FileInputStream(file));
                    int len = 0;
                    byte[] buffer = new byte[1024 * 8];
                    while ((len = bis.read(buffer)) != -1) {
                        baos.write(buffer, 0, len);
                        baos.flush();
                    }

                    return baos.toByteArray();
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (bis != null) {
                        try {
                            bis.close();
                            baos.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }

            }
        }

        return null;

    }


//    /**
//     * 根据路径获取内存状态
//     * @param
//     * @return
//     */
//    public static String getMemoryInfo(Context context)
//    {
//
//        File path = Environment.getDataDirectory();
//
//        // 获得一个磁盘状态对象
//
//        StatFs stat = new StatFs(path.getPath());
//
//        long blockSize = stat.getBlockSize();   // 获得一个扇区的大小
//
//        long totalBlocks = stat.getBlockCount();    // 获得扇区的总数
//
//        long availableBlocks = stat.getAvailableBlocks();   // 获得可用的扇区数量
//
//        // 总空间
//        String totalMemory =  Formatter.formatFileSize(context, totalBlocks * blockSize);
//        // 可用空间
//        String availableMemory = Formatter.formatFileSize(context, availableBlocks * blockSize);
//
//        return "总空间: " + totalMemory + "\n可用空间: " + availableMemory;
//    }


}
