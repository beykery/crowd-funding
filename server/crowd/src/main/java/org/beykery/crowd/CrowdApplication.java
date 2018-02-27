package org.beykery.crowd;

import org.beykery.crowd.common.vo.Message;

import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 众筹
 */
@SpringCloudApplication
public class CrowdApplication
{

  public static void main(String[] args)
  {
    SpringApplication.run(CrowdApplication.class, args);
  }

  @Bean
  LoadBalancerInterceptor loadBalancerInterceptor(LoadBalancerClient loadBalance)
  {
    return new LoadBalancerInterceptor(loadBalance);
  }

  /**
   * 校验
   *
   * @return
   */
  @Bean
  public MethodValidationPostProcessor methodValidationPostProcessor()
  {
    return new MethodValidationPostProcessor();
  }

  /**
   * 自定义校验失败消息
   *
   * @param e
   * @return
   */
  @ExceptionHandler(value = {ConstraintViolationException.class})
  @ResponseBody
  public Message handleResourceNotFoundException(ConstraintViolationException e)
  {
    Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
    StringBuilder sb = new StringBuilder();
    violations.forEach((violation) ->
    {
      sb.append(violation.getMessage()).append("\n");
    });
    Message m = new Message();
    m.setErr(sb.toString());
    return m;
  }
}
