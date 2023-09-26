package com.firdose.ars.dto;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="users")
public class User {
    @Id
    private String userName;
    private String email;

    @OneToMany(cascade = CascadeType.ALL)
    List<Passenger> passengers=new ArrayList<>();


    public User() {

    }


    public User(String userName, String email, List<Passenger> passengers) {
        super();
        this.userName = userName;
        this.email = email;
        this.passengers = passengers;
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


    public List<Passenger> getPassengers() {
        return passengers;
    }


    public void setPassengers(List<Passenger> passengers) {
        this.passengers = passengers;
    }


    @Override
    public String toString() {
        return "UserDTO [userName=" + userName + ", email=" + email + ", passengers=" + passengers + "]";
    }
}
