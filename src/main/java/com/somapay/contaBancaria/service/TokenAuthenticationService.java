package com.somapay.contaBancaria.service;

import java.util.Collections;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class TokenAuthenticationService {
	private static String chave = "Segredo";

	private static final long tempoDeExpiracao = 3600000;

	private static final String prefixo = "Bearer";
	private static final String headerString = "Authorization";

	public static void addAuthentication(HttpServletResponse response, String username) {
		String JWT = Jwts.builder().setSubject(username)
				.setExpiration(new Date(System.currentTimeMillis() + tempoDeExpiracao))
				.signWith(SignatureAlgorithm.HS512, chave).compact();

		response.addHeader(headerString, prefixo + " " + JWT);
	}

	public static Authentication getAuthentication(HttpServletRequest request) {
		String token = request.getHeader(headerString);

		if (token != null) {
			String user = Jwts.parser().setSigningKey(chave).parseClaimsJws(token.replace(prefixo, "")).getBody()
					.getSubject();

			if (user != null) {
				return new UsernamePasswordAuthenticationToken(user, null, Collections.emptyList());
			}
		}
		return null;
	}
}
