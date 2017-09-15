package com.creativestudio.weibo;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * Created by zhenz on 2017/1/4.
 */

public class HtmlParser {
    public static String GetValue(String html,String cssQuery,String attr)
    {
        Document doc = Jsoup.parse(html);
        Elements selector = doc.select(cssQuery);
        Element ele = selector.first();
        return ele.attr(attr);
    }
    public static String getById(String html,String id,String attr)
    {
        Document doc = Jsoup.parse(html);
        Element element=doc.getElementById(id);
        return element.attr(attr);
    }


}
