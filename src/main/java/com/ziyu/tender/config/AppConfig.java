package com.ziyu.tender.config;

import java.text.MessageFormat;
import java.util.Properties;

public class AppConfig {

    private static final String BASE_URL = "BASE.URL";
    private static final String GKZB_URL = "GKZB.URL";
    private static final String XJGG_URL = "XJGG.URL";
    
    private static final String CRAWLERS_GYXX_NUMBERS = "CRAWLERS.GYXX.NUMBERS";
    private static final String CRAWLERS_DETAIL_NUMBERS = "CRAWLERS.DETAIL.NUMBERS";
    private static final String CRAWLSTORAGE_GKZB_PAGE_FOLDER = "CRAWLSTORAGE.GKZB.PAGE.FOLDER";
    private static final String CRAWLSTORAGE_GKZB_SUB_FOLDER = "CRAWLSTORAGE.GKZB.SUB.FOLDER";
    private static final String CRAWLSTORAGE_GKZB_DETAIL_FOLDER = "CRAWLSTORAGE.GKZB.DETAIL.FOLDER";
    
    private static Properties p = new Properties();

    static {
        try {
            p.load(AppConfig.class.getResourceAsStream("/appconfig.properties"));
        } catch (Exception e) {
        }
    }

    
    public static String getBaseUrl() {
        return p.getProperty(BASE_URL);
    }
    
    public static String getGkzbUrl(Integer start,String startTime,String endTime) {
    	String url = p.getProperty(GKZB_URL);
        return MessageFormat.format(url, start.toString(),startTime.replace("-", "%3A"),endTime.replace("-", "%3A"));
    }
    
    public static String getXjggUrl(Integer start,String startTime,String endTime) {
    	String url = p.getProperty(XJGG_URL);
        return MessageFormat.format(url, start.toString(),startTime.replace("-", "%3A"),endTime.replace("-", "%3A"));
    }
    
    public static Integer getCrawlersGyxxNumbers() {
        return Integer.parseInt(p.getProperty(CRAWLERS_GYXX_NUMBERS));
    }
    
    public static Integer getCrawlersDetailNumbers() {
        return Integer.parseInt(p.getProperty(CRAWLERS_DETAIL_NUMBERS));
    }
    
    public static String getCrawlstorageGkzbPageFolder() {
        return p.getProperty(CRAWLSTORAGE_GKZB_PAGE_FOLDER);
    }
    
    public static String getCrawlstorageGkzbSubFolder() {
        return p.getProperty(CRAWLSTORAGE_GKZB_SUB_FOLDER);
    }
    
    public static String getCrawlstorageGkzbDetailFolder() {
        return p.getProperty(CRAWLSTORAGE_GKZB_DETAIL_FOLDER);
    }
}
