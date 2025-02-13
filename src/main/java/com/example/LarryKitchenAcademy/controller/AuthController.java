package com.example.LarryKitchenAcademy.controller;

import com.example.LarryKitchenAcademy.dto.LoginDto;
import com.example.LarryKitchenAcademy.service.AuthService;
import com.example.LarryKitchenAcademy.utils.AuthResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("auth")
@CrossOrigin(origins = "*")
public class AuthController {
    @Autowired
    private AuthService service;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> authenticate(@RequestBody LoginDto request){
        return ResponseEntity.ok(service.authenticate(request));
    }
}
