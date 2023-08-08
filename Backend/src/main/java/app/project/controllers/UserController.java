package app.project.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import app.project.entities.User;
import app.project.parametre.DataPhoto;
import app.project.parametre.DataUser;
import app.project.repositories.UserRepository;
import app.project.services.UserService;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
public class UserController {
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	UserService userService;
	
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
	

}
