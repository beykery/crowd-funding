package org.beykery.crowd.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * 网关服务
 *
 * @author beykery
 */
@EnableZuulProxy
@SpringCloudApplication
public class GatewayApplication
{

    public static void main(String[] args)
    {
        SpringApplication.run(GatewayApplication.class, args);
    }

    /**
     * 跨域问题
     *
     * @return
     */
    @Bean
    public CorsFilter corsFilter()
    {
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        final CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOrigin("*");
        config.addAllowedHeader("*");
        config.addAllowedMethod("OPTIONS");
        config.addAllowedMethod("HEAD");
        config.addAllowedMethod("GET");
        config.addAllowedMethod("PUT");
        config.addAllowedMethod("POST");
        config.addAllowedMethod("DELETE");
        config.addAllowedMethod("PATCH");
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }

    /**
     * filter
     *
     * @return
     */
    @Bean
    public GatewayPreFilter preFilter()
    {
        return new GatewayPreFilter();
    }
}
