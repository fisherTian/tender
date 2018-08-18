package com.ziyu.tender.utils;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ziyu.tender.config.AppConfig;
import com.ziyu.tender.crawler.BasicXjggGetDataCrawler;
import com.ziyu.tender.crawler.BasicXjggGetDataDetailCrawler;
import com.ziyu.tender.crawler.BasicXjggPageSizeCrawler;

import edu.uci.ics.crawler4j.crawler.CrawlConfig;
import edu.uci.ics.crawler4j.crawler.CrawlController;
import edu.uci.ics.crawler4j.fetcher.PageFetcher;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtConfig;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtServer;

public class XjggUtils {
	
	protected static final Logger logger = LoggerFactory.getLogger(XjggUtils.class);

	public static void start(List<String> pages) throws Exception{
		
		CrawlConfig config = new CrawlConfig();
		config.setCrawlStorageFolder(AppConfig.getCrawlstorageXjggPageFolder());
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
        
        controller.start(BasicXjggPageSizeCrawler.class, 1);
		
        controller.waitUntilFinish();
        
        logger.info(DateUtils.getNow()+"==============询价公告爬取信息结束==============");
        System.out.println(com.alibaba.fastjson.JSON.toJSON(LocalDataStorageUtils.xjggList));
        LocalDataStorageUtils.removeAllXjgg();
        LocalDataStorageUtils.clearXjggList();
	}
	
	/**
	 * 获取概要信息
	 */
	public static void startGetData(List<String> pages) throws Exception{
		
		CrawlConfig config = new CrawlConfig();
		config.setCrawlStorageFolder(AppConfig.getCrawlstorageXjggSubFolder());
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
        controller.start(BasicXjggGetDataCrawler.class, AppConfig.getCrawlersGyxxNumbers());
        
        controller.waitUntilFinish();
        
        logger.info(DateUtils.getNow()+"==============询价公告爬取概要信息结束==============");

        startGetDataDetail(LocalDataStorageUtils.getXjggHrefs());
	}
	
	/**
	 * 获取详细信息
	 */
	public static void startGetDataDetail(List<String> pages) throws Exception{
		
		CrawlConfig config = new CrawlConfig();
		config.setCrawlStorageFolder(AppConfig.getCrawlstorageXjggDetailFolder());
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
        
        controller.start(BasicXjggGetDataDetailCrawler.class, AppConfig.getCrawlersDetailNumbers());
		
        controller.waitUntilFinish();
        
        logger.info(DateUtils.getNow()+"==============询价公告爬取详细信息结束==============");
        
	}
	
}
