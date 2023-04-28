package com.vinothit.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vinothit.entity.UserDetailsEntity;

public interface UserDetailsRepository extends JpaRepository<UserDetailsEntity, Integer> {

	
	 public UserDetailsEntity findByUserEmail(String email);
	 public UserDetailsEntity findByPassword(String password);
	 public UserDetailsEntity findByUserEmailAndPassword(String username, String Password);
}
