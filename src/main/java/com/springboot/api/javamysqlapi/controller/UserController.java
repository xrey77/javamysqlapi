//
//  http://localhost:8090/register
// {
//  "fullname": "Bruce Lee",
//  "emailadd": "bruce@yahoo.com",
//  "username": "Bruce",
//  "password": "Reynald88",
//  "mobileno": "+5343434",
//  "active": 0
//  }
 

package com.springboot.api.javamysqlapi.controller;

import com.springboot.api.javamysqlapi.models.User;
import com.springboot.api.javamysqlapi.services.UserService;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.sql.Timestamp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

@RestController
@CrossOrigin()
public class UserController {
    @Autowired
    UserService userService;

   @Autowired
   private PasswordEncoder passwordEncoder;
    
   
//   http://localhost:8090/getall
    @GetMapping("/getall")
    public List<User> list() {
        return userService.listAllUser();
    }

//    http://localhost:8090/getuser/1
    @GetMapping("/getuser/{id}")
    public ResponseEntity<User> get(@PathVariable Integer id) {
        try {
            User user = userService.getUser(id);
            return new ResponseEntity<User>(user, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        }
    }
    
//    http://localhost:8090/register
    @PostMapping("/register")
    public Map<String, String> add(@RequestBody User user) {
    	Map<String, String> map = new HashMap<>();
    	
    	try {
     	   user.setPassword(passwordEncoder.encode(user.getPassword()));
           userService.saveUser(user);
           map.put("msg", "Successfully Inserted.");
    	} catch(Exception ex) {
    		map.put("msg", "Either Fullname, Email Address, Username has violated unique index.");
    	}
    	return map;
    }
    
//    http://localhost:8090/updateuser/1    
    @PutMapping("/updateuser/{id}")
    public Map<String, String> update(@RequestBody User user, @PathVariable Integer id) {
    	Map<String, String> map = new HashMap<>();
    	try {
            User existUser = userService.getUser(id);
            if (existUser.getId() > 0) {
            	Calendar cal = Calendar.getInstance();
                existUser.setId(id);
                existUser.setFullname(user.getFullname());
                existUser.setActive(user.isActive());
                existUser.setEmailadd(user.getEmailadd());
                existUser.setUpdated_at(new Timestamp(cal.getTimeInMillis()));
                userService.saveUser(existUser);                
                map.put("msg", "Updated Successfully.");              
            }            
            
        } catch (Exception e) {
            map.put("msg", "Fullname, Email Address, Username are bound with unique index.");
        }
        return map;
    }
    
//    http://localhost:8090/deleteuser/1
    @DeleteMapping("/deleteuser/{id}")
    public Map<String, String> delete(@PathVariable Integer id) {
    	Map<String, String> map = new HashMap<>();
    	try {
          userService.deleteUser(id);
          map.put("msg", "Deleted Successfully."); 
    	} catch(Exception ex) {
    		map.put("msg", "Unable to delete."); 
    	}
    	return map;
    }
}