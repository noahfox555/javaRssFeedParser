package com.rssreader.rssreader;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.io.*;


public class Test {

    public static void main(String[] args) {

        try {
            RSSFeedParser parser = new RSSFeedParser("https://rss.nytimes.com/services/xml/rss/nyt/World.xml", "");
            ArrayList<String> titles = parser.getRssTitles();
            ArrayList<String> links = parser.getLinks();
            ArrayList<String> pubDate = parser.getPubDates();
            ArrayList<String> images = parser.getImages();
            for (String i:pubDate) {
                System.out.println(i);
            }
            System.out.println(images.size());
            System.out.println(titles.size());
            System.out.println(pubDate.size());
            // for (int i = 0; i < titles.size(); i++) {
            //     System.out.println(titles.get(i) + ":\t" + links.get(i) + "\n");
            // }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        // ArrayList titles = getRSSTitles("https://www.espn.com/espn/rss/news");
        // for (int i = 0; i < titles.size(); i++) {
        //     System.out.println(titles.get(i));
        // }
    }

    public static ArrayList<String> getRSSTitles(String urlAddress){
        try{
            URL rssUrl = new URL (urlAddress);
            BufferedReader in = new BufferedReader(new InputStreamReader(rssUrl.openStream()));
            ArrayList<String> titleList = new ArrayList<String>();
            String line;
            while ((line = in.readLine()) != null) {
                int titleEndIndex = 0;
                int titleStartIndex = 0;
                while (titleStartIndex >= 0) {
                    titleStartIndex = line.indexOf("<link>", titleEndIndex);
                    if (titleStartIndex >= 0) {
                        titleEndIndex = line.indexOf("</link>", titleStartIndex);
                      
                        titleList.add(line.substring(titleStartIndex + "<link>".length(), titleEndIndex));
                    }
                }
            }
            in.close();
            return titleList;
        } catch (MalformedURLException ue){
            System.out.println("Malformed URL");
        } catch (IOException ioe){
            System.out.println("Something went wrong reading the contents");
        }
        return null;
    }
}
