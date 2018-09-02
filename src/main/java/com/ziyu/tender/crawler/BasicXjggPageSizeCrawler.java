package com.ziyu.tender.crawler;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import com.alibaba.fastjson.JSONObject;
import com.ziyu.tender.config.AppConfig;
import com.ziyu.tender.utils.DateUtils;
import com.ziyu.tender.utils.XjggUtils;

import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.crawler.WebCrawler;
import edu.uci.ics.crawler4j.parser.HtmlParseData;
import edu.uci.ics.crawler4j.url.WebURL;

/**
 * 询价公告
 *
 */
public class BasicXjggPageSizeCrawler extends WebCrawler {

    private static final Pattern IMAGE_EXTENSIONS = Pattern.compile(".*\\.(bmp|gif|jpg|png)$");

    @Override
    public boolean shouldVisit(Page referringPage, WebURL url) {
        String href = url.getURL().toLowerCase();
        if (IMAGE_EXTENSIONS.matcher(href).matches()) {
            return false;
        }
        return href.startsWith(AppConfig.getBaseUrl());
    }

    @Override
    public void visit(Page page) {

        if (page.getParseData() instanceof HtmlParseData) {
            HtmlParseData htmlParseData = (HtmlParseData) page.getParseData();
            String html = htmlParseData.getHtml();
            
            //解析html获得总共条目数和总页数
            Document doc = Jsoup.parse(html); 
            Element vt = doc.select("div.vT_z").get(3);
            String totalNum = vt.select("div").first().select("div").first().select("p").first().select("span").get(1).text();
            int totalPage = 0;
            if(Integer.parseInt(totalNum)>0){
            	String json = (vt.select("div").first().select("div").first().select("p.pager").first().getElementsByTag("script").get(0).data().toString().replace("Pager(","").replace(");", ""));
                JSONObject jo = JSONObject.parseObject(json);
                totalPage = jo.getIntValue("size");
                logger.info(DateUtils.getNow()+"：询价公告今日总共发布  "+totalNum+"条招标信息，一共"+totalPage+"页");
                
                //分页爬取
                List<String> pages = new ArrayList<String>();
                Date now = new Date();
                for(int i=0;i<totalPage;i++){
                	pages.add(AppConfig.getXjggUrl(i+1, DateUtils.toString(now, DateUtils.DEFAULT_DATE_FORMAT), DateUtils.toString(now, DateUtils.DEFAULT_DATE_FORMAT)));
                }
                try {
    				XjggUtils.startGetData(pages);
    			} catch (Exception e) {
    				e.printStackTrace();
    			}
            }
        }

    }
}
