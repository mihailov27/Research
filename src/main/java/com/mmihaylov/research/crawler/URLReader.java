package com.mmihaylov.research.crawler;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.logging.Logger;

/**
 * Created by mmihaylov on 3/20/16.
 */
public class URLReader {

    private static final Logger LOG = Logger.getLogger(URLReader.class.getName());

    public InputStream readUrl(String urlAddress) throws CrawlerException {
        try {
            LOG.info("Open URL address: " + urlAddress);
            URL url = new URL(urlAddress);
            URLConnection urlConnection = url.openConnection();
            InputStream input = urlConnection.getInputStream();
            return input;
        } catch (MalformedURLException me) {
            throw new CrawlerException("Invalid URL address: " + urlAddress, me);
        } catch (IOException ioe) {
            throw new CrawlerException("Invalid open connection with URL address: " + urlAddress, ioe);
        }
    }
}
