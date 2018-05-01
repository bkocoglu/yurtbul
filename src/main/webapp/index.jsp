<%@ page import="java.util.Iterator" %>
<%@ page import="com.model.entities.City" %>
<%@ page import="java.util.List" %>
<%@ page import="com.model.entities.Advert" %><%--
  Created by IntelliJ IDEA.
  User: pC2
  Date: 22.03.2018
  Time: 02:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="/load"/>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="keywords" content="Sitenin Tanıtımını Yapan Kelimeler">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/css/bootstrap.min.css" integrity="sha384-PsH8R72JQ3SOdhVi3uxftmaW6Vc51MKb0q5P2rRUpPvrszuE4W1povHYgTpBfshb" crossorigin="anonymous">
    <link rel="stylesheet" href="css/homePage.css">
    <link href="https://fonts.googleapis.com/css?family=Montserrat" rel="stylesheet">
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.3/umd/popper.min.js" integrity="sha384-vFJXuSJphROIrBnz7yo7oB41mKfc8JzQZiCq4NCceLEaO4IHwicKwpJf9c9IpFgh" crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/js/bootstrap.min.js" integrity="sha384-alpBpkh1PFOepccYVYDB4do5UnbKysX5WZXm3XxPqe5iKTfUKjNkCk9SaVuEZflJ" crossorigin="anonymous"></script>
    <script src="jquery/jquery-3.2.1.min.js"></script>
    <title>Ana Sayfa</title>
    <style>
        <jsp:directive.include file="css/homePage.css" />
    </style>
</head>
<body class="font">


<!-- MENU -->
<nav class="navbar navbar-expand-lg navbar-light bg-light header">
    <a class="navbar-brand" href="#">yurtbul.com <p></p></a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item menuItem">
                <a class="nav-link menuItem" href="#" >Ana Sayfa <span class="sr-only">(current)</span></a>
            </li>
        </ul>
        <ul id="uyeFavorilerMenu" class="navbar-nav mr-auto">
            <li class="nav-item menuItem">
                <a class="nav-link menuItem" href="#" >Favorilerim(0)</a>
            </li>
        </ul>
        <ul onclick="openNav1()" class="navbar-nav mr-auto">
            <li class="nav-item menuItem">
                <a class="nav-link menuItem" href="#" >Hakkımızda</a>
            </li>
        </ul>
        <ul onclick="openNav2()" class="navbar-nav mr-auto">
            <li class="nav-item menuItem">
                <a class="nav-link menuItem" href="#" >İletişim</a>
            </li>
        </ul>
        <form class="form-inline my-2 my-lg-0">
            <input class="form-control mr-sm-2" type="search" placeholder="Mekan, Şehir veya Semt" aria-label="Search">
            <button class="btn btn-outline-success my-2 my-sm-0" id="searchButton" type="submit">Ara</button>
        </form>
    </div>
</nav>


<%
    if (session.getAttribute("resultState")!=null){
%>
<div class="alert alert-primary" style="text-align: center;" role="alert">
    <b><%=session.getAttribute("resultMessage")%><b>
</div>

<%
        session.setAttribute("resultState",null);
        session.setAttribute("resultMessage", null);
    }
%>


<!-- YAN MENU -->



<div style="overflow: hidden;">
    <div class="row" id="homePage">
        <div class="col-md-2" id="yanMenu" style="padding-right: 20px; padding-left: 20px;">
            <div id="mySidenav" class="sidenav">
                <a href="javascript:void(0)" class="closebtn" onclick="closeNav()">&times;</a>
                <div class="row">
                    <div class="col-1"></div>
                    <div class="col">
                        <form action="advertFilter">
                            <div class="form-row">
                                <div class="form-group">
                                    <label for="inputState2" style="font-weight: bold;">Şehir</label>
                                    <select name="cityName" id="inputState2" class="form-control" style="width: 100%; margin-top: 5px;">
                                        <%
                                            List<City> cityList = (List<City>) application.getAttribute("allCity");
                                            for (City city:cityList) {
                                        %>
                                        <option value="<%=city.getCityId()%>"><%=city.getCityName()%></option>
                                        <%
                                            }
                                        %>
                                    </select>
                                    <label for="inputState" style="font-weight: bold; margin-top: 15px; margin-bottom: 5px;">Tür</label>
                                    <fieldset class="form-group">
                                        <div class="row">
                                            <div class="col">
                                                <div class="form-check" >
                                                    <label class="form-check-label" style="cursor: pointer;">
                                                        <input class="form-check-input" type="radio" name="gridRadios" id="gridRadios1" value="yurt">
                                                        <span>Yurt</span>
                                                    </label>
                                                </div>
                                            </div>
                                            <div class="col">
                                                <div class="form-check" >
                                                    <label class="form-check-label" style="cursor: pointer;">
                                                        <input class="form-check-input" type="radio" name="gridRadios" id="gridRadios2" value="apart">
                                                        Apart
                                                    </label>
                                                </div>
                                            </div>
                                            <div class="col">
                                                <div class="form-check" style="margin-left: 50px; margin-top: 10px;">
                                                    <label class="form-check-label" style="cursor: pointer;">
                                                        <input class="form-check-input" type="radio" name="gridRadios" id="gridRadios3" value="hepsi" checked>
                                                        Hepsi
                                                    </label>
                                                </div>
                                            </div>
                                        </div>
                                    </fieldset>
                                    <label for="inputState3" style="font-weight: bold;">Fiyat</label>
                                    <select id="inputState3" class="form-control" style="width: 100%; margin-top: 5px;">
                                        <option value="0">...</option>
                                        <option value="1">... - 400</option>
                                        <option value="2">400 - 600</option>
                                        <option value="3">600 - 800</option>
                                        <option value="4">800 - ...</option>
                                    </select>
                                </div>
                            </div>
                            <button type="submit" style="float: right;" class="btn btn-primary">Ara</button>
                        </form>
                    </div>
                    <div class="col-1"></div>
                </div>
            </div>
            <div id="mySidenav1" class="sidenav">
                <a href="javascript:void(0)" class="closebtn" onclick="closeNav1()">&times;</a>
                <div class="row" style="padding: 10px;">
                    <div class="col">
                    <div class="abautAndContact">
                        <p style="text-align: center; margin-bottom: 10px; font-size: 20px; font-style: italic; color: #6D4C41;">Hakkımızda</p>
                        <p style="color: #6D4C41;">
                            &nbsp;&nbsp;Bu projede amacımız üniversiteye giden veya gidecek olan arkadaşlarımızın zahmet çekmeden sokak sokak dolaşmadan kalacak bir mekan bulmalarıdır.<br><br>
                            &nbsp;&nbsp;Sitemizde istediğiniz yurt ve apartın çevresini ya da yerleşmek istediğiniz muhitin sokaklarını Google Maps sayesinde gezebilir, hemen karar vermek zorunda kalmayıp favorilerinize ekleyebilir daha sonra seçeneklerinizi tekrar gözden geçirebilirsiniz. Yorumlarla diğer kullanıcılara yardımcı olabilirsiniz.<br><br>
                            &nbsp;&nbsp;Bize destek olup sosyal medya hesaplarınızda paylaşmayı ve arkadaşlarınıza tavsiye etmeyi ihmal etmeyin. Çalışmalarımız sizlerin rahatlığı ve mutluluğu için devam edecektir.
                        </p>
                    </div>
                </div>
            </div>
        </div>
        <div id="mySidenav2" class="sidenav">
            <a href="javascript:void(0)" class="closebtn" onclick="closeNav2()">&times;</a>
            <div class="row" style="padding: 10px;">
                <div class="col">
                <div class="abautAndContact">
                    <p style="text-align: center; margin-bottom: 10px; font-size: 20px; font-style: italic; color: #6D4C41;">İletişim</p>

                    <p style="font-weight: bold; margin-top: 15px;">&#8226; Bize ulaşabileceğiniz hesap ve adresler;</p>
                    <p style="vertical-align: middle; display: table;">
                        <img width="40" height="40" align="left" src="images/gmailIcon.png"/><span style="display: table-cell; vertical-align: middle; font-size: x-small; font-weight: bold;">bilal.kocoglu@outlook.com.tr</span>
                    </p>
                    <p style="display: table; text-align: center; text-decoration: none;">
                        <a style="display: table-cell; vertical-align: middle;" href="#" target="_blank">
                            <img width="55" height="55" src="images/facebookIcon.png">
                        </a>
                        <a style="display: table-cell; vertical-align: middle;" href="#" target="_blank">
                            <img width="50" height="50" src="images/bloggerIcon.png">
                        </a>
                    <p>
                </div>
            </div>
        </div>
    </div>

    <button class="button btn btn-info col-lg" onclick="openNav()" style="width: 100%; margin-left: 0px;"><span>Özel Arama<br> Seçenekleri </span></button>

</div>

        <!-- VİTRİN -->




        <div class="col-md" id="showCase">
            <div class="row" style="padding-left: 10px; padding-right: 10px; padding-bottom: 10px; background-color:#F5F5F5; border-radius: 10px; font-weight: bold; margin-bottom: 10px;">
                <div class="row">
                    <%
                        List<Advert> advertList = (List<Advert>)application.getAttribute("showCaseList");
                        if (advertList==null || advertList.isEmpty()){


                    %>

                    <%
                        }else {
                            for (Advert advert:advertList) {
                    %>

                            <div class="col-sm-4 col-md-4">
                                <div class="card vitrinbox">
                                    <img class="card-img-top" src="<s:url value="image.action"/>?advertId=<%=advert.getId()%>" alt="Card image cap" style="height: 165px;" >
                                    <div class="card-body">
                                        <p class="card-title" style="text-align: center;"><%=advert.getName()%></p>
                                        <p class="card-text" style="font-size: small;"><%=advert.getCity().getCityName()%>/<%=advert.getDistrict()%>/<%=advert.getNeighborhood()%></p>
                                        <div class="imageTopRight">
                                            <p style="text-align: right;"><img src="images/starIcon.png" width="15" height="15"><%=advert.getStar()%></p>
                                        </div>
                                        <a href="#" class="btn btn-outline-info" style="bottom: 10px; position: absolute;">Detaylı İncele</a>
                                    </div>
                                </div>
                            </div>

                    <%
                            }
                        }
                    %>

                </div>
            </div>

            <!--        SAYFA GEÇİŞLERİ
                <div class="row">
                    <div class="btn-group " role="group" aria-label="First group" style="Margin: 0px auto;">
                        <a href="#" class="btn btn-secondary" role="button">1</a>
                        <a href="#" class="btn btn-secondary" role="button">2</a>
                        <a href="#" class="btn btn-secondary" role="button">3</a>
                        <a href="#" class="btn btn-secondary" role="button">4</a>
                    </div>
                </div>
            -->
        </div>






<!-- LOGİN -->


<jsp:directive.include file="login.jsp" />


<!-- FOOTER -->


<jsp:directive.include file="footer.jsp" />

<!-- HATALI UYARISI -->



<div id="myModal1" class="modal">
    <!-- Modal content -->
    <div class="modal-content">
        <span id="alertExit" class="close">&times;</span>
        <p id="errorText" style="font-weight: bold; text-align: center; margin-top: 25px;">Yanlış bir mail adresi girdiniz.<br>Lütfen tekrar deneyin.</p>
    </div>
</div>


<!-- SCRIPTS -->

<jsp:directive.include file="scripts.jsp" />


</body>
</html>
