package com.salk.search.lucene;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;

import java.io.File;
import java.io.IOException;

/**
 * Created by salk on 2016/9/28.
 */
public class FileIndexManager  {
    protected IndexWriter writer;
    private static final IndexWriterConfig iwc =
            new IndexWriterConfig(Version.LUCENE_47, new StandardAnalyzer(Version.LUCENE_47));
    private Directory dir;
    public FileIndexManager(File dirFile) {
        init(dirFile);
    }
    private void init(File dirFile) {
        try {
             dir = FSDirectory.open(dirFile);
            //根据Directory对象，初始化indexReader对象
            //ReaderManager.getInstance().createIndexReader(dir);
            //初始化writer对象
            createWriter(dir);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void createWriter(Directory dir) throws IOException {
        writer = new IndexWriter(dir, iwc);
    }

    public IndexWriter getWriter() {
        return writer;
    }
    public Directory getDir() {
        return dir;
    }
    public void setWriter(IndexWriter writer) {
        this.writer = writer;
    }
}
