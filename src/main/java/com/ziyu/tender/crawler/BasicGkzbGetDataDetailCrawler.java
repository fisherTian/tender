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
    	
        if (page.getParseData() instanceof HtmlParseData) {
            HtmlParseData htmlParseData = (HtmlParseData) page.getParseData();
            String html = htmlParseData.getHtml();
            
            //解析html获得总共条目数和总页数
            Document doc = Jsoup.parse(html); 
            Element detail = doc.select(".vF_detail_content").first();
            System.out.println(detail.html());
        }

    }
}
