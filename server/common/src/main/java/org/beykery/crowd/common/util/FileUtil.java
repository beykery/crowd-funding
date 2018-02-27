/**
 * 文件
 */
package org.beykery.crowd.common.util;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.*;

/**
 * @author beykery
 */
public class FileUtil
{

  /**
   * LOG
   */
  private static final Logger LOG = LoggerFactory.getLogger(FileUtil.class);

  /**
   * 读取文件内容
   *
   * @param f
   * @return
   */
  public static byte[] content(File f)
  {
    FileInputStream fis = null;
    try
    {
      fis = new FileInputStream(f);
      ByteArrayOutputStream bos = new ByteArrayOutputStream();
      byte[] buf = new byte[1024];
      int len;
      while ((len = fis.read(buf)) > 0)
      {
        bos.write(buf, 0, len);
      }
      return bos.toByteArray();
    } catch (IOException ex)
    {
      LOG.error(f + "读取失败");
    } finally
    {
      if (fis != null)
      {
        try
        {
          fis.close();
        } catch (IOException ex)
        {
        }
      }
    }
    return null;
  }

  /**
   * 获取文件内容，分行
   *
   * @param file
   * @return
   */
  public static String[] getLines(File file)
  {
    BufferedReader br = null;
    try
    {
      br = new BufferedReader(new FileReader(file));
      List<String> lines = new ArrayList<>();
      String line;
      while ((line = br.readLine()) != null)
      {
        lines.add(line);
      }
      String[] r = new String[lines.size()];
      for (int i = 0; i < r.length; i++)
      {
        r[i] = lines.get(i);
      }
      return r;
    } catch (Exception e)
    {
      LOG.error("读取文件内容失败");
      LOG.error(ExceptionUtil.stackTrace(e));
    } finally
    {
      if (br != null)
      {
        try
        {
          br.close();
        } catch (IOException ex)
        {
          LOG.error("无法关闭文件：" + file);
        }
      }
    }
    return null;
  }

  /**
   * 写入文件内容
   *
   * @param d
   * @param content
   */
  public static void write(File d, byte[] content)
  {
    OutputStream os = null;
    try
    {
      os = new FileOutputStream(d);
      os.write(content);
    } catch (IOException ex)
    {
      LOG.error("写入文件 " + d + " 失败");
    } finally
    {
      try
      {
        if (os != null)
        {
          os.close();
        }
      } catch (IOException ie)
      {
      }
    }
  }

  /**
   * 写入文件
   *
   * @param d
   * @param content
   */
  public static void write(File d, String content)
  {
    write(d, content, "utf-8");
  }

  /**
   * 写入文件
   *
   * @param d
   * @param content
   * @param encoding
   */
  public static void write(File d, String content, String encoding)
  {
    try
    {
      byte[] bs = content.getBytes(encoding);
      write(d, bs);
    } catch (UnsupportedEncodingException ex)
    {
    }
  }

  /**
   * 添加文件内容
   *
   * @param d
   * @param content
   */
  public static void append(File d, byte[] content)
  {
    d.getParentFile().mkdirs();
    FileOutputStream fos = null;
    try
    {
      fos = new FileOutputStream(d, true);
      fos.write(content);
      fos.flush();
    } catch (Exception e)
    {

    } finally
    {
      if (fos != null)
      {
        try
        {
          fos.close();
        } catch (IOException ex)
        {
        }
      }
    }
  }

  /**
   * 分割
   *
   * @param s
   * @param c
   * @return
   */
  public static String[] split(String s, char c)
  {
    s = s.trim();
    List<String> r = new ArrayList<>();
    int i = 0, j;
    while (i < s.length())
    {
      j = s.indexOf(c, i);
      if (j > 0)
      {
        String temp = s.substring(i, j).trim();
        if (temp.length() > 0)
        {
          r.add(temp);
        }
        i = j + 1;
      } else
      {
        break;
      }
    }
    if (i > 0 && i < s.length())
    {
      String temp = s.substring(i).trim();
      if (temp.length() > 0)
      {
        r.add(s.substring(i));
      }
    } else
    {
      r.add(s.trim());
    }
    String[] ss = new String[r.size()];
    for (i = 0; i < ss.length; i++)
    {
      ss[i] = r.get(i);
    }
    return ss;
  }
}
