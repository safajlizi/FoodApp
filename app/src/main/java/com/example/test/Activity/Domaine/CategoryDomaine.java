package com.example.test.Activity.Domaine;

public class CategoryDomaine {
    private String title;
    private String pic;
    public CategoryDomaine(String title , String pic){
        this.title=title;
        this.pic = pic;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
