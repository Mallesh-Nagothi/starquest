/**
 * 
 */
package com.starquest.usermgmt.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @author mallesh
 *
 */
@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {

	public void configurePathMatch(PathMatchConfigurer configurer){
		configurer.setUseSuffixPatternMatch(false);
	}
	
}
