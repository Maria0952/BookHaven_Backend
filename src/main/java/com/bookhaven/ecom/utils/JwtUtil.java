package com.bookhaven.ecom.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.Date;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import java.security.Key;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {
	
	public static final String SECRET="e82bcd0637265343d3f617546f7dbb315a7a6b1fb541784e2131798fbc9e09e9c6dd2319371cc9da81e767c2573da71528afff0ca9881e945505ca46ca60f20b9dee34ad63cf69c1208dab9c6b81cd0140ea81b0dbeb3d90e189104f63ae82ada573f149188247753aab349c3106619fab7ba663c0bacab6a46b7fcc2089cb064c8861f3ff4c2dd9dad70a05b687cd092ac075a88feaeeccba7d29640f0cd0f27492cc870f338306ea54dfc05b469147c6a538c80d8656d198bd7a6c63ee88246b9824fe4f320fe549ff54e4919b8a0d8d0605d8c6ab070d51c8b8ebefd7a500b6aaa9d52d69af43506a85668ae4948e502f88a22b1b8d37151606347686a8d3";
	
	public String generateToken(String userName) {
		Map<String, Object> claims=new HashMap<>();
		return createToken(claims,userName);
	}
	
	private String createToken(Map<String, Object> claims, String userName) { //building the token
		
		return Jwts.builder()
				.setClaims(claims)
				.setSubject(userName)
				.setIssuedAt(new Date(System.currentTimeMillis()) )
				.setExpiration(new Date(System.currentTimeMillis()+24 * 60 * 60 * 1000)) //sets to 24 hrs
				.signWith(getSignKey(),SignatureAlgorithm.HS256)
				.compact();
	}
	
	 private Key getSignKey() {
	      byte[] keybytes = Decoders.BASE64.decode(SECRET);
	      return  Keys.hmacShaKeyFor(keybytes);
	 }
	 
	 public String extractUsername(String token) {
		 return extractClaim(token,Claims::getSubject); //extracts the username from the claims
	 }
	 
	public <T> T extractClaim(String token, Function<Claims,T> claimsResolver) {
		final Claims claims=extractAllClaims(token);
		return claimsResolver.apply(claims);
	}
	
	private Claims extractAllClaims(String token) { //extracting all the payload data

		return Jwts.parserBuilder()
				.setSigningKey(getSignKey())
				.build()
				.parseClaimsJws(token)
				.getBody();
		
	}
	
	public Boolean isTokenExpired(String token){ // checks if the token is expired
		return extractExpiration(token).before(new Date());
	}
	
	public Date extractExpiration(String token) {
		return (Date) extractClaim(token, Claims::getExpiration);
	}
	
	public  Boolean validateToken(String token,UserDetails userDetails) {
		final String username=extractUsername(token);
		return(username.equals(userDetails.getUsername())&& !isTokenExpired(token));
	}
	
}
