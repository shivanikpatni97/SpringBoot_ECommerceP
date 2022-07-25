package com.interior.shivaniInteriorproject.config;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.interior.shivaniInteriorproject.model.User;
import com.interior.shivaniInteriorproject.repository.IUserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService{
	
	@Autowired
	private IUserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		
			Optional<User> user = userRepository.findUserByEmail(email);
			user.orElseThrow(() ->new UsernameNotFoundException("User not found"));
			return user.map(CustUserDetails :: new).get();
//			if(user == null) {
//				throw new UsernameNotFoundException("User not found");
//			}
//			else {
//				return new CustUserDetails(user);
//			}
	}

}
