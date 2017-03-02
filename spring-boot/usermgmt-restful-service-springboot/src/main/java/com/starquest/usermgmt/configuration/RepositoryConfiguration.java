/**
 * 
 */
package com.starquest.usermgmt.configuration;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author mallesh
 *
 */
@Configuration
@EnableAutoConfiguration
@EntityScan(basePackages = {"com.starquest.usermgmt.domain"})
@EnableJpaRepositories(basePackages = {"com.starquest.usermgmt.repositories"})
@EnableTransactionManagement
public class RepositoryConfiguration {

}
