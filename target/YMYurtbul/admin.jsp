<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.model.entities.User" %>
<%@ page import="com.model.entities.City" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="java.util.List" %>
<%@ page import="com.controller.ShowImage" %>
<%@ page import="com.model.entities.Comment" %>
<%@ page import="com.model.entities.Advert" %><%--
  Created by IntelliJ IDEA.
  User: pC2
  Date: 26.04.2018
  Time: 17:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="keywords" content="Sitenin Tanıtımını Yapan Kelimeler">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/css/bootstrap.min.css" integrity="sha384-PsH8R72JQ3SOdhVi3uxftmaW6Vc51MKb0q5P2rRUpPvrszuE4W1povHYgTpBfshb" crossorigin="anonymous">
    <link href="https://fonts.googleapis.com/css?family=Montserrat" rel="stylesheet">
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.3/umd/popper.min.js" integrity="sha384-vFJXuSJphROIrBnz7yo7oB41mKfc8JzQZiCq4NCceLEaO4IHwicKwpJf9c9IpFgh" crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/js/bootstrap.min.js" integrity="sha384-alpBpkh1PFOepccYVYDB4do5UnbKysX5WZXm3XxPqe5iKTfUKjNkCk9SaVuEZflJ" crossorigin="anonymous"></script>
    <script src="jquery/jquery-3.2.1.min.js"></script>
    <script src="http://blueimp.github.io/Gallery/js/jquery.blueimp-gallery.min.js"></script>
    <link rel="stylesheet" type="text/css" href="css/homePage.css">
    <link rel="stylesheet" type="text/css" href="css/admin.css">
    <title>Admin</title>
</head>
<body class="font">
<!-- MENU -->
<nav class="navbar navbar-expand-lg navbar-light bg-light header">
    <a class="navbar-brand" href="#">yurtbul.com <p></p></a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <span style="font-weight: bold;">Admin Sistemi</span>
    </div>
</nav>

<%
    if (session.getAttribute("resultState")!=null){
%>
<div class="alert alert-success" style="text-align: center;" role="alert">
    <b><%=session.getAttribute("resultMessage")%></b>
</div>

<%
        session.setAttribute("resultState",null);
        session.setAttribute("resultMessage", null);
    }
%>

<!-- YAN MENU -->



<div style="overflow: hidden;">
    <div class="row" id="homePage" style="margin: 0px;">
        <div class="col-md-2" id="yanMenu" style="padding-right: 20px; padding-left: 20px;">
            <button id="mekanEkleButton" class="button btn btn-info col-lg" style="width: 100%; margin-left: 0px;"><span>Mekan Ekle</span></button>
            <button id="yorumlarButton" class="button btn btn-info col-lg" style="width: 100%; margin-left: 0px;"><span>Onay Bekleyen<br> Yorumlar(<%=((List<Comment>)application.getAttribute("pendingComments")).size()%>)</span></button>
            <button id="goruntulenmeButton" class="button btn btn-info col-lg" style="width: 100%; margin-left: 0px;"><span>İlanlarım(<%=((List<Advert>)(application.getAttribute("advertList"))).size()%>)</span></button>
        </div>






        <!-- ORTA İÇERİK -->




        <div class="col-md" id="showCase" >
            <!-- MEKAN EKLEME -->
            <div class="card" id="mekanEkle">
                <div class="card-header" style="color: #9CCC65">
                    Mekan Ekle
                </div>
                <div class="card-body">
                    <s:form action="createAdvert" method="post" enctype="multipart/form-data">
                        <div class="row">
                            <div class="col-md-6 mb-3">
                                <label class="inputsLabel">İşletme Adı</label>
                                <input name="name" type="text" class="form-control is-invalid" id="validationServer07" placeholder="İşletme Adı" required>
                            </div>
                            <div class="col-md-6 mb-3">
                                <label class="inputsLabel">Tür</label>
                                <select name="type" class="form-control inputsItem">
                                    <option>yurt</option>
                                    <option>apart</option>
                                </select>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-3 mb-3">
                                <label class="inputsLabel">Şehir</label>
                                <select name="city" class="form-control inputsItem">
                                    <%
                                        List<City> cityList = (List<City>) application.getAttribute("allCity");
                                        for (City city:cityList) {
                                    %>
                                    <option><%=city.getCityName()%></option>
                                    <%
                                        }
                                    %>
                                </select>
                            </div>
                            <div class="col-md-3 mb-3">
                                <label class="inputsLabel">İlçe</label>
                                <input name="district" type="text" class="form-control is-invalid" id="validationServer04" placeholder="Mahalle" required/>
                            </div>
                            <div class="col-md-6 mb-3">
                                <label class="inputsLabel">Mahalle</label>
                                <input name="neighborhood" type="text" class="form-control is-invalid" id="validationServer05" placeholder="İlçe" required/>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-12 mb-3">
                                <label class="inputsLabel">Adres Detay</label>
                                <input name="addressDetail" type="text" class="form-control is-invalid" id="validationServer08" placeholder="Adres Detay" required/>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-6 mb-3">
                                <label class="inputsLabel">Koordinatlar(Enlem)</label>
                                <input name="coordinateLatitude" type="number" step="0.000000000000001" class="form-control is-invalid inputsItem" placeholder="Enlem" required/>
                            </div>
                            <div class="col-md-6 mb-3">
                                <label class="inputsLabel">Koordinatlar(Boylam)</label>
                                <input name="coordinateLongitude" type="number" step="0.000000000000001" class="form-control is-invalid inputsItem" placeholder="Boylam" required/>
                            </div>
                            <p style="width: 100%; font-weight: bold; font-size: x-small; margin-bottom: 10px;">
                                İşletmenizin Google Map'te gözükebilmesi için doğru koordinatları girmeniz tavsiye edilir.
                            </p>
                        </div>
                        <div class="row">
                            <div class="col-md-12 mb-3">
                                <label class="inputsLabel">Açıklama-Tanıtım Metni</label>
                                <textarea name="explanation" class="form-control is-invalid" style="margin-top: 8px; margin-bottom: 8px; font-size: small;" maxlength="700" rows="3" placeholder="Açıklama-Tanıtım"></textarea>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-6 mb-3">
                                <label class="inputsLabel">Fiyat (₺)</label>
                                <input name="price" type="number" class="form-control is-invalid inputsItem" placeholder="Fiyat (₺)" required/>
                            </div>
                            <div class="col-md-6 mb-3">
                                <label class="inputsLabel">Telefon</label>
                                <input name="telephone" type="number" class="form-control is-invalid inputsItem" id="telNoInput1" placeholder="+9" required/>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col">
                                <div class="alert alert-danger" style="text-align: center;" role="alert">
                                    <b>Lütfen fotoğraflarınızı jpg ve png uzantılı olarak seçiniz. Aksi takdirde ilanınız yayınlanmayacaktır.</b>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-6 mb-3">
                                <label class="inputsLabel">Vitrin Fotoğrafı</label>
                                <input name="mainPhoto" type="file" class="form-control-file inputsItem" style="margin-bottom: 0px;"/>
                            </div>
                            <div class="col-md-6 mb-3">
                                <label class="inputsLabel">Fotoğraf</label>
                                <input name="photo1" type="file" class="form-control-file inputsItem" style="margin-bottom: 0px;"/>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-6 mb-3">
                                <label class="inputsLabel">Fotoğraf</label>
                                <input name="photo2" type="file" class="form-control-file inputsItem" accept="image/gif,image/jpeg,image/jpg,image/png" style="margin-bottom: 0px;">
                            </div>
                        </div>
                        <div class="row">
                            <div class="col">
                                <div class="alert alert-primary" style="text-align: center;" role="alert">
                                    <b>Bu işlem 2-5 dakika sürebilir. Lütfen Bekleyin.</b>
                                </div>
                            </div>
                        </div>
                        <s:submit id="mekanEkleButton" cssClass="btn btn-success" value="Ekle"/>
                    </s:form>
                </div>
            </div>

            <!-- ONAY BEKLEYEN YORUMLAR -->

            <div id="onayBekleyenYorumlar">
                <%
                    if (application.getAttribute("commentCount").equals("0")){
                %>
                        <div class="row">
                            <div class="col">
                                <div class="alert alert-warning" style="text-align: center; margin-top: 5px;" role="alert">
                                    <b>Onay bekleyen bir yorumunuz bulunmamakta.</b>
                                </div>
                            </div>
                        </div>
                <%
                    }
                    if (!application.getAttribute("commentCount").equals("0")){
                        for (Comment comment: (List<Comment>)application.getAttribute("pendingComments")) {
                %>
                        <div class="row yorumItem">
                            <p class="yorumYapan"><%= comment.getSenderName() %><span style="float: right;"><%= comment.getDate() %></span></p>
                            <p class="yorumMetni"><%= comment.getCommentText() %></p>
                            <div style="width: 100%; text-align: center; margin-top: 10px;">
                                <a class="btn btn-success" href="<s:url value="commentAccept.action"/>?islem=ekle&commentId=<%=comment.getCommentId()%>" role="button">Onayla</a>
                                <a style="padding-left: 30px; padding-right: 30px; margin-left: 20px;" class="btn btn-danger" href="<s:url value="commentAccept.action"/>?islem=sil&commentId=<%=comment.getCommentId()%>" role="button">Sil</a>
                            </div>
                        </div>
                <%
                        }
                    }
                %>



            </div>
            <!-- GÖRÜNTÜLENME -->

            <div id="goruntulenme">
                <%
                    List<Advert> advertList = (List<Advert>)application.getAttribute("advertList");
                    if (advertList.isEmpty()){
                %>
                        <div class="row">
                            <div class="col">
                                <div class="alert alert-warning" style="text-align: center; margin-top: 5px;" role="alert">
                                    <b>Henüz bir ilanınız bulunmamakta.</b>
                                </div>
                            </div>
                        </div>
                <%
                    }
                    if (!advertList.isEmpty()){
                        for (Advert advert:advertList) {
                %>
                        <div class="row yorumItem" style="font-size: small; font-weight: bold; text-align: center; display: table;">
                            <div class="col" style="display: table-cell; vertical-align: middle;">
                                <p><%=advert.getName()%></p>
                            </div>
                            <div class="col" style="display: table-cell; vertical-align: middle;">
                                <p><img src="images/starIcon.png" width="15" height="15"><%=advert.getStar()%></p>
                            </div>
                            <div class="col" style="text-align: right; display: table-cell;">
                                <a class="btn btn-danger" href="<s:url value="deleteAdvert.action"/>?id=<%=advert.getId()%>" role="button">Sil</a>
                            </div>
                        </div>
                <%
                        }
                    }
                %>
            </div>

        </div>






        <!-- LOGİN -->





        <div class="col-md-3" id="login">
            <a name="loginIsinla">
                <div class="row">
                    <div class="col-lg-1"></div>
                    <div class="col-lg" style="background-color: #F5F5F5; border-radius: 10px;">
                        <div id="uyeBilgileri">
                            <p>Hoşgeldin <span style="font-weight: bold;"><%=((User)session.getAttribute("user")).getNewName()+" "+ ((User)session.getAttribute("user")).getNewLastName()%></span></p>
                            <a style="float: right; margin-top: 15px; margin-bottom: 15px; width: 100%;" class="btn btn-danger" href="<s:url value="logout.action"/>" role="button">Çıkış Yap</a>
                        </div>
                    </div>
                    <div class="col-lg-1"></div>
                </div>
            </a>
        </div>
    </div>
</div>





<!-- FOOTER -->




<footer>
    <div class="row">
        <div class="col-md" style="text-align: center;">
            <span style="font-size: xx-small;">&copy; Bilal Koçoğlu 2017</span>
        </div>
    </div>
</footer>



<!-- HATALI UYARISI -->



<div id="myModal1" class="modal">
    <!-- Modal content -->
    <div class="modal-content">
        <span id="alertExit" class="close">&times;</span>
        <p id="errorText" style="font-weight: bold; text-align: center; margin-top: 25px;">Yanlış bir mail adresi girdiniz.<br>Lütfen tekrar deneyin.</p>
    </div>
</div>

<script>


    //------------------------------
    var span = document.getElementById("alertExit");
    var modal1 = document.getElementById('myModal1');

    //TELEFON NUMARASI KONTROLÜ


    //SAYFA İÇERİĞİ YÖNETİMİ

    var mekanEkleButton = $("#mekanEkleButton");
    var yorumlarButton = $("#yorumlarButton");
    var goruntulenmeButton = $("#goruntulenmeButton");

    mekanEkleButton.click(function(){
        $("#mekanEkle").show(500);
        $("#onayBekleyenYorumlar").hide(500);
        $("#goruntulenme").hide(500);
    });
    yorumlarButton.click(function(){
        $("#mekanEkle").hide(500);
        $("#onayBekleyenYorumlar").show(500);
        $("#goruntulenme").hide(500);
    });
    goruntulenmeButton.click(function(){
        $("#mekanEkle").hide(500);
        $("#onayBekleyenYorumlar").hide(500);
        $("#goruntulenme").show(500);
    });
</script>
</body>
</html>
