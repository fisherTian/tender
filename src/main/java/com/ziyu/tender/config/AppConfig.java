package com.ziyu.tender.config;

import java.text.MessageFormat;
import java.util.Properties;

public class AppConfig {

    private static final String BASE_URL = "BASE.URL";
    private static final String GKZB_URL = "GKZB.URL";
    private static final String XJGG_URL = "XJGG.URL";
    
    private static final String CRAWLERS_NUMBERS = "CRAWLERS.NUMBERS";
    private static final String CRAWLSTORAGE_FOLDER = "CRAWLSTORAGE.FOLDER";
    
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
    
    public static Integer getCrawlersNumbers() {
        return Integer.parseInt(p.getProperty(CRAWLERS_NUMBERS));
    }
    
    public static String getCrawlstorageFolder() {
        return p.getProperty(CRAWLSTORAGE_FOLDER);
    }
}
