package com.nbnfsoft.admin.config;

import com.nbnfsoft.admin.domain.model.JsonData;
import com.nbnfsoft.admin.utils.FriendlyException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CommonExceptionHandler {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @ExceptionHandler(Exception.class)
    public Object ExceptionHandler(Exception e) {
        logger.error("", e);
        return new JsonData(false, null, "系统内部错误,错误代码【" + e.getCause() + "】");
    }

    @ExceptionHandler(FriendlyException.class)
    public Object FriendlyExceptionHandler(Exception e) {
        logger.error("", e);
        return new JsonData(false, null, e.getMessage());
    }

    /**
     * 自定义验证异常
     */
    @ExceptionHandler(BindException.class)
    public Object validatedBindException(BindException e) {
        String message = e.getAllErrors().get(0).getDefaultMessage();
        return new JsonData(false, null, message);
    }

    /**
     * 自定义验证异常
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Object validExceptionHandler(MethodArgumentNotValidException e) {
        String message = e.getBindingResult().getFieldError().getDefaultMessage();
        return new JsonData(false, null, message);
    }
}
