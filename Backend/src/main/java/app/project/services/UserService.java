package app.project.services;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.project.entities.User;
import app.project.repositories.UserRepository;

@Service
public class UserService {

	@Autowired
	UserRepository userRepository;
	
	 @Autowired
	private app.project.jwt.jwtTokenUtil  jwtTokenUtil;
	
	public User getByEmail(String email) {
		 try {
			 return userRepository.getUserByEmail2(email);
		 }catch(Exception e) {
			 e.printStackTrace();
			 return null;
		 }
		 
	  }
	
	// Get User Authentifie
    
    public User UserAuth(HttpServletRequest request) {
    	 //Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    	 final String requestTokenHeader=request.getHeader("Authorization");
    	 String jwtToken=requestTokenHeader.substring(7);
    	 String username=jwtTokenUtil.getUsernameFromToken(jwtToken);
    	 return userRepository.getUserByEmail2(username);
    }
    
    public int getUserClient(){
		  return userRepository.CountUserClient();
	  }
	  
	  public int getUserServiceProvider(){
		  return userRepository.CountUserServiceProvider();
	  }
	
}
