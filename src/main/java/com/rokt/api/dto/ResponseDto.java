package com.rokt.api.dto;

import java.util.Objects;

public class ResponseDto {

    private String eventTime;
    private String email;
    private String sessionId;

    public void setEventTime(String eventTime) {
        this.eventTime = eventTime;
    }

    public String getEventTime() {
        return eventTime;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ResponseDto that = (ResponseDto) o;
        return Objects.equals(eventTime, that.eventTime) && Objects.equals(email, that.email) && Objects.equals(sessionId, that.sessionId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(eventTime, email, sessionId);
    }
}
