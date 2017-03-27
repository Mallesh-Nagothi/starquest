/**
 * 
 */
package com.starquest.usermgmt.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.starquest.usermgmt.domain.Login;

/**
 * @author mallesh
 * @since Mar/2017
 * @version 1.0
 */
@Repository
public interface LoginRepository extends CrudRepository<Login, Integer> {

}
