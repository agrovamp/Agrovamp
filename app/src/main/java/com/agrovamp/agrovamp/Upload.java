package com.agrovamp.agrovamp;

/**
 * Created by Nishat Sayyed on 15-03-2018.
 */

public class Upload {
    private String name;
    private String url;

    public Upload() {

    }

    public Upload(String name, String url) {
        this.name = name;
        this.url = url;
    }

    public String getName() {
        return name;
    }
    public String getUrl() {
        return url;
    }
}
