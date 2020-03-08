package com.bdqn.common.utils;

/**
 * 返回值JSON结果
 */
public class JSONResult {

    //是否成功
    private Boolean success;
    //提示信息
    private String message;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public JSONResult(){

    }

    /**
     * 带参构造函数
     * @param success   是否成功
     * @param message   提示信息
     */
    public JSONResult(Boolean success, String message) {
        this.success = success;
        this.message = message;
    }
}