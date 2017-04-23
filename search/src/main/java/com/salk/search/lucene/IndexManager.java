package com.salk.search.lucene;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.ReferenceManager;
import org.apache.lucene.search.SearcherFactory;
import org.apache.lucene.search.SearcherManager;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.store.RAMDirectory;
import org.apache.lucene.util.Version;

import java.io.File;
import java.io.IOException;

/**
 * Created by salk on 2016/9/21.
 */
public class IndexManager {
    /**
     * 所有writer公共配置
     */
    private static final IndexWriterConfig iwc =
            new IndexWriterConfig(Version.LUCENE_47, new StandardAnalyzer(Version.LUCENE_47));
    static
    private Directory dir;
    protected IndexReader reader;
    protected IndexSearcher searcher;

    protected IndexWriter writer;

    private ReferenceManager<IndexSearcher> referenceManager = null;

    private Object writeLock = new Object();

    public IndexManager() {
        init();
    }
    private void init() {
        try {
            dir = new RAMDirectory();
            //根据Directory对象，初始化indexReader对象
            //初始化writer对象
            createWriter(dir);
            //

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void createWriter(Directory dir) throws IOException {
        IndexWriterConfig clone = iwc.clone();
        if (IndexWriter.isLocked(dir)) {
            IndexWriter.unlock(dir);
        }
        writer = new IndexWriter(dir,clone);
    }

    public void releaseIndexSearcher(IndexSearcher indexSearcher) {
        try {
            referenceManager.release(indexSearcher);
        } catch (IOException e) {
        }
    }
    public IndexSearcher getSearcher2() {
        ReaderManager.getInstance().createIndexReader(dir);
        IndexReader ir = ReaderManager.getInstance().getIndexReader(dir);
        if (reader == null || reader != ir) {
            reader = ir;
            searcher = new IndexSearcher(reader);
        }
        return searcher;
    }
    public IndexSearcher getSearcher() {
        try {
            if (null != referenceManager) {
                referenceManager.maybeRefresh();
                return referenceManager.acquire();
            }
            if (null == writer) {
                createWriter(dir);
            }
            SearcherFactory searcherFactory = new SearcherFactory();
            referenceManager = new SearcherManager(writer, true, searcherFactory);
            return referenceManager.acquire();
        } catch (Exception e) {
            //LOG.debug(e.getMessage(), e);
        }
        return null;
    }

    public IndexWriter getWriter() {
        return writer;
    }
    public void storeReader(){
        ReaderManager.getInstance().createIndexReader(dir);
    }

    public void refershReader(){
        ReaderManager.getInstance().refershReader();
    }
    public Directory getDir() {
        return dir;
    }

    public void setDir(Directory dir) {
        this.dir = dir;
    }

    public void commitWriter() {
        try {
            writer.commit();
        } catch (IOException e) {
            rollback();
        }
    }

    private void rollback() {
        try {
            writer.rollback();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

}
