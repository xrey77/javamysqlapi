//http://localhost:8089/login
//{
//  "username": "Jigoro",
//  "password": "Reynald88"
//}
  
 

package com.springboot.api.javamysqlapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.springboot.api.javamysqlapi.config.JwtTokenUtil;
import com.springboot.api.javamysqlapi.models.JwtRequest;
import com.springboot.api.javamysqlapi.models.JwtResponse;
import com.springboot.api.javamysqlapi.models.User;
import com.springboot.api.javamysqlapi.services.UserService;

@RestController
@CrossOrigin
public class JwtAuthenticationController {

	@SuppressWarnings("unused")
	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

//	@Autowired
//	private UserDetailsService jwtInMemoryUserDetailsService;

	
	
	@Autowired
    UserService userService;

	@Autowired
	private PasswordEncoder passwordEncoder;	
	

//	http://localhost:8090/login
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest)
			throws Exception {

    	String plainPwd = authenticationRequest.getPassword();
    	try {
          User xuser = userService.getUserName(authenticationRequest.getUsername());
          if (xuser != null) {
        	  if (passwordEncoder.matches(plainPwd, xuser.getPassword())) {
        		          		  	
        			final String token = jwtTokenUtil.generateToken(xuser);
        		  
        			return ResponseEntity.ok(new JwtResponse(token));
        		  
        	  } else {
        		  return (ResponseEntity<?>) ResponseEntity.notFound();    	  
        		  
        	  }
        	
          } else {
    		  return (ResponseEntity<?>) ResponseEntity.notFound();    	  

          }
    	} catch(Exception ex) {
  		  return (ResponseEntity<?>) ResponseEntity.notFound();    	  
    	}
  		
	}

}
