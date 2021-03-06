package com.springboot.api.javamysqlapi.services;

//import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.springboot.api.javamysqlapi.models.User;


@Service
public class JwtUserDetailsService implements UserDetailsService {

	@Autowired
    UserService userService;
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		User xuser = userService.getUserName(username);
		if (xuser != null) {
			return xuser;
		} else {
			throw new UsernameNotFoundException("User not found with username: " + username);			
		}
		
//		if (xuser.getUsername().equals(username)) {
//			return new User(xuser.getUsername(), "$2a$10$slYQmyNdGzTn7ZLBXBChFOC9f6kFjAqPhccnP6DxlWXx2lPk1C3G6",
//					new ArrayList<>());
//		} else {
//			throw new UsernameNotFoundException("User not found with username: " + username);
//		}
		
	}

}