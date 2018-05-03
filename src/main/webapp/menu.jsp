<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<!-- MENU -->
<nav class="navbar navbar-expand-lg navbar-light bg-light header">
    <a class="navbar-brand" href="#">yurtbul.com <p></p></a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item menuItem">
                <a class="nav-link menuItem" href="index.jsp" >Ana Sayfa <span class="sr-only">(current)</span></a>
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
        <form class="form-inline my-2 my-lg-0" action="search">
            <input name="searchText" class="form-control mr-sm-2" type="search" placeholder="Mekan, Şehir veya Semt" aria-label="Search">
            <button class="btn btn-outline-success my-2 my-sm-0" id="searchButton" type="submit">Ara</button>
        </form>
    </div>
</nav>
