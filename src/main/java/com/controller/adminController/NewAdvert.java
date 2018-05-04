package com.controller.adminController;

import com.dao.impl.TypeDaoImpl;
import com.dao.impl.CityDaoImpl;
import com.dao.impl.GlobalDaoImpl;
import com.model.entities.*;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;
import org.apache.struts2.util.ServletContextAware;

import javax.servlet.ServletContext;
import java.io.File;
import java.io.FileInputStream;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

public class NewAdvert extends ActionSupport implements SessionAware, ServletContextAware {
    ServletContext context;
    private SessionMap<String, Object> sessionMap;

    private String name,type,city,district,neighborhood,addressDetail,coordinateLatitude,coordinateLongitude,explanation;
    private String price,telephone;
    private String mainPhotoName;
    private File mainPhoto,photo1,photo2;

    @Override
    public String execute() throws Exception {
        if (getName().equals("") || getType().equals("") || getCity().equals("") || getNeighborhood().equals("") ||
                getAddressDetail().equals("") || getCoordinateLongitude().equals("") || getCoordinateLatitude().equals("") ||
                getExplanation().equals("") || getPrice().equals("") || getTelephone().equals("")){
            sessionMap.put("resultState", "Başarsız");
            sessionMap.put("resultMessage", "Hiçbir Alanı Boş Bırakmamalısınız !");
            return ERROR;
        }
        boolean telephoneCheck = getTelephone().length()==11;
        boolean telephoneCheck2 = getTelephone().charAt(0)=='0' && getTelephone().charAt(1)=='5';
        if (!(telephoneCheck && telephoneCheck2)){
            sessionMap.put("resultState", "Başarsız");
            sessionMap.put("resultMessage", "Girdiğiniz telefon numarası hatalı !");
            return ERROR;
        }
        if (mainPhoto==null){
            sessionMap.put("resultState", "Başarsız");
            sessionMap.put("resultMessage", "Vitrin Fotoğrafı Eklemeniz Gerekir !");
            return ERROR;
        }
        for (int i=0;i<coordinateLatitude.length();i++){
            if (coordinateLatitude.charAt(i) == 'e' ){
                sessionMap.put("resultState", "Başarsız");
                sessionMap.put("resultMessage", "Enlem değerini doğru girdiğinizden emin olunuz.");
                return ERROR;
            }
        }
        for (int i=0;i<coordinateLongitude.length();i++){
            if (coordinateLongitude.charAt(i) == 'e' ){
                sessionMap.put("resultState", "Başarsız");
                sessionMap.put("resultMessage", "Boylam değerini doğru girdiğinizden emin olunuz.");
                return ERROR;
            }
        }
        for (int i=0;i<price.length();i++){
            if (price.charAt(i) == 'e' ){
                sessionMap.put("resultState", "Başarsız");
                sessionMap.put("resultMessage", "Fiyat değerini doğru girdiğinizden emin olunuz.");
                return ERROR;
            }
        }
        try{
            int priceI = Integer.parseInt(price);
            double coordinateLatitudeD = Double.parseDouble(coordinateLatitude);
            double coordinateLongitudeD = Double.parseDouble(coordinateLongitude);
        }catch (Exception e){
            e.printStackTrace();
            sessionMap.put("resultState", "Başarsız");
            sessionMap.put("resultMessage", "Fiyat ve koordinat değerlerini doğru girdiğinizden emin olunuz.");
            return ERROR;
        }
        GlobalDaoImpl globalDao = new GlobalDaoImpl();
        TypeDaoImpl typeDao = new TypeDaoImpl();
        CityDaoImpl cityDao = new CityDaoImpl();

        User user = (User) ServletActionContext.getRequest().getSession().getAttribute("user");


        try {
            System.out.println(getName()+" "+getType()+" "+getCity()+" "+getDistrict()+" "+getNeighborhood()+" " +
                    ""+getAddressDetail()+" "+getCoordinateLatitude()+" "+getCoordinateLongitude()+" "+getExplanation()+"" +
                    " "+getPrice()+" "+getTelephone());
            AdvertType advertType =  typeDao.findByName(type);
            System.out.println(advertType);
            City cityClass = cityDao.findByName(city);
            System.out.println(cityClass);
            if (advertType==null || cityClass==null ){
                sessionMap.put("resultState", "Başarsız");
                sessionMap.put("resultMessage", "Geçersiz bir parametre gönderildi");
                return ERROR;
            }
            double coordinateLatitudeDouble = Double.parseDouble(coordinateLatitude);
            double coordinateLongitudeDouble = Double.parseDouble(coordinateLongitude);
            int priceInt = Integer.parseInt(price);
            float defaultStar = 1;


            System.out.println(user.toString());
            Advert advert = new Advert(name, advertType, cityClass, district, neighborhood, addressDetail, coordinateLatitudeDouble,
                    coordinateLongitudeDouble, explanation, priceInt, telephone, defaultStar, user);

            globalDao.save(advert);

            byte[] imageInByte = new byte[(int)mainPhoto.length()];
            FileInputStream fileInputStream = new FileInputStream(mainPhoto);
            fileInputStream.read(imageInByte);
            fileInputStream.close();

            Images images = new Images();
            images.setName("mainPhoto");
            images.setImage(imageInByte);
            images.setAdvert(advert);

            globalDao.save(images);

            File[] fileList = {photo1,photo2};

            for (int i = 0; i<fileList.length ; i++) {
                File photo = fileList[i];
                if (photo!=null){
                    byte[] imageByte = new byte[(int)photo.length()];
                    FileInputStream fileInputStream1 = new FileInputStream(photo);
                    fileInputStream1.read(imageByte);
                    fileInputStream1.close();

                    Images images1 = new Images();
                    images1.setName("photo"+i);
                    images1.setImage(imageByte);
                    images1.setAdvert(advert);

                    globalDao.save(images1);
                }
            }

            List<Advert> advertList = (List<Advert>) context.getAttribute("advertList");

            advertList.add(advert);

            context.setAttribute("advertList",advertList);

            List<Advert> showcaseList = (List<Advert>) context.getAttribute("showCaseList");

            showcaseList.add(advert);

            context.setAttribute("showCaseList",showcaseList);

        }catch (Exception e){
            e.printStackTrace();
            sessionMap.put("resultState", "Başarsız");
            sessionMap.put("resultMessage", "İşleminiz geçici olarak gerçekleştirilemiyor. Lütfen daha sonra tekrar deneyin.");
            return ERROR;
        }

        sessionMap.put("resultState", "Başarılı");
        sessionMap.put("resultMessage", "Tebrikler. İlanınız başarılı bir şekilde yayınlandı !");
        return SUCCESS;
    }

    public void setSession(Map<String, Object> map) {
        sessionMap =(SessionMap) map;
    }

    public SessionMap<String, Object> getSessionMap() {
        return sessionMap;
    }

    public void setSessionMap(SessionMap<String, Object> sessionMap) {
        this.sessionMap = sessionMap;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        try {
            this.name = new String(name.getBytes("ISO-8859-1"),"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        try {
            this.district = new String(district.getBytes("ISO-8859-1"),"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public void setNeighborhood(String neighborhood) {
        try {
            this.neighborhood = new String(neighborhood.getBytes("ISO-8859-1"),"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public String getAddressDetail() {
        return addressDetail;
    }

    public void setAddressDetail(String addressDetail) {
        try {
            this.addressDetail = new String(addressDetail.getBytes("ISO-8859-1"),"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public String getCoordinateLatitude() {
        return coordinateLatitude;
    }

    public void setCoordinateLatitude(String coordinateLatitude) {
        this.coordinateLatitude = coordinateLatitude;
    }

    public String getCoordinateLongitude() {
        return coordinateLongitude;
    }

    public void setCoordinateLongitude(String coordinateLongitude) {
        this.coordinateLongitude = coordinateLongitude;
    }

    public String getExplanation() {
        return explanation;
    }

    public void setExplanation(String explanation) {
        try {
            this.explanation = new String(explanation.getBytes("ISO-8859-1"),"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public File getMainPhoto() {
        return mainPhoto;
    }

    public void setMainPhoto(File mainPhoto) {
        this.mainPhoto = mainPhoto;
    }

    public File getPhoto1() {
        return photo1;
    }

    public void setPhoto1(File photo1) {
        this.photo1 = photo1;
    }

    public File getPhoto2() {
        return photo2;
    }

    public void setPhoto2(File photo2) {
        this.photo2 = photo2;
    }

    public String getMainPhotoName() {
        return mainPhotoName;
    }

    public void setMainPhotoName(String mainPhotoName) {
        this.mainPhotoName = mainPhotoName;
    }

    public void setServletContext(ServletContext context) {
        this.context = context;
    }
}
