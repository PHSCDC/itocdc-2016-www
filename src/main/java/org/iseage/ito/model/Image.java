package org.iseage.ito.model;

public class Image {

    private String image;
    private int approved;

    public Image() {}

    public Image(String image) {
        this.image = image;
        this.approved = 0;
    }

    public Image(String image, int approved) {
        this.image = image;
        this.approved = approved;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getApproved() {
        return approved;
    }

    public void setApproved(int approved) {
        this.approved = approved;
    }
}
