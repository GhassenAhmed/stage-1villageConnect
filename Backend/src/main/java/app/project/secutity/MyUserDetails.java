package app.project.secutity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import app.project.entities.Role;
import app.project.entities.User;

public class MyUserDetails implements UserDetails {
	
    private User user;
 
    public MyUserDetails(User u) {
	    user=u;
    }
 
    @Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
	    List<SimpleGrantedAuthority> authorities=new ArrayList<>();
	    try {
	    	List<Role> roles=user.getRoles();
	    	authorities=new ArrayList<>();
	    	for(Role r:roles) {
	    		authorities.add(new SimpleGrantedAuthority(r.getRoleName()));
	    	}
	    }catch(Exception e) {
	    	System.out.println(e.getMessage());
	    }
	    return authorities;
	}
    
   /* @Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<SimpleGrantedAuthority> authorities=new ArrayList<>();
	    String userRole=user.getRole();
	    authorities.add(new SimpleGrantedAuthority(userRole));
		return authorities;
	}*/
 
    @Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getEmail();
	}
	
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

    
 
	
}
