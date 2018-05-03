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

<jsp:directive.include file="menu.jsp" />




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


            <jsp:directive.include file="yanMenu.jsp" />

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
                                        <a href="<s:url value="advertView.action"/>?advertId=<%=advert.getId()%>" class="btn btn-outline-info" style="bottom: 10px; position: absolute;">Detaylı İncele</a>
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
