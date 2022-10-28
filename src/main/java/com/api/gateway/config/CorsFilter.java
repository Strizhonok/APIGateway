/**
 * @author dzaripov
 * @create 2022-09-06 17:10
 */
package com.api.gateway.config;

import java.util.Arrays;
import java.util.List;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;
/*
@Component
public class CorsFilter implements WebFilter {

    @Override
    public Mono<Void> filter(ServerWebExchange serverWebExchange, WebFilterChain webFilterChain) {
        final HttpHeaders headers = serverWebExchange.getRequest().getHeaders();

        headers.setAccessControlAllowOrigin("*");
        headers.setAccessControlAllowMethods(Arrays.stream(HttpMethod.values()).toList());
        headers.setAccessControlAllowHeaders(List.of("*"));

        return webFilterChain.filter(serverWebExchange);
    }

}
*/
