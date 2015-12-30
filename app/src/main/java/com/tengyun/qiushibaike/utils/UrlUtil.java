package com.tengyun.qiushibaike.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Administrator on 2015/12/29.
 */
public class UrlUtil {

    // 专享
    public final static String URL_HOT_SUGGEST = "http://m2.qiushibaike.com/article/list/suggest?page=";
    // 视频
    public final static String URL_ETLITE_VIDEO = "http://m2.qiushibaike.com/article/list/video?page=";

    // 纯文
    public final static String URL_ETLITE_TEXT = "http://m2.qiushibaike.com/article/list/text?page=";

    // 纯图
    public final static String URL_ETLITE_DAY = "http://m2.qiushibaike.com/article/list/image?page=";


    // 最新
    public final static String URL_HOT_LATEST = "http://m2.qiushibaike.com/article/list/latest?page=";
    //评论
    public final static String Comment = "http://m2.qiushibaike.com/article/%d/comments?page=2";

    //头像获取(+ id去掉后4位 + "/" + id + "/thumb/" + icon图片名.jpg)
    //userIcon======http://pic.qiushibaike.com/system/avtnew/1499/14997026/thumb/20140404194843.jpg
    public final static String URL_USER_ICON="http://pic.qiushibaike.com/system/avtnew/%s/%s/thumb/%s";
    //内容图片获取(+图片名从数字开始去掉后4位+图片名从数字开始数全部+"/"+"/"+small或者medium+"/"+图片名)
    //====图片Url=http://pic.qiushibaike.com/system/pictures/7128/71288069/small/app71288069.jpg
    public final static String URL_IMAGE= "http://pic.qiushibaike.com/system/pictures/%s/%s/%s/%s";


    public static String getImageUrl(Context context, String image){
        String url = "http://pic.qiushibaike.com/system/pictures/%s/%s/%s/%s";
        Pattern pattern = Pattern.compile("(\\d+)\\d{4}");
        Matcher matcher = pattern.matcher(image);
        matcher.find();
        //TODO:检测网络是WIFI还是移动网络

        String imageType = "small";
        if (getNetworkType(context)==98){
            //移动网络
            imageType = "small";
        }else {
            imageType = "small";
        }

        return String.format(url, matcher.group(1), matcher.group(), imageType, image);
    }

    /**
     * 获取头像图片
     * @param id
     * @param icon
     * @return
     */
    public static String getIconURL(long id, String icon){
        String url = "http://pic.qiushibaike.com/system/avtnew/%s/%s/thumb/%s";
        return String.format(url, id / 10000, id, icon);
    }


    /**
     * 检测当前的网络状态
     * @param context
     * @return
     */
    public static int getNetworkType(Context context) {
        int netType = 0;
        //判断网络，获取网络状态
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        //判断网络，获取网络状态
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        //获取枚举类型的网络状态
        NetworkInfo.State state = networkInfo.getState();
        // 获取网络的类型
        int type = networkInfo.getType();
        switch (type) {
            case ConnectivityManager.TYPE_MOBILE:
                 if (state == NetworkInfo.State.CONNECTED){
                     //移动网络
                     netType = 98;
                 }
                break;
            case ConnectivityManager.TYPE_WIFI:
                if (state == NetworkInfo.State.CONNECTED){
                    //WIFI
                    netType = 99;

                }
                break;
            default:
                if (state == NetworkInfo.State.CONNECTED) {
                    //网络连接了
                }
                break;
        }

        return netType;
    }
}
