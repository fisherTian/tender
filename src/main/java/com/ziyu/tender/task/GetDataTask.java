package com.ziyu.tender.task;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.ziyu.tender.config.AppConfig;
import com.ziyu.tender.utils.DateUtils;
import com.ziyu.tender.utils.GkzbUtils;
import com.ziyu.tender.utils.XjggUtils;

@Component
public class GetDataTask {

	@Scheduled(cron = "0 0 */1 * * ?")
//	@Scheduled(cron = "*/10 * * * * ?")
	public void run(){
		try{
			Date now = new Date();
			List<String> pages = new ArrayList<>();
			pages.add(AppConfig.getGkzbUrl(1, DateUtils.toString(now, DateUtils.DEFAULT_DATE_FORMAT), DateUtils.toString(now, DateUtils.DEFAULT_DATE_FORMAT)));
			GkzbUtils.start(pages);
			
			List<String> pagesb = new ArrayList<>();
			pagesb.add(AppConfig.getXjggUrl(1, DateUtils.toString(now, DateUtils.DEFAULT_DATE_FORMAT), DateUtils.toString(now, DateUtils.DEFAULT_DATE_FORMAT)));
			XjggUtils.start(pagesb);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
}
