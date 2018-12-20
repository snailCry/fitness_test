package com.qwwj.exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.ModelAndView;

import com.qwwj.pojo.JSONResult;

@RestControllerAdvice
public class MyExceptionHandler {
	public static final String IMOOC_ERROR_VIEW = "error";

	@ExceptionHandler(value = Exception.class)
    public Object errorHandler(HttpServletRequest request, HttpServletResponse response, Exception e) throws Exception {
    	e.printStackTrace();
    	String header = request.getHeader("X-Requested-With");
    	if (header != null && "XMLHttpRequest".equals(header)) {//ajax请求
    		return JSONResult.errorException(e.getMessage());
    	} else {
    		ModelAndView mav = new ModelAndView();
            mav.addObject("exception", e);
            mav.addObject("url", request.getRequestURL());
            mav.setViewName(IMOOC_ERROR_VIEW);
            return mav;
    	}
    }
	
}
