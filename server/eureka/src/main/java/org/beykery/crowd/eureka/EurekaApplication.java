package org.beykery.crowd.eureka;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * 注册服务
 *
 * @author beykery
 */
@SpringBootApplication
@EnableEurekaServer
public class EurekaApplication
{

    public static void main(String[] args)
    {
        new SpringApplicationBuilder(EurekaApplication.class).web(true).run(args);
    }
}
