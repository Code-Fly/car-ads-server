package com.cloud.carads.commons.entity;

public enum Error {
    SUCCESS(0, "success"),
    UNKNOW_EXCEPTION(-1, "unknow exception"),
    SESSION_EXPIRED_EXCEPTION(10001, "session expired exception"),
    CLASS_NOT_FOUND_EXCEPTION(10004, "class not found exception"),
    SQL_EXCEPTION(10005, "SQL exception"),
    CONNECTION_FAILED_EXCEPTION(10006, "connection failed exception"),
    FILE_UPLOAD_EXCEPTION(10007, "file upload failed"),
    IO_EXCEPTION(10008, "IO exception"),
    UNSUPPORTED_ENCODING_EXCEPTION(10009, "unsupported encoding exception"),
    MALFORMED_URL_EXCEPTION(10010, "malformed url exception"),
    JSON_EXCEPTION(10011, "JSON exception"),
    PARSE_FILE_EXCEPTION(10013, "parse file exception"),
    UNSUPPORTED_MIGRATION(10014, "unsupported migration"),
    TRANSCODER_EXCEPTION(10015, "transcoder exception"),
    PARSE_EXCEPTION(10016, "parse exception"),
    SCHEDULER_EXCEPTION(10017, "scheduler exception"),
    DATA_NOT_EXIST_EXCEPTION(10018, "data not exist exception"),
    API_RESPONSE_EXCEPTION(10019, "api response error exception"),
    DUPLICATE_KEY_EXCEPTION(10020, "duplicate key exception"),
    INVALID_DATA_EXCEPTION(10021, "invalid data exception"),
    ILLEGAL_ACCESS_EXCEPTION(10022, "illegal access exception"),
    INSTANTIANTION_EXCEPTION(10023, "instantiantion  exception"),
    NO_SUCH_FIELD_EXCEPTION(10024, "no such field exception"),
    TIME_OUT_EXCEPTION(10025, "timeout exception"),
    AUTHORIZATION_FAILED_EXCEPTION(10026, "authorization failed exception"),

    SMS_SEND_ERROR(20001, "sms send error"),
    SMS_SHORTCODE_ERROR(20002, "sms shortcode error"),

    LOGIN_PASSWORD_ERROR(30001, "Username or password incorrect"),
    LOGIN_SYSTEM_ERROR(30002, "Login system error"),
    USER_NOT_FOUND_ERROR(30003, "Username incorrect"),
    USER_ALREADY_EXIST_ERROR(30004, "User already exists"),

    C_NOT_EXITS(40001, "c not exists"),
    C_FLAG_ERROR(40002, "c flag error");


    private final int value;
    private final String reasonPhrase;

    Error(int value, String reasonPhrase) {
        this.value = value;
        this.reasonPhrase = reasonPhrase;
    }

    public int getValue() {
        return value;
    }

    public String getReasonPhrase() {
        return reasonPhrase;
    }
}
