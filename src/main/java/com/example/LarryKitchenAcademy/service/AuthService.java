package com.example.LarryKitchenAcademy.service;

import com.example.LarryKitchenAcademy.dto.LoginDto;
import com.example.LarryKitchenAcademy.dto.RegisterRequest;
import com.example.LarryKitchenAcademy.dto.UserDto;
import com.example.LarryKitchenAcademy.entity.User;
import com.example.LarryKitchenAcademy.repository.UserRepository;
import com.example.LarryKitchenAcademy.utils.AuthResponse;
import com.example.LarryKitchenAcademy.utils.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class AuthService {
    @Autowired
    private UserRepository repository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;

    public AuthResponse authenticate(LoginDto request){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(), request.getPassword()
                )
        );
        if(authentication.isAuthenticated()){
            var user = repository.findByUsername(request.getUsername()).orElseThrow();
            var jwtToken = jwtService.generateToken(user);

            var userDto = new UserDto();
            userDto.setUserId(user.getId());
            userDto.setUserName(user.getUsername());
            userDto.setUserPassword(user.getPassword());
            userDto.setRole(user.getRole());
            userDto.setUserCreateDate(user.getCreateDate());

            return AuthResponse
                    .builder()
                    .message("User successfully login")
                    .userDto(userDto)
                    .token(jwtToken)
                    .build();
        }else{
            Map<String,Object > response = new HashMap<>();
            response.put("message","user gagal login");
            return AuthResponse.builder().build();
        }
    }
}
