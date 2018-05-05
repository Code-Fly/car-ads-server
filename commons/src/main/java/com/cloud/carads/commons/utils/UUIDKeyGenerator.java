package com.cloud.carads.commons.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class UUIDKeyGenerator {

    /**
     * @Title: getUUID
     * @Description: 获得一个UUID Key
     * @param: @return
     * @return: String   返回类型
     * @throws:
     */
    public static String getUUID() {
        String s = UUID.randomUUID().toString();
        // 去掉“-”符号
        return s.substring(0, 8) + s.substring(9, 13) + s.substring(14, 18)
                + s.substring(19, 23) + s.substring(24);
    }

    /**
     * @Title: getUUID
     * @Description: 一次获取多个UUID 主键值
     * @param: @param number 需要获得的UUID数量
     * @param: @return
     * @return: String[]   返回类型
     * @throws:
     */
    public static String[] getUUID(int number) {
        if (number < 1) {
            return null;
        }
        String[] ss = new String[number];
        for (int i = 0; i < number; i++) {
            ss[i] = getUUID();
        }
        return ss;
    }

    public static void main(String[] args) {
//		String[] ss = getUUID(10);
//		for (int i = 0; i < ss.length; i++) {
//			System.out.println(ss[i] + " " + ss[i].length());
//		}
    }

    public static String getTimeStmp() {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        return sdf.format(date);
    }
}
