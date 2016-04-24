package org.iseage.ito.model;

public class ActiveSession {

    private String id;
    private String ip;

    public ActiveSession(String id, String ip) {
        this.id = id;
        this.ip = ip;
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

}

