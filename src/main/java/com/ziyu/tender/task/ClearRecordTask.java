package com.ziyu.tender.task;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.ziyu.tender.tender.service.RecordService;

@Component
public class ClearRecordTask {
	
	@Autowired
	private RecordService recordService;

	@Scheduled(cron = "0 0 0/2 * * ?")
	public void run(){
		try{
			recordService.deleteRecords();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
}
