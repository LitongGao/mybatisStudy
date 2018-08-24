package com.litong.study.model;

import lombok.Data;

@Data
public class User {
    private Long id;
    private String username;
    private String password;
    private String address;

    @Override
    public String toString() {
        return "User{" +
            "id=" + id +
            ", username='" + username + '\'' +
            ", password='" + password + '\'' +
            ", address='" + address + '\'' +
            '}';
    }

    public User() {
    }

    public User(Long id, String username, String password, String address) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.address = address;
    }
}
