<%@ page import="java.util.Iterator" %>
<%@ page import="com.model.entities.City" %>
<%@ page import="java.util.List" %>
<%@ page import="com.model.entities.Advert" %>
<%--
  Created by IntelliJ IDEA.
  User: pC2
  Date: 02.05.2018
  Time: 00:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
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
    <link rel="stylesheet" type="text/css" href="css/ozelListe.css">
    <title>Özel Liste</title>
</head>
<body class="font">


<!-- MENU -->

<jsp:directive.include file="menu.jsp" />



<!-- YAN MENU -->


<jsp:directive.include file="yanMenu.jsp" />


<!-- MEKAN İÇERİK -->




<div class="col-md" id="showCase" >

    <!-- SAYFA BAŞLIGI -->
    <%
        if (session.getAttribute("resultState")!=null){
    %>
        <div class="alert alert-info" role="alert" style="text-align: center;">
            <p style="font-size: 22px;"><%=session.getAttribute("resultMessage")%></p>
        </div>

    <%
            session.setAttribute("resultState",null);
            session.setAttribute("resultMessage", null);
        }
    %>
    <!-- SONUÇLAR -->
    <%
        List<Advert> filterResult = (List<Advert>)session.getAttribute("filterResultList");

        if (filterResult==null || filterResult.isEmpty()){
    %>

    <%
        }else {
            for (Advert advert: filterResult) {
    %>
            <div class="row listItem">
                <div class="col-lg-4 col-sm-6" style="padding-left: 0px; padding-right: 0px;">
                    <img style="border-radius: 5px; min-height: 180px;" width="240" height="180" src="<s:url value="image.action"/>?advertId=<%=advert.getId()%>">
                </div>
                <div class="col-lg-6 col-sm-6">
                    <div class="alert alert-secondary" role="alert">
                        <p style="font-weight: bold; font-size: small; text-align: center;"><%=advert.getName()%></p>
                    </div>
                    <p style="font-size: small;"><span style="font-weight: bold; font-size: xx-small;">ADRES : </span><%=advert.getCity().getCityName()%>/<%=advert.getDistrict()%>/<%=advert.getNeighborhood()%></p>
                    <p style="font-size: small;"><span style="font-weight: bold; font-size: xx-small;">FİYAT : </span><%=advert.getPrice()%>₺</p>
                    <p style="font-size: small;"><span style="font-weight: bold; font-size: xx-small;">TELEFON : </span><%=advert.getTelephone()%></p>
                </div>
                <div class="col-lg-2 col-sm-12" style="text-align: center; padding: 0px;">
                    <p style="text-align: right;"><img width="20" height="20" src="images/starIcon.png"><span><%=advert.getStar()%></span></p>
                    <a style="margin-top: 20px; margin-bottom: 5px; " class="btn btn-success" href="<s:url value="advertView.action"/>?advertId=<%=advert.getId()%>" role="button">İncele</a>
                </div>
            </div>
    <%
            }
        }
    %>
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
