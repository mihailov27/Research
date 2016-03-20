package com.mmihaylov.research.crawler;

import java.io.File;
import java.io.InputStream;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Logger;

/**
 * Created by mmihaylov on 3/20/16.
 */
public class Crawler {

    private static final Logger LOG = Logger.getLogger(Crawler.class.getName());
    private static final String FILENAME_TEMPLATE = "%d.html";

    private URLReader urlReader;
    private FileSystemWriter fileSystemWriter;
    private ExecutorService executorService;

    private volatile long current_size = 0;

    public Crawler() {
        LOG.info("Init crawler.");
        urlReader = new URLReader();
        fileSystemWriter = new FileSystemWriter();
        executorService = Executors.newFixedThreadPool(5);
    }

    public void skim(String domain, String directory, long sizeLimit, int from) {
        LOG.info(String.format("Skim domain address '%s' with limit size '%d' bytes", domain, sizeLimit));
        // create the directory
        File dir = fileSystemWriter.createDirectory(directory);
        int i;
        for(i=from; i<= 300000; i++) {
            executorService.submit(new SkimPageRunnable(String.format(domain, i), String.format(FILENAME_TEMPLATE, i), dir));
        }
        LOG.info(String.format("Process is terminated. Number or read URL's : %d", i-from));
    }

    class SkimPageRunnable implements Runnable {

        private String url;
        private String fileName;
        private File dir;

        private SkimPageRunnable(String url, String fileName, File dir) {
            this.url = url;
            this.fileName = fileName;
            this.dir = dir;
        }

        public void run() {
            try {
                LOG.info("Open URL address: " + url);
                InputStream input = urlReader.readUrl(url);
                File file = fileSystemWriter.writeStreamToFile(input, dir, fileName);
                current_size += file.length();
                LOG.info("Dir size " + current_size);
            } catch (CrawlerException ce) {
                LOG.severe("Fail to read URL. " + ce.getMessage());
            }
        }
    }

}
