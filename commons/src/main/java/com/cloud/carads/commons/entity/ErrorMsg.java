/**
 *
 */
package com.cloud.carads.commons.entity;


import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.sf.json.JSONObject;

/**
 * @author Barrie
 */
public class ErrorMsg extends BaseEntity {
    private Integer errcode;
    private String errmsg;
    private Object data;

    public ErrorMsg() {
    }

    public ErrorMsg(Integer errcode, String errmsg) {
        this.errcode = errcode;
        this.errmsg = errmsg;
    }

    public ErrorMsg(Integer errcode, String errmsg, Object data) {
        this.errcode = errcode;
        this.errmsg = errmsg;
        this.data = data;
    }

    public Integer getErrcode() {
        return errcode;
    }

    public void setErrcode(Integer errcode) {
        this.errcode = errcode;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }

    public Object getData() {
        if (this.data instanceof JsonObject) {
            return JSONObject.fromObject(this.data.toString());
        } else if (this.data instanceof JsonArray) {
            return JSONObject.fromObject(this.data.toString());
        } else {
            return this.data;
        }

    }

    public void setData(Object data) {
        this.data = data;
    }
}
