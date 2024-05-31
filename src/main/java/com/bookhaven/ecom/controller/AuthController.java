package com.bookhaven.ecom.controller;

import java.io.IOException;
import java.util.Optional;


import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bookhaven.ecom.dto.AuthenticationRequest;
import com.bookhaven.ecom.dto.SignupRequest;
import com.bookhaven.ecom.dto.UserDto;
import com.bookhaven.ecom.entity.User;
import com.bookhaven.ecom.repository.UserRepository;
import com.bookhaven.ecom.services.auth.AuthService;
import com.bookhaven.ecom.services.jwt.UserDetailsServiceImpl;
import com.bookhaven.ecom.utils.JwtUtil;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class AuthController {
	private final AuthenticationManager authenticationManager;
	private final UserDetailsService userDetailsService ;
	private final UserRepository userRepository ;
	private final JwtUtil jwtUtil;
	public static final String TOKEN_PREFIX="Bearer ";
	public static final String HEADER_STRING="Authorization";
	private final AuthService authService ;
	private Logger logger = LoggerFactory.getLogger(AuthController.class);
	
	@PostMapping("/authenticate")
	public void createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest,HttpServletResponse response ) throws IOException, JSONException {
		try {
			logger.info("Received login request from user: {}",authenticationRequest.getUsername());
			authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken( //checking if the user exists in database 
						authenticationRequest.getUsername(),
						authenticationRequest.getPassword()
						));
		}
		catch(BadCredentialsException e) {
			logger.error("Incorrect username or password");
			throw new BadCredentialsException("Incorrect username or password.");
		}
		//user exists so generating the token next and creating response
		final UserDetails userDetails =userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
		Optional<User> optionalUser=userRepository.findFirstByEmail(userDetails.getUsername());
		final String jwt=jwtUtil.generateToken(userDetails.getUsername());
		if(optionalUser.isPresent()) {
			logger.info("User authenticated successfully");
			response.getWriter().write(new JSONObject()
					.put("userId",optionalUser.get().getId())
					.put("role",optionalUser.get().getRole())
					.toString()
					);
			response.addHeader("Access-Control-Expose-Headers", "Authorization");
			response.addHeader("Access-Control-Allow-Headers", "Authorization, X-PINGOTHER, Origin, "+
			"X-Requested-With,Content-Type, Accept, X-Custom-header");
			response.addHeader(HEADER_STRING,TOKEN_PREFIX+ jwt);
		}
		
	
	}
	
	@PostMapping("/sign-up")
	public ResponseEntity<?> signupUser(@RequestBody SignupRequest signupRequest){
		logger.info("Received sign up request from user: {}",signupRequest.getEmail());
		if(authService.hasUserWithEmail(signupRequest.getEmail())) {
			logger.info("User already exists");
			return new ResponseEntity<>("User already exists",HttpStatus.NOT_ACCEPTABLE);
		}
		UserDto userDto= authService.createUser(signupRequest);
		logger.info("Registration successful for user: {}",signupRequest.getEmail());
		return new ResponseEntity<>(userDto,HttpStatus.OK);
	}
}

