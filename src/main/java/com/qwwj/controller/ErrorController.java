package com.qwwj.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qwwj.pojo.JSONResult;

@Controller
@RequestMapping("err")
public class ErrorController {

	@RequestMapping("/error")
	public String error() {
		System.out.println(1/0);
		return "error";
	}
	
	@RequestMapping("/ajaxerror")
	public String ajaxerror() {
		return "ajaxerror";
	}
	
	@RequestMapping("/getAjaxerror")
	@ResponseBody
	public JSONResult getAjaxerror() {
		System.out.println(1/0);
		return JSONResult.ok();
	}
}
