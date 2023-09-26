package com.springboot.blog.springbootblogrestapi.service;

import com.springboot.blog.springbootblogrestapi.payload.LoginDto;
import com.springboot.blog.springbootblogrestapi.payload.RegisterDto;
import org.springframework.stereotype.Service;


public interface AuthService {
    String login(LoginDto loginDto);
    String register(RegisterDto registerDto);
}
