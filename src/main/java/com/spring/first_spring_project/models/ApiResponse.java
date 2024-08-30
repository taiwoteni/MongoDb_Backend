package com.spring.first_spring_project.models;

import com.google.gson.Gson;

public class ApiResponse {
    private String message;
    private Object data;

    public ApiResponse(){}

    public ApiResponse(String message, Student data) {
        this.message = message;
        this.data = data;
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

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
