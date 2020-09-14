package com.lead.bean;

/**
 * @author mac
 */
public enum ResultCode {
    /**
     * 成功
     */
    SUCCESS(200, "正常"),
    /**
     * 路径出错
     */
    FAILED(404, "路径出错");

    /**
     * @param code
     * @param message
     */
    ResultCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    ResultCode() {
    }


    private Integer code;

    private String message;

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
