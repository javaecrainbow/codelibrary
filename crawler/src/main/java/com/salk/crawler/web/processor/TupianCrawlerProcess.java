package com.salk.crawler.web.processor;

import com.salk.crawler.web.pipeline.RoseFilePipeline;
import com.salk.crawler.web.pipeline.TuPianPipeline;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.ConsolePipeline;
import us.codecraft.webmagic.processor.PageProcessor;

public class TupianCrawlerProcess implements PageProcessor{
	// 部分一：抓取网站的相关配置，包括编码、抓取间隔、重试次数等
	private Site site = Site.me().setRetryTimes(3).setSleepTime(1000)
			.setUserAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_7_2) AppleWebKit/537.31 (KHTML, like Gecko) Chrome/26.0.1410.65 Safari/537.31")
			.setTimeOut(50000).setCharset("utf-8");
	;
	// process是定制爬虫逻辑的核心接口，在这里编写抽取逻辑

	private static final String URL_LIST="http://www\\.jpbysj\\.com/article/list_2_\\d+.html";

	private static final String URL_CONTENT="http://v\\.yupoo.com/photos/lcmyno1/albums/\\d+";
	private static final String URL_CONTENT2="http://v\\.yupoo.com/photos/lcmyno1/\\d+/";
	int total=0;
	int matchTotal=0;
	public void process(Page page) {

		//page.putField("readme", page.getHtml().xpath("//div[@id='readme']/tidyText()"));

		// 部分三：从页面发现后续的url地址来抓取
		//page.addTargetRequests(page.getHtml().links().regex("(https://github\\.com/\\w+/\\w+)").all());
		//page.addTargetRequests(page.getHtml().xpath("//div[@class=\"SetCase\"]").links().regex(URL_CONTENT).all());
		//page.putField("name", page.getHtml().xpath("//div[@class='clearfix']/div/h4/a/text()").all());
		//page.putField("value", page.getHtml().css("img.setThumb", "src").all());
		page.addTargetRequests(page.getHtml().xpath("//div[@class=\"SetCase\"]").links().regex(URL_CONTENT).all());
		System.out.println("page url"+page.getUrl());
		if(page.getUrl().regex(URL_CONTENT).match()) {
			page.addTargetRequests(page.getHtml().xpath("//div[@class=\"lightbox lb[home] Photo\"]").links().regex(URL_CONTENT2).all());
		}
		if(page.getUrl().regex(URL_CONTENT2).match()) {
			page.putField("value", page.getHtml().css("img#photo_img", "src").get());
			page.putField("name",page.getHtml().css("ul#photo_nav li:nth-child(3) a","title").get());
		}
		//page.putField("name", page.getHtml().css("span.albumOwner").get());
		//page.putField("name", page.getHtml().xpath("//span[@class='albumOwner']/tidyText()").get());
		if (page.getResultItems().get("value") == null) {
			//skip this page
			page.setSkip(true);
		}else{
			matchTotal++;
			System.out.println("match num+++++++++++++++"+matchTotal);
		}
		total++;
		System.out.println("num+++++++++++++++"+total);

	}

	public Site getSite() {
		return site;
	}
	public static void main(String[] args) {
		//.addPipeline(new FilePageModelPipeline("E://data"))
		Spider.create(new TupianCrawlerProcess()).addPipeline(new ConsolePipeline()).addPipeline(new TuPianPipeline())
				//从"https://github.com/code4craft"开始抓
				.addUrl("http://v.yupoo.com/photos/lcmyno1/albums/")
						//开启5个线程抓取
				.thread(5)
						//启动爬虫
				.run();
	}

}
