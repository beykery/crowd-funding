package org.beykery.crowd.config;

import org.beykery.crowd.common.json.FastJsonConverter;
import java.util.List;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * 配置fastjson
 *
 * @author beykery
 */
@Configuration
public class WebMvcConfigurer extends WebMvcConfigurerAdapter
{

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters)
    {
        converters.add(new FastJsonConverter());
        super.configureMessageConverters(converters);
    }
}
