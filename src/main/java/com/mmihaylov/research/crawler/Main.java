package com.mmihaylov.research.crawler;

/**
 * Created by mmihaylov on 3/20/16.
 */
public class Main {

    public static void main(String[] args) {
        Crawler crawler = new Crawler();
        //crawler.skim("http://www.novini.bg/news/%d.html", "/home/mmihaylov/novini", FileSize.KILO_BYTE * 150, 10);
        crawler.skim(" http://www.sportal.bg/news.php?news=%d", "/home/mmihaylov/sportal", FileSize.KILO_BYTE * 150, 1);
    }
}
