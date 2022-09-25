package com.example.springwebserver;

public class IpAddress{
    private String ip;
    private int countCall;

    public IpAddress(){}

    public IpAddress( String ip, int countCall){
        this.ip = ip;
        this.countCall = countCall;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public void setCountCall(int countCall) {
        this.countCall = countCall;
    }

    public String getIp() {
        return ip;
    }

    public int getCountCall() {
        return countCall;
    }
}
