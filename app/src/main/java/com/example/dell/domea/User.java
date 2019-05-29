package com.example.dell.domea;

/**
 * Created by dell on 2019/5/29.
 */

public class User {
    private String url;
    private String desc;

    public User(String url, String desc) {
        this.url = url;
        this.desc = desc;
    }

    public User() {
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public String toString() {
        return "User{" +
                "url='" + url + '\'' +
                ", desc='" + desc + '\'' +
                '}';
    }
}
