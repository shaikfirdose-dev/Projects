package com.firdose.ars.service.impl;

import com.firdose.ars.dto.Login;
import com.firdose.ars.enums.Role;
import com.firdose.ars.enums.Status;
import com.firdose.ars.exceptions.UserNotFoundException;
import com.firdose.ars.repository.LoginRepository;
import com.firdose.ars.service.LoginService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoginServiceImp implements LoginService {
    @Autowired
    LoginRepository loginRepository;


    private static final Log log= LogFactory.getLog(LoginServiceImp.class);


    public Login addUser(Login userdetails) {
        log.info("User added successfully");
        return loginRepository.save(userdetails);
    }

    public Login loginUser(String username, String password) throws UserNotFoundException {
        if(loginRepository.existsById(username)) {
            Login user=loginRepository.findById(username).get();

            if(Status.ACTIVE.equals(user.getStatus())) {
                if(password.equals(user.getPassword())) {
                    log.info("User login successfully");
                    return user;
                }
                else {
                    log.error("Password is incorrect");
                    throw new UserNotFoundException("Sorry your password is incorrect, please give correct password");
                }
            }

            else {
                log.warn("User name not found");
                throw new UserNotFoundException("Username not found");
            }
        }
        else {
            log.warn("User name not found");
            throw new UserNotFoundException("Username not found");
        }


    }

    public List<Login> viewAllUsers() {
        log.info("List of users");
        return loginRepository.findAll();
    }

    public Login updateUser(Login user) {
        log.fatal("User update successfully");
        loginRepository.save(user);
        return user;
    }

    public Login searchUserByName(String username) throws UserNotFoundException {
        if(loginRepository.existsById(username)) {
            log.info("User found successfully");
            return loginRepository.findById(username).get();
        }
        else {
            log.error("Username Not found");
            throw new UserNotFoundException("Username not found");
        }
    }

    public Login deleteUserByName(String username) throws UserNotFoundException {
        if(loginRepository.existsById(username)) {

            log.info("user deleted successfully");
            Login user=loginRepository.findById(username).get();
            user.setStatus(Status.DELETED);
            return loginRepository.save(user);

        }
        else {
            log.warn("user not found");
            throw new UserNotFoundException("Username not found");
        }
    }

    public List<Login> viewUsersByRole(Role role) {
        log.info("View users by role");
        return loginRepository.viewUsersByRole(role);
    }

    // Active or Deleted
    public List<Login> viewUsersBYStatus(Status status) {
        log.info("View users by status");
        return loginRepository.viewUsersByStatus(status);
    }

    public boolean searchByUserName(String username) throws Exception {
        if(loginRepository.existsById(username)) {
            Login user=loginRepository.findById(username).get();
            if(Status.ACTIVE.equals(user.getStatus())) {
                log.info("User found successfully");
                return true;
            }
        }
        else {
            log.error("Username Not found");
            throw new UserNotFoundException("Username not found");
        }
        return false;
    }
}

