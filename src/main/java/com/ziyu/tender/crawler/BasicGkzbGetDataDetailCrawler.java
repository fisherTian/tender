package com.ziyu.tender.crawler;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.alibaba.fastjson.JSONObject;
import com.ziyu.tender.config.AppConfig;
import com.ziyu.tender.model.Gkzb;
import com.ziyu.tender.utils.DateUtils;
import com.ziyu.tender.utils.GkzbUtils;
import com.ziyu.tender.utils.LocalDataStorageUtils;

import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.crawler.WebCrawler;
import edu.uci.ics.crawler4j.parser.HtmlParseData;
import edu.uci.ics.crawler4j.url.WebURL;

/**
 * 公开招标
 *
 */
public class BasicGkzbGetDataDetailCrawler extends WebCrawler {

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
    	String id = page.getWebURL().getURL().split("/")[page.getWebURL().getURL().split("/").length-1].replace("t", "").replace(".htm", "").replace(".html", "").replace(".hm", "");
        if (page.getParseData() instanceof HtmlParseData) {
            HtmlParseData htmlParseData = (HtmlParseData) page.getParseData();
            String html = htmlParseData.getHtml();
            
            Document doc = Jsoup.parse(html); 
            Element detail = doc.select(".vF_detail_main table").first();
            if(detail!=null){
            	Elements trs = detail.select("tr");
                JSONObject jo = new JSONObject();
                jo.put("id", id);
                for(Element tr:trs){
                	Elements tds = tr.select("td");
                	if(tds.size()>1){
                		if(tds.size()==2){
                			jo.put(tds.get(0).text(), tds.get(1).text());
                		}
                		if(tds.size()==4){
                			jo.put(tds.get(0).text(), tds.get(1).text());
                			jo.put(tds.get(2).text(), tds.get(3).text());
                		}
                	}
                }
                LocalDataStorageUtils.addGkzb(new Gkzb(jo));
            }
        }

    }
}
