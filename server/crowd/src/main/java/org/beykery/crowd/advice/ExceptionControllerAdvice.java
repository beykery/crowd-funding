package org.beykery.crowd.advice;

import org.beykery.crowd.common.util.ExceptionUtil;
import org.beykery.crowd.common.vo.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局错误信息处理
 *
 * @author beykery
 */
@RestControllerAdvice
public class ExceptionControllerAdvice
{

    /**
     * logger
     */
    private static final Logger LOG = LoggerFactory.getLogger(ExceptionControllerAdvice.class);

    /**
     * 所有的异常
     *
     * @param ex
     * @return
     */
    @ExceptionHandler(Exception.class)
    public Message bindExceptionHandler(Exception ex)
    {
        LOG.error(ExceptionUtil.stackTrace(ex));
        Message m = new Message();
        m.setErr(ex.getMessage());
        m.setPayload(ex);
        return m;
    }
}
