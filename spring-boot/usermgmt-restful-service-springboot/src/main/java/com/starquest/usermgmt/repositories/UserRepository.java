package com.starquest.usermgmt.repositories;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.starquest.usermgmt.domain.User;

/**
 * @author mallesh
 * @since Mar/2017
 * @version 1.0
 */
@Repository
public interface UserRepository extends PagingAndSortingRepository<User, Integer> {

	
	List<User> findByLastName(String lastName);
	
	List<User> findByFirstName(String firstName);
	
	User findUserById(Integer id);
	
	List<User> findUserByEmailAddress(String emailAddress);
	
}
