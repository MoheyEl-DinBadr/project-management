package com.mohey.pma.entities;


import com.mohey.pma.validatiors.UniqueUserEmail;
import com.mohey.pma.validatiors.UniqueUserName;

import javax.persistence.*;
import javax.validation.constraints.Email;

@Entity(name = "user_accounts")
@Table(name = "user_accounts")
public class UserAccount {
    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_accounts_seq")
    @SequenceGenerator(name = "user_accounts_seq", sequenceName = "user_accounts_seq", allocationSize = 1)
    private long userId;

    @Column(name = "username") @UniqueUserName
    private String userName;
    @Email @UniqueUserEmail
    private String email;

    private String password;

    private String role;

    private boolean enabled = true;

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    public String toString() {
        return "UserAccount{" +
                "userName='" + userName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                ", enabled=" + enabled +
                '}';
    }
}
