package com.filedm.config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.filedm.helper.JwtUtil;
import com.filedm.service.UserDetailsServiceImp;


@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
	
	@Autowired
	private JwtUtil util;
	
	@Autowired
	private UserDetailsServiceImp user;
	

	
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException
	{
		
		String requestTokenHeader =  request.getHeader("Authorization");
		String username = null;
		String jwtToken = null;
		if (requestTokenHeader!=null && requestTokenHeader.startsWith("Bearer ")) 
		{
			jwtToken = requestTokenHeader.substring(7);
			try 
			{
				username = this.util.extractUsername(jwtToken);
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
			UserDetails  details = this.user.loadUserByUsername(username);
			if (username!=null && SecurityContextHolder.getContext().getAuthentication()==null)
			{
				UsernamePasswordAuthenticationToken  passwordAuthenticationToken = new UsernamePasswordAuthenticationToken(details, null, details.getAuthorities());
				passwordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(passwordAuthenticationToken);
			} 
			else {

			}
			
		}
		filterChain.doFilter(request, response);
	}

}
