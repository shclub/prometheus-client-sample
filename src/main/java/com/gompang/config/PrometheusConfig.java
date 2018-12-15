package com.gompang.config;

import io.prometheus.client.exporter.HTTPServer;
import io.prometheus.client.hotspot.DefaultExports;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.io.IOException;

@Configuration
@Slf4j
public class PrometheusConfig {
    @PostConstruct
    public void init(){
        DefaultExports.initialize(); // Export JVM Metrics into http endpoint
    }

    @Bean
    public HTTPServer prometheusServer() throws IOException {
        log.info("promethues server has started");
        return new HTTPServer(9000);
    }
}
