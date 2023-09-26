package com.firdose.ars.dto;

import com.firdose.ars.enums.Role;
import com.firdose.ars.enums.Status;

import javax.persistence.*;

@Entity
@Table(name="login_info")
public class Login {
    @Id
    private String userName;
    private String userEmail;
    @Enumerated(EnumType.STRING)
    Role role;
    private String password;
    private long mobileNumber;
    @Enumerated(EnumType.STRING)
    Status status;

    public Login() {

    }

    public Login(String userName, String userEmail, Role role, String password, long mobileNumber, Status status) {
        super();
        this.userName = userName;
        this.userEmail = userEmail;
        this.role = role;
        this.password = password;
        this.mobileNumber = mobileNumber;
        this.status = status;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public long getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(long mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "LoginDTO [userName=" + userName + ", userEmail=" + userEmail + ", role=" + role + ", password="
                + password + ", mobileNumber=" + mobileNumber + ", status=" + status + "]";
    }

}