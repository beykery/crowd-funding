package org.beykery.crowd;

import javax.servlet.http.*;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * spring
 *
 * @author beykery
 */
@Component
public class Spring implements ApplicationContextAware
{

  private static ApplicationContext applicationContext = null;

  @Override
  public void setApplicationContext(ApplicationContext applicationContext) throws BeansException
  {
    if (Spring.applicationContext == null)
    {
      Spring.applicationContext = applicationContext;
    }
  }

  /**
   * context
   *
   * @return
   */
  public static ApplicationContext applicationContext()
  {
    return applicationContext;
  }

  /**
   * bean
   *
   * @param name
   * @return
   */
  public static Object bean(String name)
  {
    return applicationContext().getBean(name);
  }

  /**
   * bean
   *
   * @param <T>
   * @param clazz
   * @return
   */
  public static <T> T bean(Class<T> clazz)
  {
    return applicationContext().getBean(clazz);
  }

  /**
   * bean
   *
   * @param <T>
   * @param name
   * @param clazz
   * @return
   */
  public static <T> T bean(String name, Class<T> clazz)
  {
    return applicationContext().getBean(name, clazz);
  }

  /**
   * 当前的请求
   *
   * @return
   */
  public static HttpServletRequest curRequest()
  {
    RequestAttributes ra = RequestContextHolder.getRequestAttributes();
    ServletRequestAttributes sra = (ServletRequestAttributes) ra;
    HttpServletRequest request = sra.getRequest();
    return request;
  }


  /**
   * 当前的请求
   *
   * @return
   */
  public static HttpServletResponse curResponse()
  {
    RequestAttributes ra = RequestContextHolder.getRequestAttributes();
    ServletRequestAttributes sra = (ServletRequestAttributes) ra;
    HttpServletResponse response = sra.getResponse();
    return response;
  }

}
