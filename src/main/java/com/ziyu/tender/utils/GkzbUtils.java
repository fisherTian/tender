package com.ziyu.tender.utils;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ziyu.tender.config.AppConfig;
import com.ziyu.tender.crawler.BasicGkzbGetDataCrawler;
import com.ziyu.tender.crawler.BasicGkzbGetDataDetailCrawler;
import com.ziyu.tender.crawler.BasicGkzbPageSizeCrawler;
import com.ziyu.tender.repository.GkzbRepository;
import com.ziyu.tender.tender.model.Record;
import com.ziyu.tender.tender.service.RecordService;

import edu.uci.ics.crawler4j.crawler.CrawlConfig;
import edu.uci.ics.crawler4j.crawler.CrawlController;
import edu.uci.ics.crawler4j.fetcher.PageFetcher;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtConfig;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtServer;

public class GkzbUtils {
	
	protected static final Logger logger = LoggerFactory.getLogger(GkzbUtils.class);
	
	private static GkzbRepository gkzbRepository = SpringUtils.getBean(GkzbRepository.class);
	
	private static RecordService recordService = SpringUtils.getBean(RecordService.class);

	public static void start(List<String> pages) throws Exception{
		
		CrawlConfig config = new CrawlConfig();
		config.setCrawlStorageFolder(AppConfig.getCrawlstorageGkzbPageFolder());
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
        logger.info(DateUtils.getNow()+"==============公开招标爬取信息结束==============");
        System.out.println(com.alibaba.fastjson.JSON.toJSON(LocalDataStorageUtils.gkzbList));
        //保存到mysql
        recordService.batchAddRecord(Record.GKZB_TYPE);
        //保存到es
        if(LocalDataStorageUtils.gkzbList.size()>0)gkzbRepository.saveAll(LocalDataStorageUtils.gkzbList);
        System.out.println(com.alibaba.fastjson.JSON.toJSON(LocalDataStorageUtils.gkzbList));
        LocalDataStorageUtils.removeAllGkzb();
        LocalDataStorageUtils.clearGkzbList();
	}
	
	/**
	 * 获取概要信息
	 */
	public static void startGetData(List<String> pages) throws Exception{
		
		CrawlConfig config = new CrawlConfig();
		config.setCrawlStorageFolder(AppConfig.getCrawlstorageGkzbSubFolder());
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
        controller.start(BasicGkzbGetDataCrawler.class, AppConfig.getCrawlersGyxxNumbers());
        
        controller.waitUntilFinish();
        
        logger.info(DateUtils.getNow()+"==============公开招标爬取概要信息结束==============");

        startGetDataDetail(LocalDataStorageUtils.getGkzbHrefs());
	}
	
	/**
	 * 获取详细信息
	 */
	public static void startGetDataDetail(List<String> pages) throws Exception{
		
		CrawlConfig config = new CrawlConfig();
		config.setCrawlStorageFolder(AppConfig.getCrawlstorageGkzbDetailFolder());
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
        
        controller.start(BasicGkzbGetDataDetailCrawler.class, AppConfig.getCrawlersDetailNumbers());
		
        controller.waitUntilFinish();
        
        logger.info(DateUtils.getNow()+"==============公开招标爬取详细信息结束==============");
        
	}
	
}
