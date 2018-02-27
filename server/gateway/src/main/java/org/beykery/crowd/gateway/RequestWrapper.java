/**
 * 包装request
 */
package org.beykery.crowd.gateway;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author beykery
 */
public class RequestWrapper extends HttpServletRequestWrapper
{

    /**
     * Logger
     */
    private static final Logger LOG = LoggerFactory.getLogger(RequestWrapper.class);
    /**
     * 源请求
     */
    private HttpServletRequest src;
    /**
     * 请求方法
     */
    private String method;
    /**
     * 头
     */
    private Map<String, String> headers;

    /**
     * 包装一个请求
     *
     * @param src
     * @param method
     * @param headers
     */
    public RequestWrapper(HttpServletRequest src, String method, Map<String, String> headers)
    {
        super(src);
        this.src = src;
        this.method = method;
        this.headers = headers;
    }

    /**
     * 方法
     *
     * @return
     */
    @Override
    public String getMethod()
    {
        return method == null ? super.getMethod() : method;
    }

    @Override
    public String getHeader(String name)
    {
        String temp = headers != null ? headers.get(name) : null;
        if (temp == null)
        {
            temp = super.getHeader(name);
        }
        return temp;
    }

    @Override
    public Enumeration<String> getHeaders(String name)
    {
        String temp = headers != null ? headers.get(name) : null;
        if (temp == null)
        {
            return super.getHeaders(name);
        }
        List<String> list = new ArrayList<>();
        list.add(temp);
        return Collections.enumeration(list);
    }

    @Override
    public Enumeration<String> getHeaderNames()
    {
        if (headers == null)
        {
            return super.getHeaderNames();
        }
        Enumeration<String> em = super.getHeaderNames();
        Set<String> set = new HashSet<>();
        while (em.hasMoreElements())
        {
            set.add(em.nextElement());
        }
        for (String item : headers.keySet())
        {
            set.add(item);
        }
        return Collections.enumeration(set);
    }

    public void setHeaders(Map<String, String> headers)
    {
        this.headers = headers;
    }

    public void setMethod(String method)
    {
        this.method = method;
    }

    public void setSrc(HttpServletRequest src)
    {
        this.src = src;
    }
}
