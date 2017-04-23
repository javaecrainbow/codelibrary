package com.salk.crawler.web.pipeline;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import com.google.common.base.Joiner;

import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.PageModelPipeline;
import us.codecraft.webmagic.pipeline.Pipeline;
import us.codecraft.webmagic.selector.PlainText;

public class RoseFilePipeline implements Pipeline  {

	public void process(ResultItems resultItems, Task task) {
		 System.out.println("get page: " + resultItems.getRequest().getUrl());
	        //遍历所有结果，输出到控制台，上面例子中的"author"、"name"、"readme"都是一个key，其结果则是对应的value
		 	List<String> resuts=new ArrayList<String>();
		 	String dbType="MYSQL";
		 	StringBuffer sb = new StringBuffer();
	        for (Map.Entry<String, Object> entry : resultItems.getAll().entrySet()) {
	            System.out.println(entry.getKey() + ":\t" + entry.getValue());
	           String value="";
	           if(entry.getKey().equals("content")){
	        	   value=Joiner.on("").join((List)entry.getValue());
	           }else{
	        	   value= ((PlainText)entry.getValue()).toString();
	           }
	           if(StringUtils.isEmpty(value)){
		            sb.append("null").append("@##@");

	           }else{
	            if(entry.getKey().equals("summary") || entry.getKey().equals("content")){
	            	Pattern pattern = Pattern.compile("((?i)MYSQL|(?i)ORACLE|(?i)ACCESS|(?i)SQLSERVER|(?i)DB2|(?i)SQL2005|(?i)SQL2000|(?i)SQL2008|(?i)SQL2010)", Pattern.CASE_INSENSITIVE);
	        		Matcher matcher = pattern.matcher(value);
	        		if(matcher.find() && "MYSQL".equals(dbType)){
	        			dbType=matcher.group(1);
	        		}
	            	
	            }
	            sb.append(value).append("@##@");
	           }
	        }
	        sb.append(dbType);
	        System.out.println(sb.toString());
	        List<String> results=new ArrayList<String>();
	        results.add(sb.toString());
	        
	        write2File(results);
	}

	
	private void write2File(List<String> params){
		try {
			FileUtils.writeLines(new File("E://data/biyebao/vc/data_20150120.txt"), params, null, true);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private void writestr2File(){
		try {
			FileUtils.writeStringToFile(new File("E://data/biyebao/asp/data.txt"), "2323", true);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
//		RoseFilePipeline roseFilePipeline =new RoseFilePipeline();
//		for(int i=0;i<10;i++){
//		roseFilePipeline.writestr2File();
//		}
		Pattern pattern = Pattern.compile("((?i)abc|(?i)efg|(?i)bcd)", Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher("ssssfbcd");
		if(matcher.find()){
		System.out.println(matcher.group(1));
		}
	}
}
