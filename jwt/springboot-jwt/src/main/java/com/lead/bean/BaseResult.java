package com.lead.bean;

/**
 * @author mac
 */
public class BaseResult<T> {

    private Integer code;
    private String msg;
    private T data;


    public BaseResult(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public BaseResult() {
    }

    public static <T> BaseResult success(T data) {
        BaseResult baseResult = new BaseResult();
        baseResult.setCode(ResultCode.SUCCESS.getCode());
        baseResult.setMsg(ResultCode.SUCCESS.getMessage());
        baseResult.setData(data);
        return baseResult;
    }

    public static <T> BaseResult success(String msg) {
        BaseResult baseResult = new BaseResult();
        baseResult.setCode(ResultCode.SUCCESS.getCode());
        baseResult.setMsg(msg);
        return baseResult;
    }


    public static <T> BaseResult success() {
        BaseResult baseResult = new BaseResult();
        baseResult.setCode(ResultCode.SUCCESS.getCode());
        baseResult.setMsg(ResultCode.SUCCESS.getMessage());
        return baseResult;
    }

    public static <T> BaseResult error(T data) {
        BaseResult baseResult = new BaseResult();
        baseResult.setCode(ResultCode.FAILED.getCode());
        baseResult.setMsg(ResultCode.FAILED.getMessage());
        baseResult.setData(data);
        return baseResult;
    }

    /**
     * 返回错误信息
     *
     * @param msg
     * @param <T>
     * @return
     */
    public static <T> BaseResult getException(String msg) {
        BaseResult baseResult = new BaseResult();
        baseResult.setData(null);
        baseResult.setMsg(msg);
        baseResult.setCode(500);
        return baseResult;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
