package app.project.services;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.project.Mail.Mail;
import app.project.entities.User;
import app.project.repositories.UserRepository;
import net.bytebuddy.utility.RandomString;

@Service
public class UserService {

	@Autowired
	UserRepository userRepository;
	
	 @Autowired
	private app.project.jwt.jwtTokenUtil  jwtTokenUtil;
	 
	 @Autowired
	 Mail mailsender;
	
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
	  
	  public void VerifyEmail(String email) throws Exception {
		  User user=userRepository.getUserByEmail2(email);
		  if(user==null) {
			  throw new Exception("User Not Found");
		  }else {
			  if(user.getEmail_verified_at()!=null) {
				  throw new Exception("User Already Verified");
			  }else {
				  user.setEmail_verified_at(new Date());
				  userRepository.save(user);
			  }
		  }
	  }
	  
	  public void forgotPassword(String email)throws Exception {
		  User user=userRepository.getUserByEmail2(email);
		  if(user==null) {
			  throw new Exception("Email Not Found");
		  }else {
			  String Token=RandomString.make(8);
			  user.setPassword_token(Token);
			  user.setPassword_token_send_ats(new Date());
			  userRepository.save(user);
			  mailsender.SendForgotPassword(user, Token);
		   }
	  }
	  
	  public void ChangerPassword(String email,String Token,String password)throws Exception {
		  User user=userRepository.getUserByEmail2(email);
		  if(user==null) {
			  throw new Exception("Email Not Found");
		  }else {
			  if(userRepository.CheckToken(Token)==null) {
				  throw new Exception("Token Not Found");
			  }else {
				  if(user.getPassword_token().equals(Token)) {
					  user.setPassword(password);
					  user.setPassword_token(null);
					  user.setPassword_token_send_ats(null);
					  userRepository.save(user);
				  }else {
					  throw new Exception("Token doesn't match");
				  }
			  }
		   }
	  }
	
}
