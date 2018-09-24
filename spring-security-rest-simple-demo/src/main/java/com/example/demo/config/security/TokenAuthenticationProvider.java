package com.example.demo.config.security;

import static lombok.AccessLevel.PACKAGE;
import static lombok.AccessLevel.PRIVATE;

import java.util.Optional;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.example.demo.security.service.UserAuthenticationService;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.experimental.FieldDefaults;

@Component
@AllArgsConstructor(access = PACKAGE)
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class TokenAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {

	@NonNull
	private UserAuthenticationService userAuthenticationService;

	@Override
	protected void additionalAuthenticationChecks(final UserDetails d, final UsernamePasswordAuthenticationToken auth) {
		// Nothing to do
	}

	@Override
	protected UserDetails retrieveUser(final String username,
			final UsernamePasswordAuthenticationToken authentication) {

		final Object token = authentication.getCredentials();

		return Optional
				.ofNullable(token)
				.map(String::valueOf)
				.flatMap(userAuthenticationService::findByToken)
				.orElseThrow(
						() -> new UsernameNotFoundException("Cannot find user with authentication token=" + token));
	}
}