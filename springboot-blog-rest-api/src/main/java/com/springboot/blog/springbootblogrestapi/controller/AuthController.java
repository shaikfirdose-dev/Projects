package com.springboot.blog.springbootblogrestapi.controller;

import com.springboot.blog.springbootblogrestapi.payload.JWTAuthResponse;
import com.springboot.blog.springbootblogrestapi.payload.LoginDto;
import com.springboot.blog.springbootblogrestapi.payload.RegisterDto;
import com.springboot.blog.springbootblogrestapi.service.impl.AuthServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@Tag(
        name = "Rest APIs for Authentication Resource"
)
public class AuthController {
    @Autowired
    private AuthServiceImpl authService;

    @Operation(
            summary = "Login Rest API",
            description = "Login Rest API is used for authenticate the user credentials"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Http Status 200 Success"
    )
    @PostMapping(value = {"/login", "/signin"})
    public ResponseEntity<JWTAuthResponse> login(@RequestBody LoginDto loginDto){
        String token=authService.login(loginDto);

        JWTAuthResponse jwtAuthResponse = new JWTAuthResponse();
        jwtAuthResponse.setAccessToken(token);
        return ResponseEntity.ok(jwtAuthResponse);
    }
    @Operation(
            summary = "Register Rest API",
            description = "Register Rest API is used for Creating New User Account "
    )
    @PostMapping(value = {"/register", "/signup"})
    public ResponseEntity<?> register(@RequestBody RegisterDto registerDto){
        String response=authService.register(registerDto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
