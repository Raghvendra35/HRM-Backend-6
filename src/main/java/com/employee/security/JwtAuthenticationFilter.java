package com.employee.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

//step4
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter
{


	@Autowired()
    private JwtTokenHelper jwtTokenHelper;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			                                                                   throws ServletException, IOException 
	{
		
   
                                                //Authorization is a key		
     String requestTokenHeader=request.getHeader("Authorization");		
		
     System.out.println(requestTokenHeader);
	 String username=null;
      String token=null;
                                                     
     if(requestTokenHeader!=null && requestTokenHeader.startsWith("Bearer"))
     {
    	token=requestTokenHeader.substring(7);     
                                                 
       try
       {
    	username=this.jwtTokenHelper.getUsernameFormToken(token);
       }catch(IllegalArgumentException e)
       {
    	   System.out.println("Unable to get Jwt Token");
       }catch(ExpiredJwtException e)
       {
    	System.out.println("Jwt token has expired");   
       }catch(MalformedJwtException e)
       {
    	 System.out.println("Invalid Jwt");   
       }

    	
     }else
     {
    	 System.out.println("jwt Token does not begin with Beare");
     }
     
      

     
      if(username!=null && SecurityContextHolder.getContext().getAuthentication()==null)
      {	 
    	  
  UserDetails userDetails= this.userDetailsService.loadUserByUsername(username);
      
        if(this.jwtTokenHelper.validateToken(token, userDetails))
        {
         
        	
    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken=
        		new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
        	
   	usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        	
    SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
          	
        	
        }else
        {
        	System.out.println("Invald Jwt Token ");
        }
    	  
      }else
      {
          System.out.println("username is null or context is not null");	  
      }
  
      filterChain.doFilter(request, response);
	}
}
