package com.bookhaven.ecom.services.auth;

import org.springframework.stereotype.Repository;

import com.bookhaven.ecom.dto.SignupRequest;
import com.bookhaven.ecom.dto.UserDto;

@Repository
public interface AuthService {
UserDto createUser(SignupRequest signupRequest);
Boolean hasUserWithEmail(String email);
}
