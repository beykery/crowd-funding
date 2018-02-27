/**
 * security
 */
package org.beykery.crowd.gateway.config;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 *
 * @author beykery
 */
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter
{

    /**
     * iframe
     *
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception
    {
        http.headers().frameOptions().disable();
        http.csrf().disable();
    }

}
