package com.ziyu.tender;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.ziyu.tender.config.AppConfig;
import com.ziyu.tender.utils.DateUtils;
import com.ziyu.tender.utils.GkzbUtils;
import com.ziyu.tender.utils.XjggUtils;

@MapperScan("com.ziyu.tender.*.mapper")
@SpringBootApplication
public class TenderApplication {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(TenderApplication.class, args);
		
//		Date now = new Date();
//		List<String> pages = new ArrayList<>();
//		pages.add(AppConfig.getGkzbUrl(1, DateUtils.toString(now, DateUtils.DEFAULT_DATE_FORMAT), DateUtils.toString(now, DateUtils.DEFAULT_DATE_FORMAT)));
//		GkzbUtils.start(pages);
//		
//		List<String> pagesb = new ArrayList<>();
//		pagesb.add(AppConfig.getXjggUrl(1, DateUtils.toString(now, DateUtils.DEFAULT_DATE_FORMAT), DateUtils.toString(now, DateUtils.DEFAULT_DATE_FORMAT)));
//		XjggUtils.start(pagesb);
	}
}
