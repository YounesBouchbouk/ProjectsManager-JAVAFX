package com.example.demo.models;

public final  class UserSession {

    public static UserSession instance;

    private String userName;
    private int ID;

    private UserSession(String userName, int id) {
        this.userName = userName;
        this.ID = id;
    }

    public static UserSession getInstace(String userName, int id) {
        if(instance == null) {
            instance = new UserSession(userName, id);
        }
        return instance;
    }

    public String getUserName() {
        return userName;
    }

    public int getID() {
        return this.ID;
    }

    public void cleanUserSession() {
        userName = "";// or null
        ID = 0;// or null
    }

    @Override
    public String toString() {
        return "UserSession{" +
                "userName='" + userName + '\'' +
                ", privileges=" + ID +
                '}';
    }

}
