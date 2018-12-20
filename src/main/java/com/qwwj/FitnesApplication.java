package com.qwwj;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan(basePackages="com.qwwj.mapper")//扫描mybatis mapper 包路径
@ComponentScan(basePackages={"com.qwwj","org.n3r.idworker"})//扫描所需的包，包含一些自用的工具包所在路径
@EnableScheduling//开启定时任务
@EnableAsync//开启异步调用
public class FitnesApplication {

	public static void main(String[] args) {
		SpringApplication.run(FitnesApplication.class, args);
	}

}

