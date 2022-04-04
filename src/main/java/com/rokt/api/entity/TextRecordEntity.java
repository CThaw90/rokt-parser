package com.rokt.api.entity;

import java.time.LocalDateTime;

public class TextRecordEntity {

    private LocalDateTime date;
    private String email;
    private String sessionId;

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getSessionId() {
        return sessionId;
    }
}
