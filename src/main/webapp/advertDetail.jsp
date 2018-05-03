<%@ page import="java.util.Iterator" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.HashSet" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="com.model.entities.City" %>
<%@ page import="java.util.List" %>
<%@ page import="com.model.entities.Advert" %>
<%@ page import="com.model.entities.*" %>
<%@ page import="com.model.entities.Comment" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%--
  Created by IntelliJ IDEA.
  User: pC2
  Date: 02.05.2018
  Time: 23:57
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
    <link rel="stylesheet" type="text/css" href="css/mekanDetay.css">
    <title>Mekan Detay</title>
</head>
<body class="font">

<%
    Advert advert = (Advert) application.getAttribute("selectedAdvert");
%>

<!-- MENU -->

<jsp:directive.include file="menu.jsp" />


<!-- MEKAN ADI -->

<%
    if (advert==null){
        if (session.getAttribute("resultState")!=null){
%>
            <div class="row" style="margin:0px;">
                <div class="col-2"></div>
                <div class="col">
                    <div class="alert alert-info" role="alert" style="text-align: center;">
                        <p style="font-size: 22px;text-align: center;"><%=session.getAttribute("resultMessage")%></p>
                    </div>
                </div>
                <div class="col-2"></div>
            </div>
<%
            session.setAttribute("resultState",null);
            session.setAttribute("resultMessage", null);
        }
    }else {
%>
            <div class="row" style="margin:0px;">
                <div class="col-2"></div>
                <div class="col">
                    <div class="alert alert-info" role="alert" style="text-align: center;">
                        <p style="font-size: 22px;text-align: center;"><%=advert.getName()%></p>
                    </div>
                </div>
                <div class="col-2"></div>
            </div>
<%
    }
    if (session.getAttribute("commentMessage")!=null){
%>
            <div class="row" style="margin:0px;">
                <div class="col-2"></div>
                <div class="col">
                    <div class="alert alert-danger" role="alert" style="text-align: center;">
                        <p style="font-size: 15px;text-align: center;"><%=session.getAttribute("commentMessage")%></p>
                    </div>
                </div>
                <div class="col-2"></div>
            </div>
<%
    }
        session.setAttribute("commentMessage",null);
%>



<!-- YAN MENU -->

<div style="overflow: hidden;">
    <div class="row" id="homePage" style="margin: 0px;">
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
                                    <select name="cityId" id="inputState2" class="form-control" style="width: 100%; margin-top: 5px;">
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
                                    <select name="priceRange" id="inputState3" class="form-control" style="width: 100%; margin-top: 5px;">
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
                <div class="col" >
                <div class="abautAndContact">
                    <p style="text-align: center; margin-bottom: 10px; font-size: 20px; font-style: italic; color: #6D4C41;">İletişim</p>

                    <p style="font-weight: bold; margin-top: 15px;">&#8226; Bize ulaşabileceğiniz hesap ve adresler;</p>
                    <p style="vertical-align: middle; display: table;">
                        <img width="40" height="40" align="left" src="images/gmailIcon.png"/><span style="display: table-cell; vertical-align: middle; font-size: x-small; font-weight: bold;">bilal.kocoglu@outlook.com.tr</span>
                    </p>
                    <p style="display: table; text-align: center; text-decoration: none;">
                        <a style="display: table-cell; vertical-align: middle;" href="#" target="_blank">
                            <img width="55" height="55" src="images/facebookIcon.png"/>
                        </a>
                        <a style="display: table-cell; vertical-align: middle;" href="#" target="_blank">
                            <img width="50" height="50" src="images/bloggerIcon.png"/>
                        </a>
                    <p>
                </div>
            </div>
        </div>
    </div>

    <button class="button btn btn-info col-lg" onclick="openNav()" style="width: 100%; margin-left: 0px;"><span>Özel Arama<br> Seçenekleri </span></button>

    <%
        if (advert!=null){
    %>
            <!-- PUAN -->

            <p style="font-weight: bold; display: table; float: right; margin-top: 5px; margin-bottom: 5px;"><img width="25" height="25" src="images/starIcon.png"><span style="display: table-cell; vertical-align: middle;"><%=advert.getStar()%></span></p>

            <!-- MEKAN ADRES VE İLETİŞİM İLGİLERİ -->

            <div class="contact">
                <p><span style="font-size: x-small;">Şehir : </span><span class="bold" style="font-size: small;"><%=advert.getCity().getCityName()%></span></p>
                <p><span style="font-size: x-small;">İlçe : </span><span class="bold" style="font-size: small;"><%=advert.getDistrict()%></span></p>
                <p><span style="font-size: x-small;">Mahalle : </span><span class="bold" style="font-size: small;"><%=advert.getNeighborhood()%></span></p>
                <p><span style="font-size: x-small;">Adres : </span><span class="bold" style="font-size: small;"><%=advert.getAddressDetail()%></span></p>
                <p><span style="font-size: x-small;">Yurt Sorumlusu : </span><span class="bold" style="font-size: small;"><%=advert.getUser().getNewName()%></span></p>
                <p><span style="font-size: x-small;">Telefon : </span><span class="bold" style="font-size: small;"><%=advert.getTelephone()%></span></p>
                <p><span style="font-size: x-small;">Fiyat : </span><span class="bold" style="font-size: small;"><%=advert.getPrice()%>₺</span></p>
            </div>

    <%
        }
    %>
</div>





<!-- MEKAN İÇERİK -->




<div class="col-md" id="showCase" >

    <%
        if(advert!=null){
    %>

                <!-- MEKAN AÇIKLAMA -->


                <div class="row" style="margin-bottom: 5px;">
                    <div class="col">
                        <div class="card">
                            <div class="card-header" style="text-align: left; color: #7CB342">
                                İşletme Detayları
                            </div>
                            <div class="card-body" style="padding: 5px;">
                                <p>
                                    &ensp;<%=advert.getExplanation()%>
                                </p>
                            </div>
                        </div>
                    </div>
                </div>


                <!-- İMAGE GALERY -->



                <div class="card" style="margin-top: 5px; margin-bottom: 5px;">
                    <div class="card-header" style="text-align: left; color: #7CB342">
                        Fotoğraflara Gözatın
                    </div>
                    <div class="card-body" style="padding: 2px;">
                        <div class="row" style="padding: 5px; background-color: #E0E0E0; margin:5px; border-radius: 5px;">
                            <%
                                List<Integer> imageIdList = (List<Integer>)application.getAttribute("imageIdList");
                                for (int i = 1; i<=imageIdList.size() ; i++){

                            %>
                                    <div class="col-lg-3 col-md-6 col-sm-4 col-xs-6 col-6 cursor" style="padding: 5px;">
                                        <img class="firstİmages hover-shadow" src="<s:url value="image.action"/>?imageId=<%=imageIdList.get(i-1)%>" onclick="openModal();currentSlide(<%=i%>)">
                                    </div>
                            <%
                                }
                            %>
                        </div>
                    </div>
                </div>

                <div id="myModal2" class="galeryModal" style="padding-top: 50px;">
                    <span class="galeryClose cursor" onclick="closeModal()">&times;</span>
                    <div class="modal-content-galery">
                        <%
                            for (int i = 1; i<=imageIdList.size() ; i++){
                        %>
                                <div class="galerySlides" style="width: 100%; height: 100%;">
                                    <div class="numbertext"><%=i%> / <%=imageIdList.size()%></div>
                                    <img src="<s:url value="image.action"/>?imageId=<%=imageIdList.get(i-1)%>" style="width:100%; max-height: 700px;">
                                </div>
                        <%
                            }
                        %>

                        <a class="prev" style="top: 25%;" onclick="plusSlides(-1)">&#10094;</a>
                        <a class="next" style="top: 25%;" onclick="plusSlides(1)">&#10095;</a>

                        <div class="caption-container-galery">
                            <p id="caption"></p>
                        </div>
                        <div class="row">
                            <%
                                for (int i = 1; i<=imageIdList.size() ; i++){

                            %>
                                    <div class="col" style="padding-right: 5px; padding-left: 5px;">
                                        <img class="demo firstİmages" src="<s:url value="image.action"/>?imageId=<%=imageIdList.get(i-1)%>" onclick="currentSlide(<%=i%>)" alt="Resim Açıklaması">
                                    </div>
                            <%
                                }
                            %>
                        </div>

                    </div>
                </div>



                <!-- GOOGLE MAPS -->

                <div class="row">
                    <div class="col">
                        <div class="card">
                            <div class="card-header" style="text-align: left; color: #7CB342">
                                Sokak sokak gezip kalacağınız yer hakkında detaylı bilgi sahibi olabilirsiniz.
                            </div>
                            <div class="card-body" style="padding: 5px;">
                                <div id="googleMap4" style="width: 100%; height: 400px; border-radius: 10px;"></div>
                            </div>
                        </div>
                    </div>
                </div>

                <!-- YORUMLAR -->


                <div class="card" style="margin-top: 5px;">
                    <div class="card-header" style="text-align: left; color: #7CB342">
                        Yorumlar
                    </div>
                    <%
                        List<Comment> commentList = (List<Comment>) application.getAttribute("commentList");
                        if (commentList.isEmpty()){
                    %>
                            <div class="alert alert-success" style="margin: 5px;">
                                <span>Bu mekan hakkında bir yorum bulunmuyor.</span>
                            </div>
                    <%
                        }else {
                            for (Comment comment: commentList) {
                    %>
                                <div class="card-body" style="padding: 5px;">
                                    <div class="yorum">
                                        <p class="yorumYapan"><%=comment.getSenderName()%><span style="float: right;"><%=comment.getDate()%></span></p>
                                        <p style="font-size: small;"><%=comment.getCommentText()%></p>
                                    </div>
                                </div>
                    <%
                            }
                        }
                    %>
                    <div class="card-body" style="padding: 5px;">
                        <div class="card">
                            <h4 class="card-header" style="color: #7CB342;">Yorum Yap</h4>
                            <div class="card-body" style="padding: 0px;">
                                <form class="was-validated" action="postComment" method="post">
                                    <div class="form-group" style="margin: 5px;">
                                    <p style="font-weight: bold; font-size: x-small; margin-bottom: 5px;">Yorumlarınız küfür, hakaret vb. spam içerikli mesajlara karşı kısa bir süre içinde onaylandıktan sonra yayınlanacaktır.</p>
                                    <input name="senderName" type="text" class="form-control is-invalid" id="yotumad" placeholder="Adınız ve Soyadınız" style="font-size: x-small;" maxlength="50" required>
                                    <textarea name="commentText" class="form-control is-invalid" id="message1" rows="4" maxlength="400" style="font-size: x-small;" placeholder="Bu alana incelediğiniz yurt ile ilgili bilgileri girebilirsiniz. Yorumlarınız onaylandıktan sonra görünür hale gelecektir. Bunu spam yorumların önüne geçebilmek için yapıyoruz." required></textarea>
                                    <input name="advertId" type="hidden" value="<%=advert.getId()%>"/>
                                    <button id="yorumSendButton" type="submit" style="cursor: pointer; margin-top: 5px;" class="btn btn-primary">Gönder</button>
                                    <select name="commentStar" style="margin-top: 5px; float: right; width: 50px;" >
                                        <option>1</option>
                                        <option>2</option>
                                        <option>3</option>
                                        <option>4</option>
                                        <option>5</option>
                                    </select>
                                    <img width="20" height="20" style="float: right; margin-top: 5px;" src="images/starIcon.png">
                            </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
    <%
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
<!-- API KEY -->
<script async defer src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDBsKyuqNrPNQnsK0h32BFV9goszP1ELho&callback=initMap"
        type="text/javascript"></script>
<script>
    //GOOGLE MAPS SETTİNGS

    function initMap() {
        var myLatLng = {lat: <%=advert.getCoordinateLatitude()%>, lng: <%=advert.getCoordinateLongitude()%>};

        var map = new google.maps.Map(document.getElementById('googleMap4'), {
            zoom: 17,
            center: myLatLng
        });

        var marker = new google.maps.Marker({
            position: myLatLng,
            map: map,
            label: '<%=advert.getName()%>'
        });
    }


    //IMAGE GALERY
    function openModal() {
        document.getElementById('myModal2').style.display = "block";
    }

    function closeModal() {
        document.getElementById('myModal2').style.display = "none";
    }

    var slideIndex = 1;
    showSlides(slideIndex);

    function plusSlides(n) {
        showSlides(slideIndex += n);
    }

    function currentSlide(n) {
        showSlides(slideIndex = n);
    }

    function showSlides(n) {
        var i;
        var slides = document.getElementsByClassName("galerySlides");
        var dots = document.getElementsByClassName("demo");
        var captionText = document.getElementById("caption");
        if (n > slides.length) {slideIndex = 1}
        if (n < 1) {slideIndex = slides.length}
        for (i = 0; i < slides.length; i++) {
            slides[i].style.display = "none";
        }
        for (i = 0; i < dots.length; i++) {
            dots[i].className = dots[i].className.replace(" active", "");
        }
        slides[slideIndex-1].style.display = "block";
        dots[slideIndex-1].className += " active";
        captionText.innerHTML = dots[slideIndex-1].alt;
    }
    //------------------------------
    var span = document.getElementById("alertExit");
    var modal1 = document.getElementById('myModal1');


    //LOGİN KONTROL
    var loginButton = document.getElementById("loginButton");

    loginButton.onclick = function(){
        var username = document.getElementById("userName").value;
        var password = document.getElementById("password").value;

        if((username.length>0 && username.length<5) || (password.length>0 && password.length<5) ){
            document.getElementById("errorText").innerHTML = "Kullanıcı adı veya şifre hatalı. Lütfen tekrar deneyin.";
            modal1.style.display = "block";
            return false;
        }
    }


    //ŞİFRE KONTROL
    var newUserButton = document.getElementById("newUserButton");

    newUserButton.onclick = function(){
        var newPassword = document.getElementById("newPassword").value;
        var newPasswordAgain = document.getElementById("newPasswordAgain").value;
        var newUsername = document.getElementById("newUsername").value;

        if(newUsername.length>0 && newUsername.length<5){
            document.getElementById("errorText").innerHTML = "Kullanıcı adınız beş karakterden uzun olmalıdır.";
            modal1.style.display = "block";
            return false;
        }
        else if(newPassword.length>0 && newPassword.length<5){
            document.getElementById("errorText").innerHTML = "Şifreniz beş karakterden uzun olmalıdır.";
            modal1.style.display = "block";
            return false;
        }
        else if((newPassword!=newPasswordAgain) && newPasswordAgain.length>0){
            document.getElementById("errorText").innerHTML = "Şifreleriniz uyuşmuyor lütfen tekrar deneyin.";
            modal1.style.display = "block";
            return false;
        }

    }

    span.onclick = function() {
        modal1.style.display = "none";
    }


    //EMAİL KONTROL
    var messageSendButton = document.getElementById('messageSendButton');
    var eMail = document.getElementById('eMail');
    messageSendButton.onclick = function(){
        var mailAdress = eMail.value;
        var adresUzunlugu = mailAdress.length;
        var mailDogrulama1 = mailAdress.indexOf("@");
        var mailDogrulama2 = mailAdress.indexOf(".com");
        if((mailAdress.length>0 && mailAdress.length<5) || ((mailDogrulama1 <= 0) && (mailAdress.length>0)) || (mailDogrulama2 <= 0) && (mailAdress.length>0) || (mailAdress.charAt(adresUzunlugu-1) == ".")){
            document.getElementById("errorText").innerHTML = "Mesajınıza geri dönüş yapabilmemiz için doğru bir mail adresi girmesliniz.<br>Mail adresiniz yanlış lütfen tekrar deneyin.";
            modal1.style.display = "block";
            return false;
        }
        else{
            console.log("dogru adres");
        }
    }
    span.onclick = function() {
        modal1.style.display = "none";
    }

    // Get the modal
    var modal = document.getElementById('id01');

    // When the user clicks anywhere outside of the modal, close it
    window.onclick = function(event) {
        if (event.target == modal) {
            modal.style.display = "none";
        }
        if (event.target == modal1) {
            modal1.style.display = "none";
        }
    }
    /* Set the width of the side navigation to 250px and the left margin of the page content to 250px and add a black background color to body */
    function openNav() {
        document.getElementById("mySidenav").style.width = "250px";
    }

    /* Set the width of the side navigation to 0 and the left margin of the page content to 0, and the background color of body to white */
    function closeNav() {
        document.getElementById("mySidenav").style.width = "0";
    }
    function openNav1() {
        document.getElementById("mySidenav1").style.width = "250px";
    }
    function closeNav1() {
        document.getElementById("mySidenav1").style.width = "0";
    }
    function openNav2() {
        document.getElementById("mySidenav2").style.width = "250px";
    }
    function closeNav2() {
        document.getElementById("mySidenav2").style.width = "0";
    }
</script>
</body>
</html>