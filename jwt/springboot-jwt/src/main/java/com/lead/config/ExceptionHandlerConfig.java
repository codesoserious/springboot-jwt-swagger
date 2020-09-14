package com.lead.config;

import com.lead.bean.BaseResult;
import com.lead.execption.CheckException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author mac
 */
@RestControllerAdvice
public class ExceptionHandlerConfig {

    @ExceptionHandler(value = RuntimeException.class)
    public BaseResult executeException(RuntimeException e) {
        System.out.println("e = " + e);
        return BaseResult.getException(e.getMessage());
    }

    @ExceptionHandler(value = CheckException.class)
    @ResponseBody
    public BaseResult executeException(CheckException e) {
        System.out.println("CheckException = " + e.getMessage());
        return BaseResult.getException(e.getMessage());
    }

}
