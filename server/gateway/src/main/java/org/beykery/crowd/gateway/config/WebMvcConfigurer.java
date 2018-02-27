package org.beykery.crowd.gateway.config;

import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
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
        converters.add(fastJsonHttpMessageConverterEx());
        super.configureMessageConverters(converters);
    }

    /**
     * fastjson
     *
     * @return
     */
    private FastJsonHttpMessageConverter fastJsonHttpMessageConverterEx()
    {
        return new FastJsonHttpMessageConverter();
    }
}
