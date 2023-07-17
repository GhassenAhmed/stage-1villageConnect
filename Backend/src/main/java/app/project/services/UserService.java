package app.project.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.project.entities.User;
import app.project.repositories.UserRepository;

@Service
public class UserService {

	@Autowired
	UserRepository userRepository;
	
	
	
	public User getByEmail(String email) {
		 try {
			 return userRepository.getUserByEmail2(email);
		 }catch(Exception e) {
			 e.printStackTrace();
			 return null;
		 }
		 
	  }
	
	
}
