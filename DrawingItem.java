package com.example.artexhibition;

public class DrawingItem {
    private int imageResourse;
    private String title;
    private String key_id;
    private String favStatus;
    private String desc;


    public DrawingItem(String title, String id, int image, String desc){

    }

    public DrawingItem(int imageRecourse, String title, String key_id, String favStatus, String desc) {
        this.imageResourse = imageRecourse;
        this.title = title;
        this.key_id = key_id;
        this.favStatus = favStatus;
        this.desc = desc;
    }

  public int getImageResourse(){
        return imageResourse;
  }
  public void setImageResourse(int imageResourse){
        this.imageResourse = imageResourse;
  }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getKey_id() {
        return key_id;
    }

    public void setKey_id(String key_id) {
        this.key_id = key_id;
    }

    public String getFavStatus() {
        return favStatus;
    }

    public void setFavStatus(String favStatus) {
        this.favStatus = favStatus;
    }
    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}


