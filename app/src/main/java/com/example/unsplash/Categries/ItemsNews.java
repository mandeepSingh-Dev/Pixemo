package com.example.unsplash.Categries;

public class ItemsNews
{
      String title;
     String description;
     String url;
     String imageurl;
     String publishedAt;
    String name;
    public ItemsNews(String title,String description,String url,String imageurl,String publishedAt,String name)
    {
        this.title=title;
        this.description=description;
        this. url=url;
        this. name=name;
        this.imageurl=imageurl;
        this.publishedAt=publishedAt;
    }
    public ItemsNews(String title,String url,String publishedAt,String imageurl)
    {
        this.title=title;
        this. url=url;
        this. publishedAt=publishedAt;
        this.imageurl=imageurl;
    }

    public  String getTitleee() {
        return title;
    }

    public  String getName() {
        return name;
    }

    public  String getDescription() {
        return description;
    }

    public  String getPublishedAt() {
        return publishedAt;
    }

    public  String getUrl() {
        return url;
    }

    public  String getImageurl() {
        return imageurl;
    }


}
