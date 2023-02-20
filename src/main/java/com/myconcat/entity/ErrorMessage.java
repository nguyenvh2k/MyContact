package com.myconcat.entity;

public class ErrorMessage {
    private int satusCode;
    private String message;

    public ErrorMessage() {
    }

    public ErrorMessage(int satusCode, String message) {
        this.satusCode = satusCode;
        this.message = message;
    }

    public int getSatusCode() {
        return satusCode;
    }

    public void setSatusCode(int satusCode) {
        this.satusCode = satusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
