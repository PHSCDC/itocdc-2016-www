package org.iseage.ito.model;

public class ActiveSession {

    private long id;
    private String ip;

    public ActiveSession(long id, String ip) {
        this.id = id;
        this.ip = ip;
    }

    public ActiveSession() {}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

}

