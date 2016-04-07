package org.iseage.ito.model;

public class User {

    private long userID;
    private String username;
    private String password;
    private String email;
    private int access;

    public User(long userID, String username, String password, String email, int access) {
        this.userID = userID;
        this.username = username;
        this.password = password;
        this.email = email;
        this.access = access;
    }

    public User(String username, String password, String email) {
        super();
        this.userID = -1;
        this.username = username;
        this.password = password;
        this.email = email;
        this.access = 0;
    }

    public User(String username, String password) {
        super();
        this.userID = -1;
        this.username = username;
        this.password = password;
        this.access = 0;
    }

    public User() {}

    public long getUserID() {
        return userID;
    }

    public void setUserID(long userID) {
        this.userID = userID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getAccess() {
        return access;
    }

    public void setAccess(int access) {
        this.access = access;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
