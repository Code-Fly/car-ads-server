package com.cloud.carads.commons.entity;

/**
 * Created by Administrator on 2016/7/29.
 */
public abstract class Error {
    public static Integer SUCCESS = 0;

    public static Integer UNKNOW_EXCEPTION = -1;
    public static Integer SESSION_EXPIRED_EXCEPTION = 10001;
    public static Integer CLASS_NOT_FOUND_EXCEPTION = 10004;
    public static Integer SQL_EXCEPTION = 10005;
    public static Integer CONNECTION_FAILED_EXCEPTION = 10006;
    public static Integer FILE_UPLOAD_EXCEPTION = 10007;
    public static Integer IO_EXCEPTION = 10008;
    public static Integer UNSUPPORTED_ENCODING_EXCEPTION = 10009;
    public static Integer MALFORMED_URL_EXCEPTION = 10010;
    public static Integer JSON_EXCEPTION = 10011;
    public static Integer PARSE_FILE_EXCEPTION = 10013;
    public static Integer UNSUPPORTED_MIGRATION = 10014;
    public static Integer TRANSCODER_EXCEPTION = 10015;
    public static Integer PARSE_EXCEPTION = 10016;
    public static Integer SCHEDULER_EXCEPTION = 10017;
    public static Integer DATA_NOT_EXIST_EXCEPTION = 10018;
    public static Integer API_RESPONSE_EXCEPTION = 10019;
    public static Integer DUPLICATE_KEY_EXCEPTION = 10020;
    public static Integer INVALID_DATA_EXCEPTION = 10021;
    public static Integer ILLEGAL_ACCESS_EXCEPTION = 10022;
    public static Integer INSTANTIANTION_EXCEPTION = 10023;
    public static Integer NO_SUCH_FIELD_EXCEPTION = 10024;
    public static Integer TIME_OUT_EXCEPTION = 10025;


    public static Integer SMS_SEND_ERROR= 20001;
    public static Integer SMS_SHORTCODE_ERROR= 20002;
}
