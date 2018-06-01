package com.cloud.carads.management.entity;

import io.swagger.annotations.ApiModel;

@ApiModel(description = "车主信息表,包含验证码字段")
public class CAccountInfoDto extends CAccountInfo {
    private String shortCode;

    public String getShortCode() {
        return shortCode;
    }

    public void setShortCode(String shortCode) {
        this.shortCode = shortCode;
    }
}
