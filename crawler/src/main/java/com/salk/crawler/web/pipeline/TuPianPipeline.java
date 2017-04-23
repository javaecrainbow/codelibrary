package com.salk.crawler.web.pipeline;

import com.google.common.base.Joiner;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;
import us.codecraft.webmagic.selector.PlainText;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Administrator on 2016/5/29.
 */
public class TuPianPipeline implements Pipeline {
    int i=0;
    public static void main(String[] args) {
        String href="http://photo.yupoo.com/lcmyno1/FbiRLwTe/thumb.jpg";
        String foderName=href.substring(href.lastIndexOf("/")+1, href.lastIndexOf("."));
        String fileType=href.substring(href.lastIndexOf(".")+1);
        System.out.println(foderName);
        System.out.println(fileType);
        File imgFile = new File("F:\\tupian\\xinbai\\"+"1.txt");
        String s=" 你 妹 ";
        System.out.println(s.trim());

    }
    @Override
    public void process(ResultItems resultItems, Task task) {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        Map<String, Object> all = resultItems.getAll();
//        File file=null;
//        String fileName = (String)all.get("name");
//        if(StringUtils.isBlank(fileName)){
//            return;
//        }
//        fileName=fileName.trim();
//        file= parseName(fileName);

        String fileName=(String)all.get("name");
        System.out.println(fileName);

        if(StringUtils.isBlank(fileName)){
            return;
        }
        File file=null;
        fileName=fileName.trim();
        file= parseName(fileName);
        String value=(String)all.get("value");
        System.out.println(value);
        if(org.apache.commons.lang.StringUtils.isBlank(value)){
            return;
        }
        String fileType=value.substring(value.lastIndexOf(".")+1);
        HttpGet httpget = new HttpGet(value);
        InputStream in=null;
        i++;
        try {
            HttpResponse response = httpclient.execute(httpget);
            HttpEntity entity = response.getEntity();
            in = entity.getContent();
            File imgFile = new File(file,i+"."+fileType);
            //File imgFile = FileUtils.getFile(file, foderName + "." + fileType);

            FileOutputStream fout = new FileOutputStream(imgFile);
            int l = -1;
            byte[] tmp = new byte[1024];
            while ((l = in.read(tmp)) != -1) {
                fout.write(tmp,0,l);
            }
            fout.flush();
            fout.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        finally {
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    private File parseName(String fileName) {
        File file=new File("F:\\tupian");
        if(!file.exists()){
            file.mkdir();
        }
        File file2=new File(file,fileName);
        try {
            if(!file2.exists()) {
                file2.mkdir();
            }
            return file2;
        } catch (Exception e) {
            System.err.println("创建文件夹"+fileName+" 失败");
        }
        return null;
    }
}
