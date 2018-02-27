/**
 * 修改fast json的一个bug
 */
package org.beykery.crowd.common.json;

import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import java.io.IOException;
import org.springframework.http.HttpOutputMessage;

/**
 *
 * @author beykery
 */
public class FastJsonConverter extends FastJsonHttpMessageConverter
{

    @Override
    protected void writeInternal(Object object, HttpOutputMessage outputMessage) throws IOException
    {
        if (object instanceof String)
        {
            String content = (String) object;
            outputMessage.getBody().write(content.getBytes("utf-8"));
        } else
        {
            super.writeInternal(object, outputMessage);
        }
    }

}
