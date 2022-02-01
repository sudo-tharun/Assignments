package com.tharun.application;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
//import org.springframework.data.repository.CrudRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.stereotype.Repository;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete


@RedisHash
public interface UserRepository extends JpaRepository<User, String> {
	
//	@Query("SELECT * from User where lastname = :name and ")
//	List<User> findByLastNameAndFirstName(String name, String name1); // SELECT * from daskdh where lastname = '' and firstName '
	
	@Query("from User where email like %:email%")
	List<User> findByEmail(String email);
	
	List<User> findByContact(String contact);
	
	@Query("from User where firstname like %:fname% or lastname like %:lname%")
	List<User> findByName(String fname,String lname);
	
	@Transactional
	@Modifying
	@Query("delete from User where email=:email")
	void deleteUserByEmail(String email);
	
	@Query("from User where email=:param or contact=:param1")
	User findByEmailOrPhone(String param,String param1);
	
	
}