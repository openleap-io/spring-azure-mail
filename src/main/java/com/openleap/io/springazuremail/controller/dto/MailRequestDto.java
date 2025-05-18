package com.openleap.io.springazuremail.controller.dto;

public class MailRequestDto {
    String senderId;
    String recipientList;
    String subject;
    String body;

    public String getSenderId() {
        return senderId;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }

    public String getRecipientList() {
        return recipientList;
    }

    public void setRecipientList(String recipientList) {
        this.recipientList = recipientList;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
