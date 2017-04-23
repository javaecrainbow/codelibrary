package com.salk.crawler.web.processor;

import com.salk.crawler.web.pipeline.RoseFilePipeline;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.ConsolePipeline;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.ArrayList;

/**
 * Created by salk on 2016/12/10.
 */
public class JieKeVideoDownProcess implements PageProcessor {
    private static  final String URL_LIST_PARTEN="http://www.jikexueyuan.com/course/\\d+_\\d+.html";
    private static  final String URL_CONTENT_PARTEN="http://www.jikexueyuan.com/course/\\d+_\\d+.html.*";

    public static void main(String[] args) {
        String template="http://www.jikexueyuan.com/course/video_download?seq={seq}&course_id={course}";
        int[] courses = new int[]{937};
        int[] seq = new int[]{4};
        ArrayList<String> urls = new ArrayList<String>();

        for(int i=0;i<courses.length;i++){
            for(int j=0;j<seq.length;j++){
                int i1 = seq[j];
                String url=template.replaceAll("\\{course\\}",courses[i]+"");
                System.out.println(url);
                for(int max=i1;i1>0;i1--){
                    urls.add(url.replaceAll("\\{seq\\}",i1+""));
                }
            }
        }
        Spider.create(new JieKeVideoDownProcess()).addPipeline(new ConsolePipeline()).addPipeline(new RoseFilePipeline())
                //从"https://github.com/code4craft"开始抓
                .addUrl(urls.toArray(new String[0]))
                        //开启5个线程抓取
                .thread(5)
                        //启动爬虫
                .run();
    }
    @Override
    public void process(Page page) {
        System.out.println("page url" + page.getUrl());
        System.out.println(page.getHtml());

    }

    @Override
    public Site getSite() {
        return Site.me().setRetryTimes(3).setSleepTime(1000)
                .setUserAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_7_2) AppleWebKit/537.31 (KHTML, like Gecko) Chrome/26.0.1410.65 Safari/537.31")
                .setTimeOut(50000).setCharset("utf-8")
                        .setDomain("http://www.jikexueyuan.com")
//                .addCookie("gr_user_id", "401cebc3-3512-449c-b3b0-42b74e58c5e8")
//                .addCookie("vip_status", "1")
                .addCookie("authcode", "041a%2FhEdWZsvY6zoqwwYQ2PQycg8N5BagKl3sIuC94shBvXS%2BNSrc5gmkvfwMjhNx19o1y3DagubHp1fw2NZ58D1vjv4nT6%2FagH82YvIGeDBPCI%2FP3nGs9tErTChM%2Bd4",".jikexueyuan.com")
//                .addCookie("avatar", "http%3A%2F%2Fassets.jikexueyuan.com%2Fuser%2Favtar%2Fdefault.gif",".jikexueyuan.com")
//                .addCookie("ca_status", "1", ".jikexueyuan.com")
//                .addCookie("code", "Q82GL9", ".jikexueyuan.com")
//                .addCookie("domain", "0yjWaUPUj", ".jikexueyuan.com")
//                .addCookie("gr_cs1_195221a9-b7dc-41e5-b8b8-7853c8f42659", "uid%3A3607873", ".jikexueyuan.com")
//                .addCookie("gr_session_id_aacd01fff9535e79", "195221a9-b7dc-41e5-b8b8-7853c8f42659", ".jikexueyuan.com")
//                .addCookie("gr_user_id", "401cebc3-3512-449c-b3b0-42b74e58c5e8",".jikexueyuan.com")
//                .addCookie("is_expire","1",".jikexueyuan.com")
//                .addCookie("level_id", "3",".jikexueyuan.com")
//                .addCookie("OUTFOX_SEARCH_USER_ID_NCOO", "2074284948.7879243",".jikexueyuan.com")
                .addCookie("uid","3607873",".jikexueyuan.com")
                .addCookie("uname","jike_6035641",".jikexueyuan.com")
//                .addCookie("vip_status","1",".jikexueyuan.com")
//                .addCookie("___rl__test__cookies","1481385366472","www.jikexueyuan.com")
//                .addCookie("connect.sid","s%3AR8wU9TxJW2RYO5M6GEb-CUTDZ2HHWGaK.p1yB%2B4IAl75eC7Voq3fnWYZ%2BRazywRDtEBajVxc2LSY", "www.jikexueyuan.com")
//                .addCookie("MEIQIA_EXTRA_TRACK_ID","1f50169aba3311e6be49063d99a8887a","www.jikexueyuan.com")
//                .addCookie("QINGCLOUDELB","84b10773c6746376c2c7ad1fac354ddfd562b81daa2a899c46d3a1e304c7eb2b|WEwoM|WEwlc","www.jikexueyuan.com")
//                .addCookie("wP_v", "00e237d25abeDYQMLLO248~TO2O24kqkKV4HfWzkzk456u2CYvKtmzqXoc4tc~S","www.jikexueyuan.com");
;

    }
}
