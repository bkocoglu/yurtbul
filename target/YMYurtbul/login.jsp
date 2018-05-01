<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.HashSet" %>
<!-- LOGİN -->
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>


<%
    String loginStatus = (String) application.getAttribute("login");
    if(session.getAttribute("login")==null){

%>

<div class="col-md-3" id="login">
    <a name="loginIsinla">
        <div class="row">
            <div class="col-lg-1"></div>
            <div class="col-lg" style="background-color: #F5F5F5; border-radius: 10px;">
                <div id="uyeGirisKutusu">
                    <form class="was-validated" action="login" method="post">
                        <div class="form-group">
                            <s:textfield name="loginName" id="userName" cssClass="form-control is-invalid loginInputs"
                                         placeholder="Kullanıcı Adı veya e-Mail adresi" maxlength="50"/>
                            <s:password name="loginPassword" id="password" cssClass="form-control is-invalid loginInputs"
                                        rows="4" maxlength="20" placeholder="Şifre"/>
                            <p style="width: 100%; font-weight: bold; font-size: x-small;">İlan verebilmek için için giriş yapmalısınız.</p>
                            <s:submit id="loginButton" cssStyle="cursor: pointer; float: right; margin-bottom: 10px; margin-top: 20px;" cssClass="btn btn-outline-primary" value="Giriş Yap"/>
                        </div>
                    </form>
                    <!-- Button to open the modal -->
                    <button class="btn btn-outline-success" onclick="document.getElementById('id01').style.display='block'" style="margin-top: 5px; margin-bottom: 10px;">Kayıt Ol</button>
                </div>
                <div id="uyeBilgileri">
                    <p>Kullanıcı Adı <span style="font-weight: bold;">bilalKocoglu</span></p>
                    <a style="float: right; margin-top: 15px; margin-bottom: 15px;" class="btn btn-danger" href="#" role="button">Çıkış Yap</a>
                    <a style="float: left; margin-top: 15px; margin-bottom: 15px;" class="btn btn-warning" href="#" role="button">Favorilerim</a>
                </div>


                <!-- The Modal (contains the Sign Up form) -->
                <div id="id01" class="modal">
                    <span onclick="document.getElementById('id01').style.display='none'" class="close" title="Close Modal">&times;</span>
                    <form class="modal-content animate" action="createUser" >
                        <div class="container">
                            <s:textfield id="newAd" cssClass="newUserTexts" name="newName" maxLength="50" required="true" label="Ad " placeholder="Ad " labelposition="top"/>

                            <s:textfield id="newSoyad" cssClass="newUserTexts" name="newLastName" maxLength="50" required="true" label="Soyad " placeholder="Soyad" labelposition="top"/>

                            <s:textfield id="newUsername" cssClass="newUserTexts" name="newEmail" maxLength="60" required="true" label="Email veya Kullanıcı Adı" placeholder="eMail veya Kullanıcı Adı" labelposition="top"/>

                            <s:password id="newPassword" cssClass="newUserTexts" name="newPass" maxLength="20" required="true" label="Şifre" placeholder="Şifre" labelposition="top"/>

                            <s:password id="newPasswordAgain" cssClass="newUserTexts" name="newPassAgain" maxLength="20" required="true" label="Şifre Tekrar" placeholder="Şifre Tekrar" labelposition="top"/>

                            <s:submit id="newUserButton" cssClass="btn btn-success" value="Kayıt Ol"/>
                        </div>
                    </form>

                </div>
            </div>
            <div class="col-lg-1"></div>
        </div>
        <div class="row">
            <div class="col-lg-1"></div>
            <div class="col-lg">
                <div id="accordion" role="tablist">
                    <div class="card" style="margin-top: 15px;">
                        <%
                            List<Advert> adverts = (List<Advert>)application.getAttribute("showCaseList");
                            if (adverts.isEmpty() || adverts==null){


                        %>

                        <%
                            }else {
                        %>


                                <div class="card-header" role="tab" id="headingOne">
                                    <h5 class="mb-0">
                                        <a data-toggle="collapse" href="#collapseOne" aria-expanded="true" aria-controls="collapseOne" style="text-decoration: none">
                                            Şehirler
                                        </a>
                                    </h5>
                                </div>

                                <div id="collapseOne" class="collapse show" role="tabpanel" aria-labelledby="headingOne" data-parent="#accordion">
                                    <div class="card-body">
                                        <%
                                            HashSet<String> hashSet = new HashSet();
                                            for (Advert advert: adverts) {
                                                hashSet.add(advert.getCity().getCityName());
                                            }
                                            for (String city :hashSet){

                                        %>
                                                <p class="sehirler" style="margin-top: 5px;">
                                                    <%=city%>
                                                </p>

                                        <%
                                            }
                                        %>
                                    </div>
                                </div>
                        <%
                            }
                        %>

                    </div>
                </div>
            </div>
            <div class="col-lg-1"></div>
        </div>
</div>

<%
    }
    if (session.getAttribute("login")!=null){

%>
<div class="col-md-3" id="login">
    <a name="loginIsinla">
        <div class="row">
            <div class="col-lg-1"></div>
            <div class="col-lg" style="background-color: #F5F5F5; border-radius: 10px;">
                <div id="uyeBilgileri2">
                    <p style="margin-top: 5px;">Admin <span style="font-weight: bold;"><%=session.getAttribute("login")%></span></p>
                    <a style="float: right; margin-top: 15px; margin-bottom: 15px; width: 100%;" class="btn btn-danger" href="#" role="button">Çıkış Yap</a>
                </div>

            </div>
            <div class="col-lg-1"></div>
        </div>
</div>

<%
    }
%>
</div>
</div>

