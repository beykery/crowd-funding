/**
 * 返回给前端的消息
 */
package org.beykery.crowd.common.vo;

import lombok.Data;

/**
 *
 * @author beykery
 */
@Data
public class Message
{

    /**
     * 成功
     */
    private boolean success;
    private String err;
    private Object payload;

    public Message(boolean success, String err)
    {
        this.success = success;
        this.err = err;
    }

    public Message()
    {
    }

    /**
     * 成功消息
     *
     * @return
     */
    public static Message ok(Object payload)
    {
        return new Message(true, null);
    }

    /**
     * 失败
     *
     * @param err
     * @return
     */
    public static Message err(String err)
    {
        return new Message(false, err);
    }
}
