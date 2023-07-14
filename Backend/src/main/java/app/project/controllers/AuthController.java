package app.project.controllers;



import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


import app.project.entities.User;
import app.project.authenticate.Credentials;
import app.project.repositories.UserRepository;
import app.project.secutity.UserDetailsServiceImpl;
import app.project.services.UserService;


@RestController
public class AuthController {

	@Autowired
	private UserRepository userRepositiry;
		
	 @Autowired
	AuthenticationManager authenticationManager;
	 
	 @Autowired
	UserDetailsServiceImpl userDetailsServiceImpl;
	 
	 @Autowired
	private app.project.secutity.SecurityConfig SecurityConfig;
	 
	 @Autowired
	 UserService userService;
	 
	 @Autowired
	 private app.project.jwt.jwtTokenUtil jwtTokenUtil;
	
	@PostMapping("/signUp")
	public ResponseEntity<?> SignUp(@RequestBody User user){	
		User u = new User();
		u.setFirstName(user.getFirstName());
		u.setLastName(user.getLastName());
		u.setEmail(user.getEmail());
		u.setPassword(SecurityConfig.passwordEncoder().encode(user.getPassword()));
		u.setRoles(u.getRoles());
		userRepositiry.save(u);
		return new ResponseEntity<>(u,HttpStatus.OK);
	}
	
	
	@PostMapping("/logIn")
	public ResponseEntity<?> LogIn(@RequestBody Credentials auth) {
		
		try {
    		if(userRepositiry.getUserByemail(auth.getEmail())==null) {
    			return new ResponseEntity<String>("User Not Found",HttpStatus.CONFLICT);
    		}
    		Authentication authsuser = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(auth.getEmail(), auth.getPassword()));
    	}catch(BadCredentialsException e) {
    		return new ResponseEntity<String>("Incorrect email or password",HttpStatus.CONFLICT);
    	}
		
		
		UserDetails userDetails = userDetailsServiceImpl.loadUserByUsername(auth.getEmail());
		
		User user=userRepositiry.getUserByEmail2(auth.getEmail());
		
		//verify email
		if(user.getEmail_verified_at()==null) {
    		return new ResponseEntity<String>("Email not verified",HttpStatus.CONFLICT);
    	}
		
		
		//active email
		if(userRepositiry.GetStatus(user.getId())==0) {
    		return new ResponseEntity<String>("Your Acount Not Active",HttpStatus.CONFLICT);
    	}else if(userRepositiry.GetStatus(user.getId())==2) {
    		return new ResponseEntity<String>("Your Acount Rejected",HttpStatus.CONFLICT);
    	}
		
		String token=jwtTokenUtil.generateToken(userDetails);
		
		return new ResponseEntity<>(token,HttpStatus.OK);
		
	}
}