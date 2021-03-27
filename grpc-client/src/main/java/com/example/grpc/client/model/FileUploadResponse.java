package com.example.grpc.client.model;

public class FileUploadResponse{
    private String fileName;
    private String contentType;
    private String message;

    public FileUploadResponse(String fn, String ct, String msg){
        this.fileName = fn;
        this.contentType = ct;
        this.message = msg;;
    }

    public String getFileName(){
        return this.fileName;
    }
    public String getContentType(){
        return this.contentType;
    }
    public String getMessage(){
        return this.message;
    }
}