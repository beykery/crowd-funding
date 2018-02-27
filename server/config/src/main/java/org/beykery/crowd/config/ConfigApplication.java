package org.beykery.crowd.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * config 系统
 *
 * @author beykery
 */
@EnableDiscoveryClient
@EnableConfigServer
@SpringBootApplication
public class ConfigApplication
{

    public static void main(String[] args)
    {
        SpringApplication.run(ConfigApplication.class, args);
    }

}
