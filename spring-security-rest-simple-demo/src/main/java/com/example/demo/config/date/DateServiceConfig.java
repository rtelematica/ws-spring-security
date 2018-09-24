package com.example.demo.config.date;

import org.joda.time.DateTimeZone;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.demo.date.service.DateService;
import com.example.demo.date.service.impl.JodaDateService;

@Configuration
public class DateServiceConfig {

	@Bean
	public DateService dateService() {
		return new JodaDateService(defaultTimeZone());
	}

	@Bean
	public DateTimeZone defaultTimeZone() {
		return DateTimeZone.UTC;
	}
}
