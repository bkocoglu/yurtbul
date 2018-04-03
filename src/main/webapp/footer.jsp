<%--
  Created by IntelliJ IDEA.
  User: pC2
  Date: 26.03.2018
  Time: 03:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<footer>

    <div class="row" id="bilgilendirme">
        <div class="col-sm" >
            <p style="margin-left: 2px; margin-right: 2px;">Yerleşeceğiniz şehirde zahmet çekmeden yeni yuvanızı bulun.</p>
        </div>
    </div>
    <div class="row">
        <div class="col-lg-3" style="padding: 10px;">
            <div class="footerAciklama">
                <p>Türkiye'nin dört bir yanında üniversite şehirlerindeki yurtları ve apartları sizler için derledik. Tek tıkla zahmetsiz bir şekilde konaklayacağınız yerlerin çevrsini google map aracılığıyla canlı inceleyin. Şikayet ve önerilerinizi bize bildirin. Memnuniyetinizi üst düzeyde tutmak için destek ekibimiz sizlerle irtibat halinde olacaktır.</p>
            </div>
        </div>
        <div class="col-lg-6" style="background-color: white">
            <div class="alert alert-success" role="alert" style="margin-top: 10px;margin-bottom: 10px;">
                Tavsiye ve İsteklerinizi Bize İletin...
            </div>
            <form class="was-validated" action="createProposal" method="post">
                <div class="form-group" style="margin-bottom: 10px;">

                    <s:textfield name="email" cssClass="form-control is-invalid" id="eMail" placeholder="E-posta Adresi" maxlength="50"/>

                    <s:textarea name="proposalText" cssClass="form-control is-invalid" id="message" rows="4" maxlength="400" placeholder="Mesajınız"/>
                    <small id="emailHelp" class="form-text text-muted">E-posta adresiniz asla kimseyle paylaşılmayacaktır.</small>
                    <label class="custom-control custom-checkbox">
                        <s:checkbox name="emailcheck" cssClass="custom-control-input is-valid"/>
                        <span class="custom-control-indicator"></span>
                        <span style="margin-top: 5px;" class="custom-control-description">Bu adrese yurtbul.com bilgilendirme mailleri almak istiyorum.<br></span>
                    </label>
                    <s:submit id="messageSendButton" cssStyle="cursor: pointer;" cssClass="btn btn-primary" value="Gönder"/>
                </div>
            </form>
        </div>
        <div class="col-lg-3">
            <p style="font-weight: bold; margin-top: 15px;">&#8226; Bize ulaşabileceğiniz hesap ve adresler;</p>
            <p style="vertical-align: middle; display: table;">
                <img width="40" height="40" align="left" src="images/gmailIcon.png"/><span style="display: table-cell; vertical-align: middle; font-size: small; font-weight: bold;">bilal.kocoglu@outlook.com.tr</span>
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
    <div class="row">
        <div class="col-md" style="text-align: center;">
            <span style="font-size: xx-small;">&copy; Bilal Koçoğlu 2017</span>
        </div>
    </div>
</footer>






