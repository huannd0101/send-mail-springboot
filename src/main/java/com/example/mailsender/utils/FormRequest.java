package com.example.mailsender.utils;

import org.springframework.web.multipart.MultipartFile;

public class FormRequest {
    private String subject;
    private String content;
    private String mailTo;
    private MultipartFile []files;

    public FormRequest() {

    }
    public FormRequest(String subject, String content, String mailTo, MultipartFile[] files) {
        this.subject = subject;
        this.content = content;
        this.mailTo = mailTo;
        this.files = files;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getMailTo() {
        return mailTo;
    }

    public void setMailTo(String mailTo) {
        this.mailTo = mailTo;
    }

    public MultipartFile[] getFiles() {
        return files;
    }

    public void setFiles(MultipartFile[] files) {
        this.files = files;
    }
}
