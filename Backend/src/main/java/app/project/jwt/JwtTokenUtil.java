package app.project.jwt;

import java.io.Serializable;
import java.util.Date;


import java.util.Base64;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtTokenUtil implements Serializable {
	
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static final long JWT_TOKEN_VALIDITY = 5 * 60 * 60;
	 
	 @Value("${jwt.secret}")
	 private static String secret;
	 
	  public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
	        final Claims claims = getAllClaimsFromToken(token);
	        return claimsResolver.apply(claims);
	  }
	  
	  //retrieve expiration date from jwt token
	  public Date getExpirationDateFromToken(String token) {
	        return (Date) getClaimFromToken(token,  Claims::getExpiration);
	  }
	    
	 private Claims getAllClaimsFromToken(String token) {
		  return Jwts.parser().setSigningKey(secret.getBytes()).parseClaimsJws(token).getBody();
	  }
	 
	 // check if the token has expired
	  private Boolean isTokenExpired(String token) {
	        final Date expiration = getExpirationDateFromToken(token);
	        return expiration.before(new Date());
	  }
	  
	   private String doGenerateToken(Map<String, Object> claims, String subject) {
	        JwtTokenUtil.secret = "MyEduConnect";
	        String encodedString = Base64.getEncoder().encodeToString(JwtTokenUtil.secret.getBytes());
	        return Jwts.builder()
	                .setClaims(claims)
	                .setSubject(subject)
	                .setIssuedAt(new Date(System.currentTimeMillis()))
	                .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 5000))
	                .signWith(SignatureAlgorithm.HS512, encodedString).compact();

	    }

	    // generate token for user
	  public String generateToken(UserDetails userDetails) {
	        Map<String, Object> claims = new HashMap<>();
	        return doGenerateToken(claims, userDetails.getUsername());
	    }
	  
	  public String getUsernameFromToken(String token) {
	        return getClaimFromToken(token, Claims::getSubject);
	   }
	  
	// validate token
	  public Boolean validateToken(String token, UserDetails userDetails) {
	        final String username = getUsernameFromToken(token);
	        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
	    }
	  
	  
}