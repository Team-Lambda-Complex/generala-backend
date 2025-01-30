package com.lambda_complex.generala.controllers;

import com.lambda_complex.generala.dto.request.AuthRequestDto;
import com.lambda_complex.generala.services.interfaces.IAuthService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    IAuthService authService;
    public AuthController(IAuthService authService){
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody AuthRequestDto authRequestDto){
        return ResponseEntity.ok(authService.authenticate(authRequestDto));
    }
}
