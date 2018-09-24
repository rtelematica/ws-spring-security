package com.example.demo.security.service.jwt;

import java.util.Map;

/**
 * Creates and validates credentials.
 */
public interface TokenService {

	public String permanent(Map<String, String> attributes);

	public String expiring(Map<String, String> attributes);

	/**
	 * Checks the validity of the given credentials.
	 *
	 * @param token
	 * @return attributes if verified
	 */
	public Map<String, String> untrusted(String token);

	/**
	 * Checks the validity of the given credentials.
	 *
	 * @param token
	 * @return attributes if verified
	 */
	public Map<String, String> verify(String token);
}
