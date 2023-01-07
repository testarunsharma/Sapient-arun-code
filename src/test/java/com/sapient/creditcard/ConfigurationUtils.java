package com.sapient.creditcard;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.sapient.creditcard.controller.CreditCardController;

@Configuration
public class ConfigurationUtils {
	
    @Bean
    public CreditCardController creditCardController() {
        return new CreditCardController();
    }

	  @Bean
    public CreditCardManagementService creditCardService() {
		  CreditCardManagementService.init();
        return new CreditCardManagementService();
    }
	
}
