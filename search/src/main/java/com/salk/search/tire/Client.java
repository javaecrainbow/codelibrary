package com.salk.search.tire;

import org.apache.commons.lang.StringUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.regex.Pattern;

/**
 * Created by lijingjing on 2016/9/14.
 */
public class Client {
    public static void main(String[] args)throws Exception {
        Client client=new Client();
        String path = client.getClass().getResource("/").getPath() + "/tire/tire.txt";
        //String path2 = c.getClass().getClassLoader().getResource("").getPath() + "/tire/data.txt";
        TireTree tree=new TireTree();
        client.readFile(path,tree);
        System.out.println("ok");
        String str="alloy";
        Thread.sleep(2000);
        StringBuilder sb=new StringBuilder();
        for(char c:str.toCharArray()){
            sb.append(c);
            System.out.println(StringUtils.join(tree.search(tree.getRoot(),sb.toString()),","));
        }
        //tree.search(tree.getRoot(),"as");
        //System.out.println(tree.search(tree.getRoot(),"home",tree.getRoot().getHashSet()));
    }

    public  void readFile(String fileName,TireTree tree) throws Exception{

        BufferedReader read =
                new BufferedReader(new InputStreamReader(new FileInputStream(new File(fileName)), "utf-8"));
        String line=null;
        int index=1;
        while((line=read.readLine())!=null){
            String[] split = line.split("\t");
            if(split==null || split.length<2 || split[0].split("\\s+").length>1){
                continue;
            }

            Pattern p = Pattern.compile("([a-z])+");
            if(!p.matcher(split[0]).matches()){
                System.out.println("error======"+split[0]);
                continue;
            }
            //System.out.println(split[0]);
            tree.addNode(tree.getRoot(),split[0],split[0]);
            index++;
            if(index%10000==0){
                System.out.println(index);
            }
        }

    }

}
