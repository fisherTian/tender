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

import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.crawler.WebCrawler;
import edu.uci.ics.crawler4j.parser.HtmlParseData;
import edu.uci.ics.crawler4j.url.WebURL;

/**
 * 公开招标
 *
 */
public class BasicGkzbGetDataCrawler extends WebCrawler {

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
            Elements lis = doc.select(".vT-srch-result-list-bid").first().select("li");
            List<Gkzb> list = new ArrayList<Gkzb>();
            for(Element el:lis){
            	Element a = el.select("a").first();
                String id = a.attr("abs:href").split("/")[a.attr("abs:href").split("/").length-1].replace("t", "").replace(".htm", "").replace(".html", "").replace(".hm", "");
                String title = a.text();
                Element span = el.select("span").first();
                String[] arr = span.text().split("\\|");
                String time = arr[0].replace(".","-");
                String cgr = arr[1].replace("采购人：", "").trim();
                String dljg = arr[2].replace("代理机构：", "").replace("公开招标公告", "").trim();
                String province = arr[3].trim();
                String type = arr.length==5?arr[4].trim():"";
                Gkzb gkzb = new Gkzb(id, title, time, cgr, dljg, province, type);
                list.add(gkzb);
            }
            System.out.println(com.alibaba.fastjson.JSON.toJSONString(list));
        }

    }
}
