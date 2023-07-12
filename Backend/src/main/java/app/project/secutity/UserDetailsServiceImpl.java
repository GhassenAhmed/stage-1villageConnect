package app.project.secutity;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import app.project.entities.User;
import app.project.repositories.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	
	 @Autowired
	 UserRepository userrep;
	 
	 public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		 	Optional<User> user=userrep.getUserByemail(username);
		 	if(user==null) {
		 		throw new UsernameNotFoundException("could not found email : "+username);
		 	}
		 	return user.map(MyUserDetails::new).get();	
	 }
	 
}
