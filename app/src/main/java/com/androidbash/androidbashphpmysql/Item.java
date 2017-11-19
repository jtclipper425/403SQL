package com.androidbash.androidbashphpmysql;

public class Item {

    private int id;
    private String itemTitle;
    private String imageLink;
    private String itemQuality;

    public Item(int id, String itemTitle, String imageLink, String itemQuality) {
        this.id = id;
        this.itemTitle = itemTitle;
        this.imageLink = imageLink;
        this.itemQuality = itemQuality;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getItemTitle() { return itemTitle; }

    public void setItemTitle(String itemTitle) { this.itemTitle = itemTitle; }

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }

    public String getItemQuality() {
        return itemQuality;
    }

    public void setItemQuality(String itemQuality) {
        this.itemQuality = itemQuality;
    }

}
