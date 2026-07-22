package com.example.hospital.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse<T> {
    private String status;
    private int code;
    private T data;
    private Object meta;

    public ApiResponse() {}

    public ApiResponse(int code, String status) {
        this.code = code;
        this.status = status;
    }

    public ApiResponse(int code, String status, T data) {
        this.code = code;
        this.status = status;
        this.data = data;
    }

    public ApiResponse(int code, String status, T data, Object meta) {
        this.code = code;
        this.status = status;
        this.data = data;
        this.meta = meta;
    }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public int getCode() { return code; }
    public void setCode(int code) { this.code = code; }

    public T getData() { return data; }
    public void setData(T data) { this.data = data; }

    public Object getMeta() { return meta; }
    public void setMeta(Object meta) { this.meta = meta; }
}
