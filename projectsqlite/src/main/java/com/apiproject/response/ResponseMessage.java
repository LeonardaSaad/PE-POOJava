package com.apiproject.response;

public class ResponseMessage {
    private boolean status;
    private String message;

    public ResponseMessage(boolean status, String message) {
        this.status = status;
        this.message = message;
    }

    // Getters e Setters
    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
