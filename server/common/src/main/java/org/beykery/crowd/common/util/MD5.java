package org.beykery.crowd.common.util;

import java.security.*;
import java.io.*;

/**
 * md5
 *
 * @author beykery
 */
public class MD5
{

    /**
     * md5指纹
     *
     * @param sDataIn
     * @return
     */
    public static String md5(final String sDataIn)
    {
        MessageDigest md5 = null;
        try
        {
            md5 = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }
        byte[] byt = null;
        try
        {
            byt = sDataIn.getBytes("UTF-8");
        } catch (UnsupportedEncodingException e2)
        {
            e2.printStackTrace();
        }
        final byte[] bytHash = md5.digest(byt);
        final String sTemp = bytesToHexString(bytHash);
        return sTemp;
    }

    /**
     * md5
     *
     * @param bytes
     * @return
     */
    public static String md5(final byte[] bytes)
    {
        MessageDigest md5 = null;
        try
        {
            md5 = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }
        final byte[] bytHash = md5.digest(bytes);
        final String sTemp = bytesToHexString(bytHash);
        return sTemp;
    }

    /**
     * input stream to md5 string
     *
     * @param src
     * @return
     * @throws java.io.IOException
     */
    public static String inputSteamToHexString(final InputStream src) throws IOException
    {
        final StringBuilder stringBuilder = new StringBuilder();
        while (true)
        {
            int cur = src.read();
            if (cur != -1)
            {
                final int v = cur & 0xFF;
                final String hv = Integer.toHexString(v);
                if (hv.length() < 2)
                {
                    stringBuilder.append(0);
                }
                stringBuilder.append(hv);
            } else
            {
                break;
            }
        }
        return stringBuilder.toString();
    }

    /**
     * hex string
     *
     * @param src
     * @return
     */
    public static String bytesToHexString(final byte[] src)
    {
        final StringBuilder stringBuilder = new StringBuilder("");
        if (src == null || src.length <= 0)
        {
            return null;
        }
        for (int i = 0; i < src.length; ++i)
        {
            final int v = src[i] & 0xFF;
            final String hv = Integer.toHexString(v);
            if (hv.length() < 2)
            {
                stringBuilder.append(0);
            }
            stringBuilder.append(hv);
        }
        return stringBuilder.toString();
    }
}
