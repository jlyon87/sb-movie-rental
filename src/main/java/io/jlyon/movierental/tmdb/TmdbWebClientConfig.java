package io.jlyon.movierental.tmdb;

import io.jlyon.movierental.MovieRentalApplication;
import io.jlyon.movierental.config.RequestFilter;
import io.jlyon.movierental.config.ResponseFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class TmdbWebClientConfig {
	public static final String WEB_CLIENT_NAME = "tmbd_web_client";
	public static final String BASE_URL = "https://api.themoviedb.org";

	@Autowired
	private TmdbConfig config;

	@Autowired
	private RequestFilter requestLogger;
	@Autowired
	private ResponseFilter responseLogger;

	@Bean(name = WEB_CLIENT_NAME)
	public WebClient.Builder getWebClientBuilder() {
		return WebClient.builder()
			.baseUrl(BASE_URL + "/" + config.getVersion())
			.defaultHeader(HttpHeaders.AUTHORIZATION, "Bearer " + config.getToken())
			.filter(requestLogger.logRequest())
			.filter(responseLogger.logResponse());
	}
}
