package com.salk.search.lucene;

import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.IntField;
import org.apache.lucene.document.StringField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.*;

import java.io.IOException;

/**
 * Created by salk on 2016/9/22.
 */
public class Searcher {

    public static void main(String[] args)throws Exception{
        Searcher s=new Searcher();
        s.index();
        int i=0;
        long time1= System.currentTimeMillis();
        while(i<1000000) {
            i++;
            s.searcher();
        }
        long time2= System.currentTimeMillis();
        System.out.println("time coust========"+(time2-time1));
        //s.searcher();
        //s.update();
        // Thread.sleep(2000);
        // s.searcher();
    }

    private IndexManager indexManager;
    public Searcher(){
        indexManager=new IndexManager();
    }

    private void index()throws Exception{
        IndexWriter writer = indexManager.getWriter();
        //Document存放经过组织后的数据源，只有转换为Document对象才可以被索引和搜索到
        Document doc = new Document();
        //文件名称
        for(int i=1;i<=200;i++){
            doc.add(new StringField("index",i+"", Field.Store.YES));
            writer.addDocument(doc);
        }
        writer.commit();
        new Thread(new LazyThread()).start();
        //indexManager.storeReader();
    }

    private void searcher()throws Exception{
        IndexSearcher searcher = indexManager.getSearcher();
        TermQuery index = new TermQuery(new Term("index", "300"));
        BooleanQuery q=new BooleanQuery();
        q.add(index, BooleanClause.Occur.MUST);

        TopDocs search = searcher.search(index, Integer.MAX_VALUE);
        int totalHits = search.totalHits;
        indexManager.releaseIndexSearcher(searcher);
        System.out.println(totalHits);

    }

    private void update()throws  Exception{
        IndexWriter writer = indexManager.getWriter();
        Document document = new Document();

        for(int i=20;i<10;i++){
            document.add(new StringField("index", i * 10 + "", Field.Store.YES));
            writer.updateDocument(new Term("index", i + ""), document);
        }
        writer.commit();
    }
    class LazyThread implements Runnable{

        @Override
        public void run() {
            IndexWriter  writer= indexManager.getWriter();
            Document doc = new Document();

            for(int i=201;i<=400;i++){
                System.out.println("lazy init i========" + i);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                doc.add(new StringField("index", i + "", Field.Store.YES));
                try {
                    writer.addDocument(doc);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            try {
                writer.commit();
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            //indexManager.refershReader();
        }
    }

}
