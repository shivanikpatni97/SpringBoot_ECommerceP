package com.interior.shivaniInteriorproject.config;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.interior.shivaniInteriorproject.model.Role;
import com.interior.shivaniInteriorproject.model.User;
import com.interior.shivaniInteriorproject.repository.IUserRepository;
import com.interior.shivaniInteriorproject.repository.RoleRepository;

@Component
public class GoogleOAuth2SuccessHandler implements AuthenticationSuccessHandler{

	@Autowired
	IUserRepository userRepository;

	@Autowired
	RoleRepository roleRepository;

	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {

		OAuth2AuthenticationToken authToken = (OAuth2AuthenticationToken) authentication;
		String email = authToken.getPrincipal().getAttribute("email").toString();
		if(!userRepository.findUserByEmail(email).isPresent()) {
			User user = new User();
			user.setFirstName(authToken.getPrincipal().getAttributes().get("given_name").toString());
			user.setLastName(authToken.getPrincipal().getAttributes().get("family_name").toString());
			user.setEmail(email);
			List<Role> roles = new ArrayList<>();
			roles.add(roleRepository.findById(2).get());
			user.setRoles(roles);
			userRepository.save(user);
		}
		redirectStrategy.sendRedirect(request, response, "/");

	}


}
