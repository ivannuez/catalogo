<%-- 
    Document   : index
    Created on : 27/11/2021, 11:30:18 AM
    Author     : Usuario
--%>

<%@page import="com.py.catalogo.entity.Acorde"%>
<%@page import="com.py.catalogo.facade.Facade"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>My Perfume</title>
        <meta name="theme-color" content="#FCBBCF" />
        <link rel="icon" href="resource/icons/favicon.ico">
        <link href="resource/css/animate.min.css" rel="stylesheet" type="text/css"/>
        <link  href="resource/css/all.min.css" rel="stylesheet">
        <link  href="resource/css/awesomplete.css" rel="stylesheet">
        <link href="resource/css/main.css" rel="stylesheet" type="text/css"/>
        <script src="resource/js/jquery-3.6.0.min.js" type="text/javascript"></script>
        <script src="resource/js/awesomplete.js" type="text/javascript"></script>
    </head>
    <body>
        <div class="container-fluid">
            <div class="header">
                <img src="resource/img/left_logo.png" alt="green apple slice">
                <div class="buscador">
                    <div class="buscador-content">
                        <img class="bt-title" src="resource/img/title_logo.png" alt="green apple slice">
                        <div class="form-search">
                            <div class="form-group">
                                <input id="textFilter" class="form-field" type="text" name="s" value="" placeholder="¿Qué estás buscando?" aria-label="Buscar" autocomplete="off">
                                <button id="bt-filter-text" class="form-button"><i class="fas fa-search"></i> <span>Buscar</span></button>
                            </div>
                            <div class="form-option-bt">
                                <button id="bt-filter" class="form-text-button"><i class="fas fa-filter"></i> <span>Filtrar por Acordes</span></button>
                                <button id="bt-clean-filter" class="form-text-button hidden"><i class="fas fa-eraser"></i> <span>Limpiar Filtro</span></button>
                            </div>
                        </div>
                    </div>
                    <div id="filter-popap" class="card">
                        <p>Acordes</p>
                        <hr></hr>
                        <div id="acordes-items">
                            <%
                                List<Acorde> acordes = Facade.acordes();
                                if (acordes.size() > 0) {
                                    for (Acorde ac : acordes) {%>
                            <div><input id="<%= ac.getIdAcorde()%>" class="chk-acordes" type="checkbox" /> <%= ac.getNombre()%></div>
                                <%}
                                    }
                                %>
                        </div>
                        <button id="bt-filter-content" class="form-text-button"><i class="fas fa-filter"></i> <span>Filtrar</span></button>
                    </div>
                </div>
                <img src="resource/img/right_logo.png" alt="green apple slice">
            </div>
            <div id="idGallery" class="content gallery ">
            </div>
            <div class="footer"></div>
        </div>
        <script src="resource/js/main.js" type="text/javascript"></script>
    </body>
</html>
