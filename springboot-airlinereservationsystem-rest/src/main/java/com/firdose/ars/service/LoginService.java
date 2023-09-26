package com.firdose.ars.service;

import com.firdose.ars.dto.Login;
import com.firdose.ars.enums.Role;
import com.firdose.ars.enums.Status;
import com.firdose.ars.exceptions.UserNotFoundException;

import java.util.List;

public interface LoginService {
    public Login addUser(Login userdetails);
    public Login loginUser(String username, String password) throws UserNotFoundException;
    public List<Login> viewAllUsers();
    public Login updateUser(Login user);
    public Login searchUserByName(String username) throws UserNotFoundException;
    public Login deleteUserByName(String username) throws UserNotFoundException;
    public List<Login> viewUsersByRole(Role role);
    public List<Login> viewUsersBYStatus(Status status);
}
