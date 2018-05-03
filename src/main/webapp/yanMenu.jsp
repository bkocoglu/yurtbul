<%@ page contentType="text/html;charset=UTF-8" language="java" %>

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
                            <div class="col">
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

                </div>