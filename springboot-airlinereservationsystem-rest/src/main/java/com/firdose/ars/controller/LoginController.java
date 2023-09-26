package com.firdose.ars.controller;

import com.firdose.ars.dto.Login;
import com.firdose.ars.enums.Role;
import com.firdose.ars.enums.Status;
import com.firdose.ars.service.impl.LoginServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/login")
public class LoginController {
    @Autowired
    LoginServiceImp loginServiceImp;

    @PostMapping("/register")
    public ResponseEntity<?> addUser(@RequestBody Login userdetails) {
        loginServiceImp.addUser(userdetails);
        return new ResponseEntity<>("User added successfully!", HttpStatus.OK);
    }

    @GetMapping("/login")
    public ResponseEntity<?> loginUser(@RequestParam("username") String username,@RequestParam("password") String password) throws Exception{
        Login lg=loginServiceImp.loginUser(username,password);
        return new ResponseEntity<>("Login successfully", HttpStatus.OK);
    }

    @GetMapping("/view-all-users")
    public List<Login> viewAllUsers(){
        return loginServiceImp.viewAllUsers();
    }

    @PutMapping("/update-user")
    public ResponseEntity<?> updateUser(@RequestBody Login user) {
        return new ResponseEntity<>(loginServiceImp.updateUser(user),HttpStatus.OK);
    }

    @GetMapping("/search-user/{username}")
    public ResponseEntity<?> searchUserByName(@PathVariable("username") String username) throws Exception {
        return new ResponseEntity<>(loginServiceImp.searchUserByName(username), HttpStatus.FOUND);
    }

    @DeleteMapping("/delete-user/{username}")
    public Login deleteUserByName(@PathVariable("username") String username) throws Exception {
        return loginServiceImp.deleteUserByName(username);
    }

    @GetMapping("/view-users/{role}")
    public List<Login> viewUsersByRole(@PathVariable("role") Role role){
        return loginServiceImp.viewUsersByRole(role);
    }

    @GetMapping("/list-users/{status}")
    public ResponseEntity<?> viewUsersByStatus(@PathVariable("status") Status status){
        return new ResponseEntity<>(loginServiceImp.viewUsersBYStatus(status), HttpStatus.OK);
    }

    @GetMapping("/logout")
    public ResponseEntity<?> logout(){
        return new ResponseEntity<>("Logout successfully", HttpStatus.ACCEPTED);
    }
}
