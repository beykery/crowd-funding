package org.beykery.crowd.controller;

import org.beykery.crowd.common.vo.Message;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 众筹controller
 */
@RestController
public class CrowdController
{
  /**
   * 测试下
   *
   * @return
   */
  @GetMapping(value = "/hi")
  public Message hi()
  {
    Message m = Message.ok("hi.");
    return m;
  }
}
