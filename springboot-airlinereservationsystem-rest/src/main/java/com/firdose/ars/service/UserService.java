package com.firdose.ars.service;

import com.firdose.ars.dto.User;

import java.util.List;

public interface UserService {

    public void addUserBookings(User user1) throws Exception;
    public User viewUserBookings(String username) throws Exception;
    public User updateUserBookings(User user);
    public boolean deleteUserBookings(String username);
    public List<User> viewAllBookings();
}
