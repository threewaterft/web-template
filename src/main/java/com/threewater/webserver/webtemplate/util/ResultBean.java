package com.threewater.webserver.webtemplate.util;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class ResultBean implements Serializable {

    private static final long serialVersionUID = 8034599831078646960L;
    @JsonProperty("status")
    private String status; //00表示成功
    @JsonProperty("code")
    private String code;   //0表示成功
    @JsonProperty("msg")
    private String msg;   //错误描述
    @JsonProperty("data")
    private Object data;      //返回对象

    public ResultBean() {}

    public ResultBean(String status, String code, String msg, String data){
        this.status = status;
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static ResultBean getDefaultSuccessRes(){
        return new ResultBean("00", "0", "", "");
    }

    public static ResultBean getDefaultFailRes(){
        return getFailRes("01", "-1", "系统错误！");
    }

    public static ResultBean getSuccessRes(String data){
        return new ResultBean("00", "0", "", data);
    }

    public static ResultBean getFailRes(String status, String code, String msg){
        return new ResultBean(status, code, msg, "");
    }

    @Override
    public String toString() {
        return "ResultBean{" +
                "status='" + status + '\'' +
                ", code='" + code + '\'' +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }

    @JsonIgnore
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    @JsonIgnore
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
    @JsonIgnore
    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
    @JsonIgnore
    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
