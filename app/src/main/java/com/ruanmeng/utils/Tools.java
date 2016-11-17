package com.ruanmeng.utils;

import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore.Images.ImageColumns;
import android.util.Base64;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Tools {


    /**
     * 检查是否存在SDCard
     *
     * @return
     */
    public static boolean hasSdcard() {
        String state = Environment.getExternalStorageState();
        if (state.equals(Environment.MEDIA_MOUNTED)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 以数据流的方式将Resources下的图片显示，
     * 防止内存溢出
     */
    @SuppressWarnings("deprecation")
    public static void getImgFromSD(Context context, ImageView iv, int resID) {
        BitmapFactory.Options opt = new BitmapFactory.Options();
        opt.inPreferredConfig = Bitmap.Config.RGB_565;
        opt.inPurgeable = true;
        opt.inInputShareable = true;
        InputStream is = context.getResources().openRawResource(resID);
        Bitmap bm = BitmapFactory.decodeStream(is, null, opt);
        BitmapDrawable bd = new BitmapDrawable(context.getResources(), bm);
        iv.setBackgroundDrawable(bd);
    }

    public static String getRealFilePath(final Context context, final Uri uri) {
        if (null == uri) return null;
        final String scheme = uri.getScheme();
        String data = null;
        if (scheme == null)
            data = uri.getPath();
        else if (ContentResolver.SCHEME_FILE.equals(scheme)) {
            data = uri.getPath();
        } else if (ContentResolver.SCHEME_CONTENT.equals(scheme)) {
            Cursor cursor = context.getContentResolver().query(uri, new String[]{ImageColumns.DATA}, null, null, null);
            if (null != cursor) {
                if (cursor.moveToFirst()) {
                    int index = cursor.getColumnIndex(ImageColumns.DATA);
                    if (index > -1) {
                        data = cursor.getString(index);
                    }
                }
                cursor.close();
            }
        }
        return data;
    }

    /**
     * 获取版本名称
     *
     * @param context
     * @return String
     */
    public static String getVersion(Context context) {
        String version = "";
        try {
            PackageManager manager = context.getPackageManager();
            PackageInfo info = manager.getPackageInfo(context.getPackageName(), 0);
            version = info.versionName;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return version;
    }

    /**
     * 获取软件版本号
     *
     * @param context
     * @return
     */
    public static int getVerCode(Context context) {
        int verCode = -1;
        try {
            PackageManager manager = context.getPackageManager();
            PackageInfo info = manager.getPackageInfo(context.getPackageName(), 0);
            verCode = info.versionCode;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return verCode;
    }

    /**
     * 根据包名启动一个App
     *
     * @param @param context
     * @param @param packagename    参数
     */
    public static void openAppWithPackageName(Context context, String packagename) {
        // 通过包名获取此APP详细信息，包括Activities、services、versioncode、name等等
        PackageInfo packageinfo = null;
        try {
            packageinfo = context.getPackageManager().getPackageInfo(packagename, 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (packageinfo == null) return;

        // 创建一个类别为CATEGORY_LAUNCHER的该包名的Intent
        Intent resolveIntent = new Intent(Intent.ACTION_MAIN, null);
        resolveIntent.addCategory(Intent.CATEGORY_LAUNCHER);
        resolveIntent.setPackage(packageinfo.packageName);

        // 通过getPackageManager()的queryIntentActivities方法遍历
        List<ResolveInfo> resolveinfoList = context.getPackageManager()
                .queryIntentActivities(resolveIntent, 0);

        ResolveInfo resolveinfo = resolveinfoList.iterator().next();
        if (resolveinfo != null) {
            // packagename = 参数packname
            String packageName = resolveinfo.activityInfo.packageName;
            // 这个就是我们要找的该APP的LAUNCHER的Activity[组织形式：packagename.mainActivityname]
            String className = resolveinfo.activityInfo.name;
            // LAUNCHER Intent
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_LAUNCHER);
            // 设置ComponentName参数1:packagename参数2:MainActivity路径
            ComponentName cn = new ComponentName(packageName, className);
            intent.setComponent(cn);
            context.startActivity(intent);
        }
    }

    /**
     * 根据包名判断是否有安装该App
     *
     * @param @param context
     * @param @param packageName
     */
    public static boolean isAvilible(Context context, String packageName) {
        PackageManager packageManager = context.getPackageManager();

        List<PackageInfo> pinfo = packageManager.getInstalledPackages(0);
        for (int i = 0; i < pinfo.size(); i++) {
            if (((PackageInfo) pinfo.get(i)).packageName.equalsIgnoreCase(packageName))
                return true;
        }
        return false;
    }

    /**
     * @param @param bitmap
     * @return String 返回类型
     * @Description: 图片转字符串
     */
    public static String bitmapToBase64(Bitmap bitmap) {
        String result = null;
        ByteArrayOutputStream baos = null;
        try {
            if (bitmap != null) {
                baos = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 80, baos);
                baos.flush();
                baos.close();
                byte[] bitmapBytes = baos.toByteArray();
                result = Base64.encodeToString(bitmapBytes, Base64.DEFAULT);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (baos != null) try {
                baos.close();
            } catch (Exception e) {
            }
        }
        return result;
    }

    /**
     * @param @param bitmap
     * @return String 返回类型
     * @Description: 图片转字符串
     */
    public static String bitmapToBase64(Bitmap bitmap, int quality) {
        String result = null;
        ByteArrayOutputStream baos = null;
        try {
            if (bitmap != null) {
                baos = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, quality, baos);
                baos.flush();
                baos.close();
                byte[] bitmapBytes = baos.toByteArray();
                result = Base64.encodeToString(bitmapBytes, Base64.DEFAULT);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (baos != null) try {
                baos.close();
            } catch (Exception e) {
            }
            if (bitmap != null) bitmap.recycle();
        }
        return result;
    }

    public static String bitmapToBase64(String path) {
        String result = null;
        ByteArrayOutputStream baos = null;
        Bitmap bitmap = null;
        try {
            bitmap = BitmapFactory.decodeFile(path);
            if (bitmap != null) {
                baos = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
                Log.i("toByteArray", baos.toByteArray().length + "");
                int options = 100;
                //循环判断如果压缩后图片是否大于100kb,大于继续压缩
                while ( baos.toByteArray().length / 1024 > 100) {
                    Log.i("toByteArray", baos.toByteArray().length + "");
                    //重置baos即清空baos
                    baos.reset();
                    //每次都减少10
                    options -= 10;
                    bitmap.compress(Bitmap.CompressFormat.JPEG, options, baos);
                }
                byte[] bitmapBytes = baos.toByteArray();
                result = Base64.encodeToString(bitmapBytes, Base64.DEFAULT);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (baos != null) try {
                baos.flush();
                baos.close();
            } catch (Exception e) {
            }
            if (bitmap != null) bitmap.recycle();
        }
        return result;
    }

    /**
     * @param @param path
     * @return String 返回类型
     * @Description: 根据图片路径转字符串
     */
    public static String bitmapToBase64(String path, int quality) {
        String result = null;
        ByteArrayOutputStream baos = null;
        Bitmap bitmap = null;
        try {
            bitmap = BitmapFactory.decodeFile(path);
            if (bitmap != null) {
                baos = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, quality, baos);
                byte[] bitmapBytes = baos.toByteArray();
                result = Base64.encodeToString(bitmapBytes, Base64.DEFAULT);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (baos != null) try {
                baos.flush();
                baos.close();
            } catch (Exception e) {
            }
            if (bitmap != null) bitmap.recycle();
        }
        return result;
    }

    /**
     * @param @param path
     * @return String 返回类型
     * @Description: 将文件转为String对象
     */
    public static String readStream(String path) {
        InputStream inStream = null;
        ByteArrayOutputStream outStream = null;
        String mImage = null;
        try {
            File file = new File(path);
            inStream = new FileInputStream(file);
            byte[] buffer = new byte[1024];
            int len = -1;
            outStream = new ByteArrayOutputStream();
            try {
                while ((len = inStream.read(buffer)) != -1)
                    outStream.write(buffer, 0, len);
            } catch (IOException e) {
                e.printStackTrace();
            }
            byte[] data = outStream.toByteArray();
            mImage = Base64.encodeToString(data, Base64.DEFAULT);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (outStream != null) try {
                outStream.close();
            } catch (Exception e) {
            }
            if (inStream != null) try {
                inStream.close();
            } catch (Exception e) {
            }
        }
        return mImage;
    }

    /**
     * 获取网络图片
     *
     * @param @param  urlStr
     * @param @return 参数
     * @return Bitmap    返回类型
     */
    public static Bitmap getBitmap(String urlStr) {

        Bitmap bitmap = null;

        HttpURLConnection conn = null;
        try {
            URL url = new URL(urlStr);
            conn = (HttpURLConnection) url.openConnection();
            conn.setConnectTimeout(5 * 1000);
            conn.setRequestMethod("GET");
            conn.connect();
            if (200 == conn.getResponseCode()) {
                BitmapFactory.Options opts = new BitmapFactory.Options();

                opts.inSampleSize = 1;
                InputStream is = conn.getInputStream();
                bitmap = BitmapFactory.decodeStream(is, null, opts);

            }
        } catch (Exception e) {
            e.printStackTrace();
            return bitmap;
        } finally {
            if (conn != null) conn.disconnect();
            conn = null;
        }
        return bitmap;
    }


    /*
    * 电话号码正则表达
    *
    * */


    //判断手机格式是否正确

    public boolean isMobileNO(String mobiles) {

        Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");

        Matcher m = p.matcher(mobiles);

        return m.matches();

    }



   // android 验证电话号码或者固定电话 均可

    public static boolean isPhoneNumberValid(String phoneNumber) {

        boolean isValid = false;

        String expression = "((^(13|15|18)[0-9]{9}$)|(^0[1,2]{1}\\d{1}-?\\d{8}$)|(^0[3-9] {1}\\d{2}-?\\d{7,8}$)|(^0[1,2]{1}\\d{1}-?\\d{8}-(\\d{1,4})$)|(^0[3-9]{1}\\d{2}-? \\d{7,8}-(\\d{1,4})$))";

        CharSequence inputStr = phoneNumber;

        Pattern pattern = Pattern.compile(expression);

        Matcher matcher = pattern.matcher(inputStr);

        if (matcher.matches() ) {

            isValid = true;

        }

        return isValid;

    }



    //判断email格式是否正确

    public boolean isEmail(String email) {

        String str = "^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$";

        Pattern p = Pattern.compile(str);

        Matcher m = p.matcher(email);

        return m.matches();

    }

    //判断是否全是数字

    public boolean isNumeric(String str) {

        Pattern pattern = Pattern.compile("[0-9]*");

        Matcher isNum = pattern.matcher(str);

        if (!isNum.matches()) {

            return false;

        }

        return true;

    }

/*
* 使用说明
*
* */


//    if (contentStr != null && contentStr !=""&& contentStr.length() > 0) {
//
//        if(!isNumeric(userInfoStr)&&!isEmail(userInfoStr)){
//
//            alerDialog(“手机或邮箱格式不正确”);
//
//            return;
//
//        }
//
//        if(isNumeric(userInfoStr)&&!isMobileNO(userInfoStr)){
//
//            alerDialog(“手机或邮箱格式不正确”);
//
//            return;
//
//        }





    /**
     * 检测当的网络（WLAN、3G/2G）状态
     * @param context Context
     * @return true 表示网络可用
     */
    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivity = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity != null) {
            NetworkInfo info = connectivity.getActiveNetworkInfo();
            if (info != null && info.isConnected())
            {
                // 当前网络是连接的
                if (info.getState() == NetworkInfo.State.CONNECTED)
                {
                    // 当前所连接的网络可用
                    return true;
                }
            }
        }
        return false;
    }



    /*
    *
    *获取时间
    *
    *
    * */

    //获取当月第一天 和 当前时间


    public   void getFirstDate(TextView v){
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat datef=new SimpleDateFormat("yyyy-MM-dd");
        //获取当月的第一天
        //当前月的第一天
        cal.set(GregorianCalendar.DAY_OF_MONTH, 1);
        Date beginTime=cal.getTime();
        String beginTime1=datef.format(beginTime);
        v.setText(beginTime1);
    }
    /**
     * 获取现在时间
     *
     * @return 返回短时间字符串格式yyyy-MM-dd HH:mm:ss
     */

    public void getCurrentDate(TextView v) {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = formatter.format(currentTime);
        v.setText(dateString);
    }



    }
