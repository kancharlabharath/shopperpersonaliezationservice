package com.bha.shopperpersonalizationservice;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ShopperpersonalizationserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShopperpersonalizationserviceApplication.class, args);
	}

	/*
	 * @Bean public ModelMapper modelMapper() { return new ModelMapper(); }
	 */

	@Bean
	public ModelMapper modelMapper() {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setFieldMatchingEnabled(true)
				.setFieldAccessLevel(org.modelmapper.config.Configuration.AccessLevel.PRIVATE)
				.setMatchingStrategy(MatchingStrategies.STRICT);
		return modelMapper;
	}

}
