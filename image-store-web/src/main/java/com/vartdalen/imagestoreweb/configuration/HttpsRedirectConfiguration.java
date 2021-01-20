package com.vartdalen.imagestoreweb.configuration;

import java.net.URI;
import java.net.URISyntaxException;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.embedded.netty.NettyReactiveWebServerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;

import reactor.core.publisher.Mono;

@Configuration
public class HttpsRedirectConfiguration {
    
    @Value("${server.port}")
    private int httpsPort;

    @Value("${server.http.port}")
    private int httpPort;

    @PostConstruct
    public void startRedirectServer() {
        NettyReactiveWebServerFactory httpNettyReactiveWebServerFactory = new NettyReactiveWebServerFactory(httpPort);
        httpNettyReactiveWebServerFactory.getWebServer((request, response) -> {
            URI uri = request.getURI();
            URI httpsUri;
            try {
                httpsUri = new URI("https", uri.getUserInfo(), uri.getHost(), httpsPort, uri.getPath(), uri.getQuery(), uri.getFragment());
            } catch (URISyntaxException e) {
                return Mono.error(e);
            }
            response.setStatusCode(HttpStatus.MOVED_PERMANENTLY);
            response.getHeaders().setLocation(httpsUri);
            return response.setComplete();
        }).start();
    }
}