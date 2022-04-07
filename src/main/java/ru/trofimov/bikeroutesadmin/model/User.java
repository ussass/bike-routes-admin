package ru.trofimov.bikeroutesadmin.model;

public class User {

    private String login;

    private String role;

    public User() {
    }

    public User(String string) {
        String login = string.split(",")[0].split(":")[1];
        String role = string.split(",")[1].split(":")[1];
        this.login = login.substring(1, login.length() - 1);
        this.role = role.substring(1, login.length() - 1);
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
