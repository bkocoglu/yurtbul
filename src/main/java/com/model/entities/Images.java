package com.model.entities;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;

@Entity
@Table
public class Images {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int imageId;

    private String name;

    @ManyToOne()
    private Advert advert;

    @Lob
    private byte[] image;

    public Images() {
    }

    public int getImageId() {

        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Advert getAdvert() {
        return advert;
    }

    public void setAdvert(Advert advert) {
        this.advert = advert;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
}
