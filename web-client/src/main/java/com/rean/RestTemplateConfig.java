package com.rean;

import org.apache.hc.client5.http.config.RequestConfig;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManager;
import org.apache.hc.core5.util.Timeout;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {

    @Bean
    public RestTemplate restTemplate() {

        //RestTemplate restTemplate = new RestTemplate();

        return new RestTemplateBuilder().requestFactory(this::requestFactory).build();
    }

    public HttpComponentsClientHttpRequestFactory requestFactory() {

        RequestConfig requestConfig = RequestConfig.custom()
                .setResponseTimeout(Timeout.ofSeconds(35))
                .setConnectionRequestTimeout(Timeout.ofSeconds(35))
                .build();

        PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager();
        connectionManager.setMaxTotal(5);
        connectionManager.setDefaultMaxPerRoute(5);

        CloseableHttpClient httpClient = HttpClients.custom()
                .setConnectionManager(connectionManager)
                .setDefaultRequestConfig(requestConfig)
                .build();

        return new HttpComponentsClientHttpRequestFactory(httpClient);
    }



    // Work on Spring 2.XX

    /* @Bean
    public RestTemplate restTemplate() {
        return new RestTemplateBuilder().requestFactory(this::requestFactory).build();
    }

    private HttpComponentsClientHttpRequestFactory requestFactory() {

        RequestConfig requestConfig = RequestConfig.custom().setConnectionRequestTimeout(5000).setSocketTimeout(5000)
                .setConnectTimeout(5000).build();
        PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager();
        connectionManager.setMaxTotal(5);
        connectionManager.setDefaultMaxPerRoute(5);

        CloseableHttpClient httpClient = HttpClientBuilder.create().setConnectionManager(connectionManager)
                .setDefaultRequestConfig(requestConfig).build();
        return new HttpComponentsClientHttpRequestFactory(httpClient);
    }*/

}



