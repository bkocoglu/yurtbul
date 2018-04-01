package com.model.entities;

import javax.persistence.*;


@Entity
@Table
public class Advert {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column
    private String name;

    @Column
    private int price;

    @Column
    private String explanation;

    @ManyToOne(cascade = CascadeType.ALL)
    private AdvertType typeId;

    @Override
    public String toString() {
        return "Advert{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", explanation='" + explanation + '\'' +
                ", typeId=" + typeId +
                '}';
    }

    public Advert() {
    }

    public Advert(String name, int price, String explanation) {

        this.name = name;
        this.price = price;
        this.explanation = explanation;
    }

    public Advert(String name, int price, String explanation, AdvertType typeId) {

        this.name = name;
        this.price = price;
        this.explanation = explanation;
        this.typeId = typeId;
    }

    public int getId() {

        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getExplanation() {
        return explanation;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }

    public AdvertType getTypeId() {
        return typeId;
    }

    public void setTypeId(AdvertType typeId) {
        this.typeId = typeId;
    }
}
