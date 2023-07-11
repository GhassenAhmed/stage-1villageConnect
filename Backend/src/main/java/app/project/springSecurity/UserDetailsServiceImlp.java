package app.project.springSecurity;

import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import app.project.entities.User;
import app.project.repositories.UserRepository;

public class UserDetailsServiceImlp implements UserDetailsService {
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> user=userRepository.findByEmail(username);
	 	if(user==null) {
	 		throw new UsernameNotFoundException("could not found email : "+username);
	 	}
	 	return user.map(MyUserDetails::new).get();	
	}

	
}
