package org.iseage.ito.model;

public class ActiveSession {

    private String id;
    private String ip;
    private long expiry;

    public ActiveSession(String id, String ip, long expiry) {
        this.id = id;
        this.ip = ip;
        this.expiry = expiry;
    }

    public ActiveSession() {}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }
    
    public long getExpiry() {
        return expiry;
    }

    public void setExpiry(long expiry) {
        this.expiry = expiry;
    }

}

