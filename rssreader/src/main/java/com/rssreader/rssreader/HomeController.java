package com.rssreader.rssreader;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.*;

 

@Controller
public class HomeController {

    // Returns the homepage
    @GetMapping
    public String getHomePage() {
        // model.addAttribute("rssFeedURL", rssFeedInfo);
        return "homePage";
    }

    // returns page when something is searched for
    @PostMapping("/")
    public String rssFeedResults(Model model, String rssFeedURL, String searchPhrase) {
        // If link is not there or not a link page will be redirected
        
        try {
            RSSFeedParser feedAtrributes = new RSSFeedParser(rssFeedURL, searchPhrase);
            model.addAttribute("titlesList", feedAtrributes.getRssTitles());
            model.addAttribute("searchResults", feedAtrributes.getRssTitles().size());
            model.addAttribute("links", feedAtrributes.getLinks());
            model.addAttribute("pubDates", feedAtrributes.getPubDates());
            model.addAttribute("images", feedAtrributes.getImages());
            return "searchResults";
        } catch (IOException e) {
            return "noLinkGiven";
        }

        
  }
}