package com.zyc.skillijcommon.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;

/**
 * Created on 2018/2/6.
 * Author: Gordon
 * Email: biggordon@163.com
 */
public class JsonResult {
    /**
     * json响应状态码：200，500
     */
    public static final int SUCCESS = 200;
    public static final int FAIL = 500;
    /**
     * 分页数据字段
     */
    public static final String PAGE_DATA_FIELD = "rows";
    public static final String PAGE_DATA_PAGING_TOTAL_FIELD = "total";

    /**
     * json响应状态
     */
    private int status = SUCCESS;
    /**
     * json响应消息
     */
    private String msg = null;
    /**
     * json响应数据：单个对象
     */
    private JSONObject data = null;
    /**
     * json响应数据：列表
     */
    private JSONArray rows = null;
    /**
     * 记录数，适用于分页数据
     */
    private Integer total = null;

    /**
     * 序列化默认规则：
     * 为空的字段不序列化
     * 在pojo中没有get方法的字段不序列化
     * 禁用循环引用检测
     * 序列化时将Enum转换为toString()的返回值
     */
    public static SerializerFeature[] DEFAULT_SERIALIZER_FEATURES = new SerializerFeature[] {
            SerializerFeature.WriteMapNullValue,
            SerializerFeature.IgnoreNonFieldGetter,
            SerializerFeature.DisableCircularReferenceDetect,
            SerializerFeature.WriteEnumUsingToString,
    };

    private JsonResult(int status) {
        this.status = status;
    }
    private JsonResult(int status, String msg) {
        this.status = status;
        this.msg = msg;
    }
    private JsonResult(int status, JSONObject data) {
        this.status = status;
        this.data = data;
    }
    private JsonResult(int status, JSONArray jsonData, Integer total) {
        this.status = status;
        rows = jsonData;
        if (total != null) {
            this.total = total;
        }
    }

    /**
     * 简单成功消息
     * @return
     */
    public static String jsonWithSuccess(){
        JsonResult jsonResult = new JsonResult(SUCCESS);
        return getJsonObject(jsonResult).toString();
    }

    /**
     * 错误消息提示信息
     * @param msg
     * @return
     */
    public static String jsonWithErrMsg(String msg){
        JsonResult jsonResult = new JsonResult(FAIL, msg);
        return getJsonObject(jsonResult).toString();
    }

    /**
     * 单条 json 记录
     * @param data
     * @return
     */
    public static String jsonWithRecord(JSONObject data) {
        JsonResult jsonResult = new JsonResult(SUCCESS, data);
        return getJsonObject(jsonResult).toString();
    }

    /**
     * 分页 json 数据
     * @return
     */
    public static String jsonWithRecordset(JSONArray jsonData, Integer total) {
        JsonResult jsonResult = new JsonResult(SUCCESS, jsonData, total);
        return getJsonObject(jsonResult).toString();
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(JSONObject data) {
        this.data = data;
    }

    public JSONArray getRows() {
        return rows;
    }

    public void setRows(JSONArray rows) {
        this.rows = rows;
    }

    public JsonResult() {
        super();
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    private static JSONObject getJsonObject(Object o) {
        String s = JSON.toJSONString(o, DEFAULT_SERIALIZER_FEATURES);
        return JSON.parseObject(s);
    }
}
