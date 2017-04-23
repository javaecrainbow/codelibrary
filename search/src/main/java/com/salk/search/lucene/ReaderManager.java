package com.salk.search.lucene;

/**
 * Created by salk on 2016/9/21.
 */

import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.store.Directory;

import java.io.IOException;
import java.util.*;

/**
 * 用于提供IndexReader生命周期管理
 */
public class ReaderManager {
    /**
     * reader回收Map
     */

    private static final Map<DirectoryReader, Long> recyleReaderMap
            = new HashMap<DirectoryReader, Long>();


    /**
     * oldreader回收最大生命周期
     */

    private static final int oldReaderMaxLifeTime = 60 * 1000;


    private static final Timer readerRefereshTimer = new Timer();


    private static final Map<Directory, DirectoryReader> readerMap
            = new HashMap<Directory, DirectoryReader>();


    private static final ReaderManager manager = new ReaderManager();


    public static final synchronized ReaderManager getInstance()

    {

        return manager;

    }


    /**
     * 创建indexReader并放缓存
     *
     * @paramreader
     */

    public synchronized void createIndexReader(Directory dir)

    {

        try {

            readerMap.put(dir, DirectoryReader.open(dir));

        } catch (IOException e) {

            e.printStackTrace();
        }

    }


    /**
     * 获取IndexReader
     *
     * @return
     * @paramdir
     */

    public IndexReader getIndexReader(Directory dir)

    {

        return readerMap.get(dir);

    }
    public void refershReader()

    {

        for (Map.Entry<Directory, DirectoryReader> entry : new HashMap<Directory, DirectoryReader>(readerMap).entrySet()) {

            try {

                DirectoryReader oldReader = entry.getValue();

                DirectoryReader newReader = DirectoryReader.openIfChanged(oldReader);

                if (newReader != null)

                {

                    //替换旧reader对象
                    System.out.println("reader changed");
                    readerMap.put(entry.getKey(), newReader);


                    //放入回收MAP中

                    recyleReaderMap.put(oldReader, System.currentTimeMillis());

                }

            } catch (IOException e) {

                e.printStackTrace();

            }

        }


    }
    static

    {
        readerRefereshTimer.schedule(new TimerTask() {


            public void run() {

                //判断处理reader是否改变

                for (Map.Entry<Directory, DirectoryReader> entry : new HashMap<Directory, DirectoryReader>(readerMap).entrySet()) {

                    try {

                        DirectoryReader oldReader = entry.getValue();

                        DirectoryReader newReader = DirectoryReader.openIfChanged(oldReader);

                        if (newReader != null)

                        {

                            //替换旧reader对象
                            System.out.println("reader changed");
                            readerMap.put(entry.getKey(), newReader);


                            //放入回收MAP中

                            recyleReaderMap.put(oldReader, System.currentTimeMillis());

                        }

                    } catch (IOException e) {

                        e.printStackTrace();

                    }

                }


                //处理old reader回收

                for (Map.Entry<DirectoryReader, Long> entry : new HashMap < DirectoryReader,
                Long > (recyleReaderMap).entrySet()){

                    if (System.currentTimeMillis() - entry.getValue() > oldReaderMaxLifeTime)
                    {
                        try {

                            entry.getKey().close();

                        } catch (IOException e) {

                            e.printStackTrace();

                        } finally {

                            recyleReaderMap.remove(entry.getKey());

                        }

                    }

                }

            }


        }, 200, 200);

    }
}
