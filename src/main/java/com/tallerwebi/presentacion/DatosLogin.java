package com.tallerwebi.presentacion;

public class DatosLogin {
    private String user;
    private String password;

    public DatosLogin() {
    }

    public DatosLogin(String user, String password) {
        this.user = user;
        this.password = password;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String email) {
        this.user = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

