package com.tx.tx_11_29.taskframework.entity;

/**
 * <p>
 *     任务执行的结果
 * </p>
 * @ClassName: TaskResult
 * @Auther: admin
 * @Date: 2020/1/15 11:00
 * @Description:
 */
public class TaskResult<T> {

    private int code;
    private String message;
    private T data;

    public static <T> TaskResult result(int code, String message, T data) {
        return new TaskResult(code, message, data);
    }

    public static <T> TaskResult result(int code, String message) {
        return new TaskResult(code, message, null);
    }

    public TaskResult(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
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
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
