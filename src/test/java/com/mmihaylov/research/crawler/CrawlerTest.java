package com.mmihaylov.research.crawler;

import org.junit.Before;
import org.junit.Test;

import java.util.logging.Logger;

/**
 * Created by mmihaylov on 3/20/16.
 */

public class CrawlerTest {

    private static final Logger LOG = Logger.getLogger(CrawlerTest.class.getName());

    private Crawler crawler;

    @Before
    public void setUp() {
        LOG.info("Init test resources.");
        crawler = new Crawler();
    }

    @Test
    public void skimTestNovini() {
        crawler.skim("http://www.novini.bg/news/%d.html", "/home/mmihaylov/novini", FileSize.KILO_BYTE * 150, 10);
    }

    @Test
    public void skimTestSportal() {

    }
}
