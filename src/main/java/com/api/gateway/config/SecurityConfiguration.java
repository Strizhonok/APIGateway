package com.api.gateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.oauth2.client.oidc.web.server.logout.OidcClientInitiatedServerLogoutSuccessHandler;
import org.springframework.security.oauth2.client.registration.ReactiveClientRegistrationRepository;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.authentication.logout.ServerLogoutSuccessHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsConfigurationSource;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfiguration {

    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http, ServerLogoutSuccessHandler handler/*, CorsConfigurationSource configurationSource*/) {
        http
            .authorizeExchange()
                .pathMatchers("/actuator/**", "/login", "/logout")
                .permitAll()
            .and()
                .authorizeExchange()
                .anyExchange()
                .authenticated()
            .and()
                .oauth2Login();

        //http.cors().configurationSource(configurationSource);

        http
            .logout()
            .logoutSuccessHandler(handler);

        return http.build();
    }


    @Bean
    public ServerLogoutSuccessHandler customLogoutSuccessHandler(
        ReactiveClientRegistrationRepository reactiveClientRegistrationRepository) {

        OidcClientInitiatedServerLogoutSuccessHandler handler = new OidcClientInitiatedServerLogoutSuccessHandler(
            reactiveClientRegistrationRepository);
        handler.setPostLogoutRedirectUri("{baseUrl}/login");

        return handler;
    }
/*
    @Bean
    public CorsConfiguration corsConfiguration() {
        final CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.addAllowedOrigin("http://localhost:8080");
        corsConfiguration.addAllowedOrigin("http://localhost:3000");
        corsConfiguration.applyPermitDefaultValues();
        return corsConfiguration;


      default-filters:
        - DedupeResponseHeader=Access-Control-Allow-Origin Access-Control-Allow-Credentials, RETAIN_UNIQUE
        - TokenRelay=
        - RemoveRequestHeader=Cookie
      globalcors:
        cors-configurations:
          '[/**]':
            allowedOrigins: "*"
            allowedHeaders: "*"
            allowedMethods:
              - GET
              - PUT
              - POST
              - OPTIONS
    }


    @Bean
    public CorsConfigurationSource corsConfigurationSource(CorsConfiguration corsConfiguration) {
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfiguration);
        return source;
    }
*/
}