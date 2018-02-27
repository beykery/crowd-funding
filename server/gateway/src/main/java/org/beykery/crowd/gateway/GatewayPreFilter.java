/**
 * 过滤器
 */
package org.beykery.crowd.gateway;

import com.netflix.zuul.ZuulFilter;
import javax.servlet.http.HttpServletRequest;
import com.netflix.zuul.context.RequestContext;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author beykery
 */
public class GatewayPreFilter extends ZuulFilter
{

    private static final Logger LOG = LoggerFactory.getLogger(GatewayPreFilter.class);

    @Override
    public String filterType()
    {
        return "pre";
    }

    @Override
    public int filterOrder()
    {
        return 0;
    }

    @Override
    public boolean shouldFilter()
    {
        return true;
    }

    @Override
    public Object run()
    {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        if (request.getCharacterEncoding() == null)
        {
            try
            {
                request.setCharacterEncoding("UTF-8");
            } catch (UnsupportedEncodingException ex)
            {
                LOG.error("utf-8不支持");
            }
        }
        RequestWrapper rw = null;//包装
        String path = request.getRequestURI();
        String method = request.getMethod();
        if (method.equalsIgnoreCase("POST") && "/uaa/oauth/token".equals(path))//token请求
        {
            String contentType = request.getHeader("content-type");
            if (!"application/x-www-form-urlencoded".equals(contentType))//如果不是urlencoded，
            {
                LOG.info("error content-type : " + contentType);
                Map<String, String> map = new HashMap<>();
                map.put("content-type", "application/x-www-form-urlencoded");
                rw = new RequestWrapper(request, null, map);
            }
        }
        //method
        method = request.getParameter("_method");
        if (method != null && !method.isEmpty())
        {
            if (rw == null)
            {
                rw = new RequestWrapper(request, method.toUpperCase(), null);
            } else
            {
                rw.setMethod(method.toUpperCase());
            }
        }
        if (rw != null)
        {
            ctx.setRequest(rw);
        }
        String ip = getIpAddr(request);
        LOG.info("ip : " + ip + " : " + path);
        return null;
    }

    /**
     * ip
     *
     * @param request
     * @return
     */
    private static String getIpAddr(HttpServletRequest request)
    {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
        {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
        {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
        {
            ip = request.getRemoteAddr();
        }
        return ip;
    }
}
