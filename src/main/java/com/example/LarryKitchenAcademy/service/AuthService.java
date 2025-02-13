package com.example.LarryKitchenAcademy.service;

import com.example.LarryKitchenAcademy.dto.LoginDto;
import com.example.LarryKitchenAcademy.dto.UserDto;
import com.example.LarryKitchenAcademy.repository.UserRepository;
import com.example.LarryKitchenAcademy.utils.AuthResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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
        try{
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
                userDto.setUsername(user.getUsername());
                userDto.setRole(user.getRole());

                return AuthResponse
                        .builder()
                        .message("User successfully login")
                        .data(userDto)
                        .token(jwtToken)
                        .build();
            }else{
                Map<String,Object > response = new HashMap<>();
                response.put("message","User failed to login");
                return AuthResponse.builder().build();
            }
        }catch (BadCredentialsException e){
            return AuthResponse.builder()
                    .message(e.getMessage())
                    .build();
        }catch (Exception e){
            return AuthResponse.builder()
                    .message(e.getMessage())
                    .build();
        }
    }
}
