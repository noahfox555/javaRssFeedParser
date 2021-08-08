package com.rssreader.rssreader;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.io.*;

public class RSSFeedParser {
  private URL rssUrl;
  private String searchPhrase;
  private ArrayList<String> rssTitles;
  private ArrayList<String> links;
  private ArrayList<String> pubDates;
  private ArrayList<String> images;

  public RSSFeedParser(String feedURL, String searchPhrase) throws IOException {
    this.rssUrl = new URL(feedURL);
    this.searchPhrase = searchPhrase;
    // initialize arrays
    rssTitles = new ArrayList<String>();
    links = new ArrayList<String>();
    pubDates = new ArrayList<String>();
    images = new ArrayList<String>();
    // fill arrays
    setRssTitles();
    setLinks();
    setPubDates();
    setImages();
  }

  public ArrayList<String> getRssTitles() {
    return rssTitles;
  }

  public ArrayList<String> getLinks() {
    return links;
  }

  public ArrayList<String> getPubDates() {
    return pubDates;
  }

  public ArrayList<String> getImages() {
    return images;
  }

  // set titles of articles
  private void setRssTitles() throws IOException{
    BufferedReader in = new BufferedReader(new InputStreamReader(rssUrl.openStream()));
    // ArrayList<String> titleList = new ArrayList<String>();
    String line;
    while ((line = in.readLine()) != null) {
        int titleEndIndex = 0;
        int titleStartIndex = 0;
        while (titleStartIndex >= 0) {
            titleStartIndex = line.indexOf("<title>", titleEndIndex);
            if (titleStartIndex >= 0) {
                titleEndIndex = line.indexOf("</title>", titleStartIndex);
                // if the searchphrase is found anywhere in the title
                if (line.substring(titleStartIndex + "<title>".length(), titleEndIndex).toLowerCase().matches(".*" + searchPhrase + ".*")) {
                    line = line.replace("<![CDATA[", "");
                    line = line.replace("]]>", "");
                    rssTitles.add(line.substring(titleStartIndex + "<title>".length(), titleEndIndex));
                }
            }
        }
    }
    in.close();
    if (rssTitles.size() != 0) {
      rssTitles.remove(0);
    }
  }

  private void setLinks() throws IOException{
    BufferedReader in = new BufferedReader(new InputStreamReader(rssUrl.openStream()));
    String line;
    while ((line = in.readLine()) != null) {
        int linkEndIndex = 0;
        int linkStartIndex = 0;
        while (linkStartIndex >= 0) {
          linkStartIndex = line.indexOf("<link>", linkEndIndex);
            if (linkStartIndex >= 0) {
              linkEndIndex = line.indexOf("</link>", linkStartIndex);
                // if the searchphrase is found anywhere in the title
                // if (line.substring(linkStartIndex + "<title>".length(), linkEndIndex).toLowerCase().matches(".*" + searchPhrase + ".*")) {
                    line = line.replace("<![CDATA[", "");
                    line = line.replace("]]>", "");
                    links.add(line.substring(linkStartIndex + "<link>".length(), linkEndIndex));
                // }
            }    
        }
      
    }
    in.close();
    if (links.size() != 0) {
      links.remove(0);
    }
  }

  private void setPubDates() throws IOException{
    BufferedReader in = new BufferedReader(new InputStreamReader(rssUrl.openStream()));
    String line;
    while ((line = in.readLine()) != null) {
        int pubDateEndIndex = 0;
        int pubDateStartIndex = 0;
        while (pubDateStartIndex >= 0) {
          pubDateStartIndex = line.indexOf("<pubDate>", pubDateEndIndex);
            if (pubDateStartIndex >= 0) {
              pubDateEndIndex = line.indexOf("</pubDate>", pubDateStartIndex);
                // if the searchphrase is found anywhere in the title
                // if (line.substring(linkStartIndex + "<title>".length(), linkEndIndex).toLowerCase().matches(".*" + searchPhrase + ".*")) {
                    line = line.replace("<![CDATA[", "");
                    line = line.replace("]]>", "");
                    pubDates.add(line.substring(pubDateStartIndex + "<pubDate>".length(), pubDateEndIndex));
                // }
            }    
        }
      
    }
    in.close();
    // if (pubDates.size() != 0) {
    //   pubDates.remove(0);
    // }
  }

  private void setImages() throws IOException{
    BufferedReader in = new BufferedReader(new InputStreamReader(rssUrl.openStream()));
    String line;
    while ((line = in.readLine()) != null) {
        int imageEndIndex = 0;
        int imageStartIndex = 0;
        while (imageStartIndex >= 0) {
          imageStartIndex = line.indexOf("<image>", imageEndIndex);
            if (imageStartIndex >= 0) {
              imageEndIndex = line.indexOf("</image>", imageStartIndex);
                // if the searchphrase is found anywhere in the title
                // if (line.substring(pubDateStartIndex + "<pubDate>".length(), pubDateEndIndex).toLowerCase().matches(".*" + searchPhrase + ".*")) {
                    line = line.replace("<![CDATA[", "");
                    line = line.replace("]]>", "");
                    images.add(line.substring(imageStartIndex + "<image>".length(), imageEndIndex));
                // }
            }
        }
    }
    in.close();
    // if (images.size() != 0) {
    //   images.remove(0);
    // }
  }
}





// private ArrayList<String> setLinks() throws IOException{
//   BufferedReader in = new BufferedReader(new InputStreamReader(rssUrl.openStream()));
//   ArrayList<String> linkLists = new ArrayList<String>();
//   String line;
//   while ((line = in.readLine()) != null) {
//       int linkEndIndex = 0;
//       int linkStartIndex = 0;
//       int titleEndIndex = 0;
//       int titleStartIndex = 0;
//       //  only works with mlb
//       while (linkStartIndex >= 0 & titleStartIndex >= 0) {
//         linkStartIndex = line.indexOf("<link>", linkEndIndex);
//         titleStartIndex = line.indexOf("<title>", titleEndIndex);
//           if (linkStartIndex >= 0 & titleStartIndex >= 0) {
//             linkEndIndex = line.indexOf("</link>", linkStartIndex);
//             titleEndIndex = line.indexOf("</title>", titleStartIndex);
//               // if the searchphrase is found anywhere in the title
//               String tit = line.substring(titleStartIndex + "<title>".length(), titleEndIndex);
//               if (line.substring(titleStartIndex + "<title>".length(), titleEndIndex).toLowerCase().matches(".*" + searchPhrase + ".*")) {
//                   line = line.replace("<![CDATA[", "");
//                   line = line.replace("]]>", "");
//                   linkLists.add(line.substring(linkStartIndex + "<link>".length(), linkEndIndex));
//               }
//           }    
//       }
    
//   }
//   in.close();
//   if (linkLists.size() != 0) {
//     linkLists.remove(0);
//   }
//   return linkLists;
// }