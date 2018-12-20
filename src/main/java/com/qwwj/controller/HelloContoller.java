package com.qwwj.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qwwj.pojo.JSONResult;
import com.qwwj.pojo.Resource;

@RestController
public class HelloContoller {

	@Autowired
	private Resource resource;
	
	@RequestMapping("/hello")
	public Object hello() {
		return "hello springboot~~";
	}
	
	@RequestMapping("/getResource")
	public JSONResult getResource() {
		System.out.println(resource);
		Resource bean = new Resource();
		BeanUtils.copyProperties(resource, bean);
		System.out.println(bean);
		return JSONResult.ok(bean);
	}
	
}
