package org.example.gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

@Configuration
public class GlobalCorsConfig {

    @Bean
    public CorsWebFilter corsWebFilter() {
        CorsConfiguration config = new CorsConfiguration();

        // 允许的前端来源（可写具体地址，开发时用 * 或 localhost）
        config.addAllowedOriginPattern("http://localhost:5173"); // Vue 开发环境端口
        config.setAllowCredentials(true); // 允许携带 cookie、token
        config.addAllowedHeader("*");    // 允许所有请求头
        config.addAllowedMethod("*");    // 允许所有 HTTP 方法（GET、POST、PUT、DELETE 等）

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config); // 对所有请求路径生效
        return new CorsWebFilter(source);
    }
}