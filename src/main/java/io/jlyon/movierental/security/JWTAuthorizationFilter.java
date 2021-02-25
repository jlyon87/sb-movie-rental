package io.jlyon.movierental.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

import static io.jlyon.movierental.security.SecurityConstants.BEARER_PREFIX;

public class JWTAuthorizationFilter extends BasicAuthenticationFilter {
	private final SecurityConfig securityConfig;

	public JWTAuthorizationFilter(AuthenticationManager authManager, SecurityConfig sc) {
		super(authManager);
		this.securityConfig = sc;
	}

	@Override
	protected void doFilterInternal(
		HttpServletRequest req,
		HttpServletResponse res,
		FilterChain chain) throws IOException, ServletException {

		String header = req.getHeader(HttpHeaders.AUTHORIZATION);
		if (header == null || !header.startsWith(BEARER_PREFIX)) {
			chain.doFilter(req, res);
			return;
		}

		UsernamePasswordAuthenticationToken authentication = getAuthentication(req);

		SecurityContextHolder.getContext().setAuthentication(authentication);
		chain.doFilter(req, res);
	}

	private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest req) {
		String token = req.getHeader(HttpHeaders.AUTHORIZATION);
		// TODO - Need a String Util
		if (token != null && !token.isBlank() && !token.isEmpty()) {
			String jwsString = token.replace(BEARER_PREFIX, "");

			Jws<Claims> jws = Jwts
				.parserBuilder()
				.setSigningKey(securityConfig.getSecretKey())
				.build()
				.parseClaimsJws(jwsString);

			String user = jws.getBody().getSubject();

			if (user != null) {
				// new ArrayList means authorities/scopes
				return new UsernamePasswordAuthenticationToken(user, null, new ArrayList<>());
			}
		}
		return null;
	}
}
