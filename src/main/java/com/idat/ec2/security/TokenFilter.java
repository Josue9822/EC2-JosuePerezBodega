package com.idat.ec2.security;

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

import io.jsonwebtoken.ExpiredJwtException;

@Component
public class TokenFilter extends OncePerRequestFilter {
	
	@Autowired
	private UserDetailService service;
	
	@Autowired
	private JWTUtil util;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {


		final String tokenHeader = request.getHeader("Authorization"); //constante para obtener la cabecera del token
		
		String token = null;
		String username = null;
		
		if (tokenHeader != null && tokenHeader.startsWith("Bearer ")) { //todos los tokens comienzan con bearer
			
			token = tokenHeader.substring(7);  //el token comienza con bearer y un espacio por lo tanto debe tener 7 caracteres
			
			try {
				
				username = util.getUsernameFromToken(token); //Obtener el token en la variable username
				
			} catch (IllegalArgumentException e) { //excepciones personalizadas
				
				logger.warn("Token con campo invalido"); 
			} catch(ExpiredJwtException e) {
				
				logger.warn("Token expirado");
			}
			
		}else {
			logger.warn("Token invalido");
		}
		
		if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
			
			UserDetails user = this.service.loadUserByUsername(username); //este usuario que se esta validando
			
			if (util.validateToken(token, user)) {
				
				UsernamePasswordAuthenticationToken usernameToken = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities()); //se guarda aqui para autenticar el token
				
				usernameToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				
				SecurityContextHolder.getContext().setAuthentication(usernameToken);
				
			}
			
		}
		
		filterChain.doFilter(request, response);
		
		
	}

}
