package com.salk.crawler.web;

import com.salk.crawler.web.pipeline.RoseFilePipeline;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.ConsolePipeline;
import us.codecraft.webmagic.pipeline.FilePageModelPipeline;
import us.codecraft.webmagic.pipeline.FilePipeline;
import us.codecraft.webmagic.pipeline.Pipeline;
import us.codecraft.webmagic.processor.PageProcessor;

public class BiyebaoCrawlerProcess implements PageProcessor{
	// 部分一：抓取网站的相关配置，包括编码、抓取间隔、重试次数等
    private Site site = Site.me().setRetryTimes(3).setSleepTime(1000)
    		.setUserAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_7_2) AppleWebKit/537.31 (KHTML, like Gecko) Chrome/26.0.1410.65 Safari/537.31");
    // process是定制爬虫逻辑的核心接口，在这里编写抽取逻辑

    private static final String URL_LIST="http://www\\.jpbysj\\.com/article/list_2_\\d+.html";

    private static final String URL_CONTENT="http://www\\.jpbysj\\.com/article/\\d+\\.html";
    
    int total=0;
	public void process(Page page) {
		
        //page.putField("readme", page.getHtml().xpath("//div[@id='readme']/tidyText()"));

        // 部分三：从页面发现后续的url地址来抓取
        //page.addTargetRequests(page.getHtml().links().regex("(https://github\\.com/\\w+/\\w+)").all());
        page.addTargetRequests(page.getHtml().xpath("//div[@class=\"text1_1\"]").links().regex(URL_CONTENT).all());
        page.addTargetRequests(page.getHtml().links().regex(URL_LIST).all());

        if (page.getUrl().regex(URL_LIST).match()) {
            page.addTargetRequests(page.getHtml().xpath("//div[@class=\"text1_1\"]").links().regex(URL_CONTENT).all());
            page.addTargetRequests(page.getHtml().links().regex(URL_LIST).all());

		    //文章页
		} else{
		page.putField("title", page.getHtml().xpath("//div[@class='centen1']/h1/text(0)"));
        page.putField("date", page.getHtml().xpath("//div[@class='centen1']/text(0)").regex("\\d+-\\d+-\\d+"));
        //page.putField("summary1", page.getHtml().xpath("//div[@class='centen2']/div/text(0)"));
        //page.putField("summary", page.getHtml().xpath("//div[@class='centen2']/div/text(0)").regex("\\d+：([\\s\\S]*)",1));
        page.putField("summary", page.getHtml().xpath("//div[@class='centen2']/div/text(0)"));
        page.putField("content", page.getHtml().xpath("//div[@class='centen2']/p").all());
        if (page.getResultItems().get("title") == null) {
            //skip this page
            page.setSkip(true);
        }else{
        	total++;
        	System.out.println("num+++++++++++++++"+total);
        }
		}
	
	
	}

	public Site getSite() {
		return site;
	}
	public static void main(String[] args) {
		//.addPipeline(new FilePageModelPipeline("E://data"))
		Spider.create(new BiyebaoCrawlerProcess()).addPipeline(new ConsolePipeline()).addPipeline(new RoseFilePipeline())
        //从"https://github.com/code4craft"开始抓
		.addUrl("http://www.jpbysj.com/article/list_2.html")
        //开启5个线程抓取
        .thread(5)
        //启动爬虫
        .run();
	}

}
