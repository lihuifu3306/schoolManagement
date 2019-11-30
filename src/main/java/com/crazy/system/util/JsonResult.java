package com.crazy.system.util;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import java.util.List;
import java.util.stream.Collectors;

public class JsonResult {
    private int status;
    private String message;
    private Object data;

    public static final JsonResult SUCCESS = new JsonResult(0, "ok");
    public static final JsonResult FAILURE = new JsonResult(-1, "Failure");
    public static JsonResult success(Object obj) {
        return new JsonResult(0, "ok", obj);
    }
    public static JsonResult failure(Object obj) {
        return new JsonResult(-1, "error", obj);
    }
    public static JsonResult failure(String message) {
        return new JsonResult(-1, message);
    }
    public static JsonResult BindingError(BindingResult result) {
        if (!result.hasErrors()) {
            return SUCCESS;
        }
        List<ObjectError> allErrors = result.getAllErrors();
        List<String> collect = allErrors.stream().map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.toList());
        return JsonResult.failure(collect);
    }

    public JsonResult() {
    }

    public JsonResult(int status, String message, Object data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public JsonResult(int status, String message) {
        this.status = status;
        this.message = message;
    }

    @Override
    public String toString() {
        return "JsonResult{" +
                "status=" + status +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
