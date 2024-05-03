package br.com.ebac.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import javax.validation.Validator;

@Configuration
public class ValidatorConfig {

	@Bean
    public Validator validatorFactory () {
	    return new LocalValidatorFactoryBean();
    }
}
