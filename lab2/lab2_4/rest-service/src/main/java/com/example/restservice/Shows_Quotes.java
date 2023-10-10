package com.example.restservice;

import java.util.List;

public class Shows_Quotes {

    private final List<String> quotes;
    private final String show_name;

    public Shows_Quotes(String show_name, List<String> quotes) {
        this.show_name = show_name;
        this.quotes = quotes;
    }

    public List<String> getQuotes() {
        return quotes;
    }
    public String getShow_name() {
        return show_name;
    }
     
}
