package com.cloud.carads.commons.utils;

/**
 * Created by barrie on 2017/7/1.
 */
public class SSLCert {
    private String path;
    private String password;

    public SSLCert() {
    }

    public SSLCert(String path, String password) {
        this.path = path;
        this.password = password;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
