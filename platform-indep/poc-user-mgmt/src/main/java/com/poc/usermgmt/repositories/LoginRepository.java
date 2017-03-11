/**
 * 
 */
package com.poc.usermgmt.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.poc.usermgmt.domain.Login;

/**
 * @author mallesh
 *
 */
@Repository
public interface LoginRepository extends CrudRepository<Login, Integer> {

}
