/**
 * 
 */
package com.starquest.usermgmt.repositories;

import org.springframework.data.repository.CrudRepository;

import com.starquest.usermgmt.domain.Login;

/**
 * @author mallesh
 *
 */
public interface LoginRepository extends CrudRepository<Login, Integer> {

}
