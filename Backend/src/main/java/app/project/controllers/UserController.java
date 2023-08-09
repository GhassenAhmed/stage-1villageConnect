package app.project.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import app.project.entities.User;
import app.project.parametre.DataPhoto;
import app.project.parametre.DataUser;
import app.project.repositories.UserRepository;
import app.project.secutity.SecurityConfig;
import app.project.services.UserService;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
public class UserController {
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	UserService userService;
	
	@Autowired
	SecurityConfig securityConfig;
	
	  @GetMapping("/getUserClient")
  	public ResponseEntity<?> getUserClient(){	
  			return  ResponseEntity.ok().body(userService.getUserClient());	
  	}
	  
	  @GetMapping("/getUserServiceProvider")
	  	public ResponseEntity<?> getUserServiceProvider(){	
	  			return  ResponseEntity.ok().body(userService.getUserServiceProvider());	
	  	}
	  
	  @GetMapping("/getAllUsers")
	  	public List<User> getAllUsers(){	
	  			return  userRepository.getAllUsers();	
	  	}
	  
	  
	  @GetMapping("/countUsers")
	  	public int countUsers(){	
	  			return  userRepository.countUsers();	
	  	}
	  
	  
	  
	  
	  @PostMapping("/uploadPhoto")
	    public ResponseEntity<?> EditPhoto(@RequestBody DataPhoto photo,HttpServletRequest request){
	    	  User userAuth=userService.UserAuth(request);
	    	  userAuth.setPhoto(photo.getPhoto());
	    	  userRepository.save(userAuth);
	    	  return  ResponseEntity.ok().body("Photo Upload");
	    }
	  
	  @PostMapping("/EditInfoPersonnel")
	    public ResponseEntity<?> EditInfoPersonnel(@RequestBody DataUser data,HttpServletRequest request){
	    	  User userAuth=userService.UserAuth(request);
	    	  userAuth.setFirstName(data.getFirstName());
	    	  userAuth.setLastName(data.getLastName());
	    	  userRepository.save(userAuth);
	    	  return  ResponseEntity.ok().body("User Modified");
	    }
	  
	  @GetMapping("/CheckPassword")
	    public  ResponseEntity<?> CheckPassword(@RequestParam("password") String pass,HttpServletRequest request) {
	    	   User user = userService.UserAuth(request);
	    	    if (user == null) {
	    	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User not authenticated");
	    	    }
	    	    boolean res = securityConfig.passwordEncoder().matches(pass, user.getPassword());
	    	    if (res) {
	    	        return ResponseEntity.ok().body(true);
	    	    } else {
	    	        return ResponseEntity.ok().body(false);
	    	    }
	    }
	  
	  @PostMapping("/ChangerPasswordActuel")
	    public ResponseEntity<?> changerPassword(@RequestParam("password") String pass,HttpServletRequest request){
	    	   User user = userService.UserAuth(request);
	    	   if (user == null) {
	    	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User not authenticated");
	    	    }
	    	   user.setPassword(securityConfig.passwordEncoder().encode(pass));
	    	   userRepository.save(user);
	    	   return  ResponseEntity.ok().body("Password Modified");
	    }
	

}
