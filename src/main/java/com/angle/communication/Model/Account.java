package com.angle.communication.Model;

import lombok.Data;

@Data
public class Account {
    private int id;
    private int password;
    private String username;

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", password=" + password +
                ", username='" + username + '\'' +
                '}';
    }
}
