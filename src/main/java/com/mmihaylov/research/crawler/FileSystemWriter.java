package com.mmihaylov.research.crawler;

import java.io.*;
import java.util.logging.Logger;

/**
 * Created by mmihaylov on 3/20/16.
 */
public class FileSystemWriter {

    private static final Logger LOG = Logger.getLogger(FileSystemWriter.class.getName());

    public File createDirectory(String path) {
        LOG.info(String.format("Create directory %s", path));
        File directory = new File(path);
        if(directory.exists()) {
            directory.delete();
        }
        directory.mkdir();
        return directory;
    }

    public File writeStreamToFile(InputStream input, File directory, String fileName) throws CrawlerException {
        OutputStream out = null;
        try {
            byte[] bytes = new byte[50];
            File file = new File(directory, fileName);
            out = new FileOutputStream(file);
            while(input.read(bytes) != -1) {
                out.write(bytes);
            }
            return file;
        } catch (IOException ioe) {
            throw new CrawlerException(String.format("Failed to write a file '%s'", fileName), ioe);
        } finally {
            closeResource(input);
            closeResource(out);
        }
    }

    private void closeResource(Closeable resource) {
        try {
            if(resource != null) {
                resource.close();
            }
        } catch (IOException ioe) {
            LOG.severe("Failed to close resource. " + ioe.getMessage());
        }
    }
}