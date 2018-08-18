package com.ziyu.tender.utils;

import java.util.List;

import com.ziyu.tender.config.AppConfig;
import com.ziyu.tender.crawler.BasicGkzbGetDataCrawler;
import com.ziyu.tender.crawler.BasicGkzbPageSizeCrawler;

import edu.uci.ics.crawler4j.crawler.CrawlConfig;
import edu.uci.ics.crawler4j.crawler.CrawlController;
import edu.uci.ics.crawler4j.fetcher.PageFetcher;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtConfig;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtServer;

public class GkzbUtils {

	public static void start(List<String> pages) throws Exception{
		
		CrawlConfig config = new CrawlConfig();
		config.setCrawlStorageFolder(AppConfig.getCrawlstorageFolder());
		config.setPolitenessDelay(1000);
		config.setMaxDepthOfCrawling(2);
		config.setMaxPagesToFetch(1000);
		config.setIncludeBinaryContentInCrawling(false);
		config.setResumableCrawling(false);
		
		PageFetcher pageFetcher = new PageFetcher(config);
        RobotstxtConfig robotstxtConfig = new RobotstxtConfig();
        RobotstxtServer robotstxtServer = new RobotstxtServer(robotstxtConfig, pageFetcher);
        CrawlController controller = new CrawlController(config, pageFetcher, robotstxtServer);
        
        for(String page : pages){
        	controller.addSeed(page);
        }
        
        controller.start(BasicGkzbPageSizeCrawler.class, 1);
		
        controller.waitUntilFinish();
        
        System.err.println("==============爬虫结束==========");
	}
	
	
	public static void startGetData(List<String> pages) throws Exception{
		
		CrawlConfig config = new CrawlConfig();
		config.setCrawlStorageFolder(AppConfig.getCrawlstorageFolder());
		config.setPolitenessDelay(1000);
		config.setMaxDepthOfCrawling(2);
		config.setMaxPagesToFetch(1000);
		config.setIncludeBinaryContentInCrawling(false);
		config.setResumableCrawling(false);
		
		PageFetcher pageFetcher = new PageFetcher(config);
        RobotstxtConfig robotstxtConfig = new RobotstxtConfig();
        RobotstxtServer robotstxtServer = new RobotstxtServer(robotstxtConfig, pageFetcher);
        CrawlController controller = new CrawlController(config, pageFetcher, robotstxtServer);
        
        for(String page : pages){
        	controller.addSeed(page);
        }
        
        controller.start(BasicGkzbGetDataCrawler.class, AppConfig.getCrawlersNumbers());
		
	}
	
}
