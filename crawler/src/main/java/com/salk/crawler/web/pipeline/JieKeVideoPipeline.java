package com.salk.crawler.web.pipeline;

import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import java.util.Map;
import java.util.Objects;

/**
 * Created by salk on 2016/12/10.
 */
public class JieKeVideoPipeline implements Pipeline {
    @Override
    public void process(ResultItems resultItems, Task task) {
        Map<String, Object> all = resultItems.getAll();
        for (Map.Entry<String,Object> entry:all.entrySet()){
            System.out.println(entry.getKey()+"=="+entry.getValue());
        }
    }
}
