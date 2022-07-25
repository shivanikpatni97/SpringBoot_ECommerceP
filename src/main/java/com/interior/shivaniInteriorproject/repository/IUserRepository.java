package com.interior.shivaniInteriorproject.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.interior.shivaniInteriorproject.model.User;

@Repository
public interface IUserRepository extends JpaRepository<User, Integer>{
	Optional<User> findUserByEmail(String email);
}
