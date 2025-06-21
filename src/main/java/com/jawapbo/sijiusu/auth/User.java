package com.jawapbo.sijiusu.auth;

import com.jawapbo.sijiusu.utils.Role;

@SuppressWarnings("unused")
public class User {
    private static User INSTANCE;

    private String id;
    private String name;
    private String email;
    private Role role;

    public static User getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new User();
        }
        return INSTANCE;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
