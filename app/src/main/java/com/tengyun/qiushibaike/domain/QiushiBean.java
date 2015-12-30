package com.tengyun.qiushibaike.domain;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Administrator on 2015/12/29.
 */
public class QiushiBean {
    private long userId;
    private String userIcon;
    private String userName;
    private String content;
    private String image;
    private String format;
    private String videoUrl;

    public QiushiBean(JSONObject object) throws JSONException{
        if (!object.isNull("user")) {
            userIcon = object.getJSONObject("user").getString("icon");
            userName = object.getJSONObject("user").getString("login");
            userId = object.getJSONObject("user").getLong("id");
        }
        this.content = object.getString("content");
        this.format = object.getString("format");
        if (!object.isNull("image")) {
            image = object.getString("image");
        }
        if (!object.isNull("pic_url")){
            videoUrl = object.getString("pic_url");
        }
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getUserIcon() {
        return userIcon;
    }

    public void setUserIcon(String userIcon) {
        this.userIcon = userIcon;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }
}
