package com.tx.tx_11_29.taskframework.enums;

/**
 * @ClassName: ResultEnum
 * @Auther: admin
 * @Date: 2020/1/15 11:10
 * @Description:
 */
public enum ResultEnum {
    SUCCESS(0, "执行成功"),
    FAILED(1, "执行失败"),
    EXCEPTION(2, "异常")
    ;

    private int code;
    private String message;

    ResultEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }}
