package com.example.free_uc;

public class modal {

    String name;
    String password;
    String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public modal(String email) {
        this.email = email;
    }

    public modal() {


    }

    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public modal(String name, String password) {
        this.name = name;
        this.password = password;
    }
}
