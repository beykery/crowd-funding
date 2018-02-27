/**
 * 异常工具
 */
package org.beykery.crowd.common.util;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author beykery
 */
public class ExceptionUtil
{

  private static final Logger LOG = LoggerFactory.getLogger(ExceptionUtil.class);

  /**
   * 异常栈
   *
   * @param e
   * @return
   */
  public static String stackTrace(Throwable e)
  {
    try
    {
      ByteArrayOutputStream baos = new ByteArrayOutputStream();
      PrintStream ps = new PrintStream(baos, true, "utf-8");
      e.printStackTrace(ps);
      return baos.toString();
    } catch (Exception ex)
    {
      LOG.error("无法打印异常栈"+ex.getMessage());
      return null;
    }
  }

}
