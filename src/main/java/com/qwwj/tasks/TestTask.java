package com.qwwj.tasks;

import java.text.SimpleDateFormat;
import java.util.Date;

//import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class TestTask {
	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

	//定义每过3秒执行任务
	//@Scheduled(fixedRate = 3000)
	//@Scheduled(cron = "5,15,26,36,45,55 * * * * ?")//http://cron.qqe2.com
    public void reportCurrentTime() {
        System.out.println("现在时间：" + dateFormat.format(new Date()));
    }
}
