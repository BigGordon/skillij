package com.zyc.skillijserver.dto;

/**
 * Created on 2018/7/12.
 * Author: Gordon
 * Email: biggordon@163.com
 */
public class UserPortraitDto {

    /**
     * 用户姓名
     */
    private String name;

    /**
     * 用户头像路径
     */
    private String img;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
