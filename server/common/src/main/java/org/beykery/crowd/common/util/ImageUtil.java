/**
 * 图片
 */
package org.beykery.crowd.common.util;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.image.CropImageFilter;
import java.awt.image.FilteredImageSource;
import java.awt.image.ImageFilter;
import java.awt.image.ImageObserver;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;

/**
 * @author Administrator
 */
public class ImageUtil
{

  public static final int TRANS_NONE = 0;
  public static final int TRANS_ROT90 = 5;
  public static final int TRANS_ROT180 = 3;
  public static final int TRANS_ROT270 = 6;
  public static final int TRANS_MIRROR = 2;
  public static final int TRANS_MIRROR_ROT90 = 7;
  public static final int TRANS_MIRROR_ROT180 = 1;
  public static final int TRANS_MIRROR_ROT270 = 4;
  public static final int HCENTER = 1;
  public static final int VCENTER = 2;
  public static final int LEFT = 4;
  public static final int RIGHT = 8;
  public static final int TOP = 16;
  public static final int BOTTOM = 32;
  public static final int BASELINE = 64;

  public static Image getImage(URLClassLoader loader, String filePath)
  {
    URL url = loader.findResource(filePath);
    return new ImageIcon(url).getImage();
  }

  public static ImageIcon getImageIcon(URLClassLoader loader, String filePath)
  {
    URL url = loader.findResource(filePath);
    //   new ImageIcon("").getImage();
    return new ImageIcon(url);
  }

  public static Image getImage(String fileName)
  {
    return new ImageIcon(fileName).getImage();

  }

  public static ImageIcon getImageIcon(String fileName)
  {
    return new ImageIcon(fileName);
  }

  public static Image getScaledImage(Image src, ImageObserver observer, float scale)
  {
    //    Image scaledImage = src.getScaledInstance(100, 100, Image.SCALE_DEFAULT);//得到一个100X100的图像
    //      Image doubledImage = src.getScaledInstance(src.getWidth(observer) * 2, src.getHeight(observer) * 2, Image.SCALE_DEFAULT);//
    return src.getScaledInstance((int) (src.getWidth(observer) * scale), (int) (src.getHeight(observer) * scale), Image.SCALE_DEFAULT);
  }

  /**
   * @param w     缩放后的宽度
   * @param h     缩放后的高度
   * @param image 原来的图片
   * @return
   */
  public static Image getScaledImage(int w, int h, Image image)
  {
    return image.getScaledInstance(w, h, Image.SCALE_FAST);
  }

  public static Image getCorpImage(Image src, int x, int y, int width, int height)
  {
    Image croppedImage;
    ImageFilter cropFilter;
    cropFilter = new CropImageFilter(x, y, width, height);//四个参数分别为图像起点坐标和宽高，即CropImageFilter(int x,int y,int width,int height)，详细情况请参考API
    croppedImage = Toolkit.getDefaultToolkit().createImage(new FilteredImageSource(src.getSource(), cropFilter));
    return croppedImage;
  }

  private static Logger log = Logger.getLogger("log");

  public static Image loadImg(String strName)
  {
    Image img = null;
    try
    {
      if (!strName.endsWith(".png"))
      {
        strName += ".png";
      }
      img = Toolkit.getDefaultToolkit().createImage(strName);
    } catch (Exception ex)
    {
      log.log(Level.INFO, "error load" + strName + "失败 请确定src目录有此图");
      ex.printStackTrace();
    }
    return img;
  }

  /**
   * 绘制region
   *
   * @param g      绘图上下文
   * @param src    原图
   * @param x
   * @param y
   * @param w
   * @param h
   * @param trans
   * @param dx     目标点
   * @param dy
   * @param anchor 锚点
   */
  public static void drawRegion(Graphics2D g, Image src, int x, int y, int w, int h, int trans, int dx, int dy,
                                int anchor)
  {
    if (src.getWidth(null) <= 0)
    {
      return;
    }
    if (x + w > src.getWidth(null) || y + h > src.getHeight(null) || w < 0 || h < 0 || x < 0 || y < 0)
    {
      throw new IllegalArgumentException("绘制范围超出");
    }
    Image img = src;
    AffineTransform t = new AffineTransform();
    int dW = w, dH = h;
    switch (trans)
    {
      case ImageUtil.TRANS_NONE:
      {
        break;
      }
      case ImageUtil.TRANS_ROT90:
      {
        t.translate((double) h, 0);
        t.rotate(Math.PI / 2);
        dW = h;
        dH = w;
        break;
      }
      case ImageUtil.TRANS_ROT180:
      {
        t.translate(w, h);
        t.rotate(Math.PI);
        break;
      }
      case ImageUtil.TRANS_ROT270:
      {
        t.translate(0, w);
        t.rotate(Math.PI * 3 / 2);
        dW = h;
        dH = w;
        break;
      }
      case ImageUtil.TRANS_MIRROR:
      {
        t.translate(w, 0);
        t.scale(-1, 1);
        break;
      }
      case ImageUtil.TRANS_MIRROR_ROT90:
      {
        t.translate((double) h, 0);
        t.rotate(Math.PI / 2);
        t.translate((double) w, 0);
        t.scale(-1, 1);
        dW = h;
        dH = w;
        break;
      }
      case ImageUtil.TRANS_MIRROR_ROT180:
      {
        t.translate(w, 0);
        t.scale(-1, 1);
        t.translate(w, h);
        t.rotate(Math.PI);
        break;
      }
      case ImageUtil.TRANS_MIRROR_ROT270:
      {
        t.rotate(Math.PI * 3 / 2);
        t.scale(-1, 1);
        dW = h;
        dH = w;
        break;
      }
      default:
        throw new IllegalArgumentException("非法变形参数");
    }
    boolean anchorOk = false;
    if (anchor == 0)
    {
      anchor = TOP | LEFT;
    }
    if ((anchor & 0x7f) != anchor || (anchor & BASELINE) != 0)
    {
      anchorOk = true;
    }
    if ((anchor & TOP) != 0)
    {
      if ((anchor & (VCENTER | BOTTOM)) != 0)
      {
        anchorOk = true;
      }
    } else if ((anchor & BOTTOM) != 0)
    {
      if ((anchor & VCENTER) != 0)
      {
        anchorOk = true;
      } else
      {
        dy -= dH - 1;
      }
    } else if ((anchor & VCENTER) != 0)
    {
      dy -= (dH - 1) >>> 1;
    } else
    {
      anchorOk = true;
    }
    if ((anchor & LEFT) != 0)
    {
      if ((anchor & (HCENTER | RIGHT)) != 0)
      {
        anchorOk = true;
      }
    } else if ((anchor & RIGHT) != 0)
    {
      if ((anchor & HCENTER) != 0)
      {
        anchorOk = true;
      } else
      {
        dx -= dW - 1;
      }
    } else if ((anchor & HCENTER) != 0)
    {
      dx -= (dW - 1) >>> 1;
    } else
    {
      anchorOk = true;
    }
    if (anchorOk)
    {
      throw new IllegalArgumentException("锚点错误");
    }
    AffineTransform savedT = g.getTransform();
    g.translate(dx, dy);
    g.transform(t);
    g.drawImage(img, 0, 0, w, h, x, y, x + w, y + h, null);
    g.setTransform(savedT);
  }

  /**
   * 获取所有切片
   *
   * @param image
   * @return
   */
  public static List<Integer[]> getCells(Image image)
  {
    List<Integer[]> result = new LinkedList<Integer[]>();
    image = new ImageIcon(image).getImage();
    BufferedImage bi = new BufferedImage(image.getWidth(null), image.getHeight(null), BufferedImage.TYPE_INT_ARGB);
    Graphics2D g = (Graphics2D) bi.getGraphics();
    g.drawImage(image, 0, 0, null);
    LinkedList<Pixel> ll = new LinkedList<Pixel>();
    for (int i = 0; i < bi.getHeight(); i++)
    {
      for (int j = 0; j < bi.getWidth(); j++)
      {
        if (bi.getRGB(j, i) != 0)
        {
          Pixel p = new Pixel(j, i);
          ll.add(p);
        }
      }
    }
    for (Pixel p : ll)
    {
      for (int i = -1; i < 2; i++)
      {
        for (int j = -1; j < 2; j++)
        {
          if (!(i == 0 && j == 0))
          {
            Pixel temp = new Pixel(p.x + j, p.y + i);
            if (ll.contains(temp))
            {
              int index = ll.indexOf(temp);
              temp = ll.get(index);
              p.linked[4 + j + i * 3] = temp;
            }
          }
        }
      }
    }
    //现在所有的像素关系都出来了,分析所有像素
    while (ll.size() > 0)
    {
      Pixel p = ll.removeFirst();
      List<Pixel> visitedHistory = new LinkedList<Pixel>();
      count(p, visitedHistory);
      ll.removeAll(visitedHistory);
      Integer[] item = new Integer[4];
      item[0] = minx;
      item[1] = miny;
      item[2] = maxx - minx + 1;
      item[3] = maxy - miny + 1;
      if (item[2] > 1 && item[3] > 1)
      {
        result.add(item);
      }
      minx = Integer.MAX_VALUE;
      miny = Integer.MAX_VALUE;
      maxx = Integer.MIN_VALUE;
      maxy = Integer.MIN_VALUE;
    }
    return result;
  }

  /**
   * 从一个点出发，递归的计算所有相邻点
   *
   * @param p
   * @param minx
   * @param miny
   * @param maxx
   * @param maxy
   * @return
   */
  private static int minx = Integer.MAX_VALUE;
  private static int miny = Integer.MAX_VALUE;
  private static int maxx = Integer.MIN_VALUE;
  private static int maxy = Integer.MIN_VALUE;

  private static void count(Pixel p, List<Pixel> visited)
  {
    visited.add(p);
    if (p.x < minx)
    {
      minx = p.x;
    }
    if (p.x > maxx)
    {
      maxx = p.x;
    }
    if (p.y < miny)
    {
      miny = p.y;
    }
    if (p.y > maxy)
    {
      maxy = p.y;
    }
    for (int i = 0; i < 9; i++)
    {
      if (p.linked[i] != null)
      {
        if (!visited.contains(p.linked[i]))
        {
          count(p.linked[i], visited);
        }
      }
    }
  }

  private static class Pixel
  {

    int x;
    int y;
    Pixel[] linked = new Pixel[9];

    public Pixel(int x, int y)
    {
      this.x = x;
      this.y = y;
    }

    @Override
    public boolean equals(Object obj)
    {
      if (obj instanceof Pixel)
      {
        Pixel p = (Pixel) obj;
        if (p.x == this.x && p.y == this.y)
        {
          return true;
        } else
        {
          return false;
        }
      } else
      {
        return false;
      }
    }

    @Override
    public int hashCode()
    {
      int hash = 3;
      hash = 79 * hash + this.x;
      hash = 79 * hash + this.y;
      return hash;
    }
  }
}
