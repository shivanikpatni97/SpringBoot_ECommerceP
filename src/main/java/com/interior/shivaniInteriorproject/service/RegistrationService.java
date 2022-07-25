package com.interior.shivaniInteriorproject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.interior.shivaniInteriorproject.model.User;
import com.interior.shivaniInteriorproject.repository.IUserRepository;

@Service
public class RegistrationService {

	@Autowired
	IUserRepository userRepository;

	
	public void register(User user) {
		userRepository.save(user);
	}
}
