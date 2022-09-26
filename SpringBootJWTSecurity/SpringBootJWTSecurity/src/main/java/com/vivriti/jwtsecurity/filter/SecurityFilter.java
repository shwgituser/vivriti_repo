package com.vivriti.jwtsecurity.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.vivriti.jwtsecurity.util.JwtUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class SecurityFilter extends OncePerRequestFilter {
	@Autowired
	private JwtUtil jwtUtil;
	@Autowired
	private UserDetailsService userDetailsService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// 1. Read Token from Request
		String token = request.getHeader("Authorization");
		// 2. if token is not null then goto JwtUtil
		if (token != null) {
			// 3 get Subject(username)
			String username = jwtUtil.getUsername(token);

			// 4 communicate with DB using username
			// 5 get Spring security user Object
			if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
				UserDetails user = userDetailsService.loadUserByUsername(username);
				// 6 Optional validate DB user and token subject mactching or not?
				boolean isValid = jwtUtil.validateToken(token, user.getUsername());
				if (isValid) {
					// 7 create Authentication Object
					UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
							user.getUsername(), user.getPassword(), user.getAuthorities());

					// link with current request
					authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
					// also link with SecurityContext
					SecurityContextHolder.getContext().setAuthentication(authentication);
				} // isValid
			} // username ==null
		} // token !=null
			// filters
		filterChain.doFilter(request, response);
	}

}
