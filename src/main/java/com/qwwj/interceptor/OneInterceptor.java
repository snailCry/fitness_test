package com.qwwj.interceptor;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.qwwj.pojo.JSONResult;
import com.qwwj.utils.JsonUtils;

public class OneInterceptor implements HandlerInterceptor {
	/**
	 * 在请求处理之前调用（Controller方法调用之前）
	 */
	@Override
	public boolean preHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2) throws Exception {
		System.out.println("被one拦截，放行...");
		return true;//被拦截返回false,放行返回true
		
		/*if (true) {
			returnErrorResponse(response, IMoocJSONResult.errorMsg("被one拦截..."));
		}
		return false;*/
	}
	/**
	 * 请求处理之后调用，但是在视图被渲染之前（Controller方法调用之后）
	 */
	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
		// TODO Auto-generated method stub
		
	}
	/**
	 * 在整个请求结束之后调用，也就是在DispatcherServlet渲染了对应的视图之后执行，主要用于进行资源清理工作
	 */
	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub

	}
	/**
	 * ajax请求被拦截返回信息
	 */
	public void returnErrorResponse(HttpServletResponse response, JSONResult result) throws UnsupportedEncodingException, IOException {
		OutputStream out=null;
		try{
		    response.setCharacterEncoding("utf-8");
		    response.setContentType("text/json");
		    out = response.getOutputStream();
		    out.write(JsonUtils.objectToJson(result).getBytes("utf-8"));
		    out.flush();
		} finally{
		    if(out!=null){
		        out.close();
		    }
		}
	}

}
