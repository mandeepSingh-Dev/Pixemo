package com.example.unsplash.Categries;

public class ItemsCat
{
    int likes;
    int views;
    int downloads;
    String username;
    String imageURL;
    String imageDescription;

    String largeImageUrl;

    public ItemsCat(int likes,int views,int downloads,String username,String imageURL,String imageDescription,String largeImageUrl)
    {
        this.likes=likes;
        this.views=views;
        this.downloads=downloads;
        this.username=username;
        this.imageURL=imageURL;
        this.imageDescription=imageDescription;
        this.largeImageUrl=largeImageUrl;
    }

    public int getLikes() {
        return likes;
    }

    public int getViews() {
        return views;
    }

    public int getDownloads() {
        return downloads;
    }

    public String getImageURL() {
        return imageURL;
    }

    public String getImageDescription() {
        return imageDescription;
    }



    public String getLargeImageUrl() {
        return largeImageUrl;
    }
}
