package com.model.entities;

import javax.persistence.*;


@Entity
@Table
public class Advert {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String name;

    @ManyToOne()
    private AdvertType advertType;

    @ManyToOne()
    private City city;

    private String district;        //ilçe

    private String neighborhood;    //mahalle

    private String addressDetail;

    private double coordinateLatitude;      //enlem

    private double coordinateLongitude;     //boylam

    private String explanation;

    private int price;

    private String telephone;

    private float star;

    @ManyToOne()
    private User user;

    public Advert() {
    }

    public Advert(String name, AdvertType advertType, City city, String district, String neighborhood, String addressDetail,
                  double coordinateLatitude, double coordinateLongitude, String explanation, int price, String telephone, float star, User user) {
        this.name = name;
        this.advertType = advertType;
        this.city = city;
        this.district = district;
        this.neighborhood = neighborhood;
        this.addressDetail = addressDetail;
        this.coordinateLatitude = coordinateLatitude;
        this.coordinateLongitude = coordinateLongitude;
        this.explanation = explanation;
        this.price = price;
        this.telephone = telephone;
        this.star = star;
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public float getStar() {
        return star;
    }

    public void setStar(float star) {
        this.star = star;
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

    public AdvertType getAdvertType() {
        return advertType;
    }

    public void setAdvertType(AdvertType advertType) {
        this.advertType = advertType;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
    }

    public String getAddressDetail() {
        return addressDetail;
    }

    public void setAddressDetail(String addressDetail) {
        this.addressDetail = addressDetail;
    }

    public double getCoordinateLatitude() {
        return coordinateLatitude;
    }

    public void setCoordinateLatitude(double coordinateLatitude) {
        this.coordinateLatitude = coordinateLatitude;
    }

    public double getCoordinateLongitude() {
        return coordinateLongitude;
    }

    public void setCoordinateLongitude(double coordinateLongitude) {
        this.coordinateLongitude = coordinateLongitude;
    }

    public String getExplanation() {
        return explanation;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    @Override
    public String toString() {
        return "Advert{" +
                "name='" + name + '\'' +
                ", advertType=" + advertType +
                ", city=" + city +
                ", district='" + district + '\'' +
                ", neighborhood='" + neighborhood + '\'' +
                ", addressDetail='" + addressDetail + '\'' +
                ", coordinateLatitude=" + coordinateLatitude +
                ", coordinateLongitude=" + coordinateLongitude +
                ", explanation='" + explanation + '\'' +
                ", price=" + price +
                ", telephone='" + telephone + '\'' +
                ", star=" + star +
                ", user=" + user +
                '}';
    }
}
