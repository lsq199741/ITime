package com.itime.sign.pojo;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class JsonResult {


    private Integer code;

    private String message;

    private Object data;

    private Integer count;

    public static JsonResult build(Integer status, String msg, Object data) {

        return new JsonResult(status, msg, data);
    }


    public static JsonResult ok(Object data) {
        return new JsonResult(200, "", data);
    }


    public static JsonResult ok(String message, Object data) {
        return new JsonResult(200, message, data);
    }

    public static JsonResult ok(Object data, Integer count) {
        return new JsonResult(200, "", data, count);
    }

    public static JsonResult errorMsg(String msg) {
        return new JsonResult(500, msg, null, null);
    }

    public static JsonResult errorMap(Object data) {
        return new JsonResult(501, "error", data, null);
    }

    public static JsonResult errorTokenMsg(String msg) {
        return new JsonResult(502, msg, null, null);
    }

    public static JsonResult errorException(String msg) {
        return new JsonResult(555, msg, null, null);
    }

    public JsonResult() {

    }

    public JsonResult(Integer code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
        this.count = 0;
    }


    public JsonResult(Integer code, String message, Object data, Integer count) {
        this.code = code;
        this.message = message;
        this.data = data;
        this.count = count;
    }


}
