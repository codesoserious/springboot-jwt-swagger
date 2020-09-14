package com.lead.config;

import com.lead.bean.BaseResult;
import com.lead.execption.CheckException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常拦截配置
 *
 * @author mac
 */
@RestControllerAdvice
public class ExceptionHandlerConfig {


    private Logger log = LoggerFactory.getLogger(ExceptionHandlerConfig.class);

    /**
     * 全局运行时异常捕捉
     *
     * @param e
     * @return
     */
    @ExceptionHandler(value = RuntimeException.class)
    public BaseResult executeException(RuntimeException e) {
        log.info("RuntimeException为{}", e.getMessage());
        System.out.println("e = " + e);
        return BaseResult.getException(e.getMessage());
    }

    /**
     * 自定义异常捕捉
     *
     * @param e
     * @return
     */
    @ExceptionHandler(value = CheckException.class)
    public BaseResult executeException(CheckException e) {
        log.info("CheckException为{}", e.getMessage());
        return BaseResult.getException(e.getMessage());
    }

}
