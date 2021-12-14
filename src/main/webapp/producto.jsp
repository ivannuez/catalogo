<%-- 
    Document   : index
    Created on : 27/11/2021, 11:30:18 AM
    Author     : Usuario
--%>

<%@page import="com.py.catalogo.entity.Acorde"%>
<%@page import="com.py.catalogo.entity.Producto"%>
<%@page import="com.py.catalogo.facade.Facade"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%
            Producto prod = null;
            if (request.getParameter("id") == null) {
                response.sendRedirect("/catalogo");
            } else {
                String id = request.getParameter("id");
                prod = Facade.productoWithAcorde(Integer.parseInt(id));
                if (prod == null) {
                    response.sendRedirect("/catalogo");
                }
            }%>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>My Perfume</title>
        <meta name="theme-color" content="#FCBBCF" />
        <link rel="icon" href="resource/icons/favicon.ico">
        <link href="resource/css/animate.min.css" rel="stylesheet" type="text/css"/>
        <link href="resource/css/main.css" rel="stylesheet" type="text/css"/>
        <link  href="resource/css/all.min.css" rel="stylesheet">
        <script src="resource/js/jquery-3.6.0.min.js" type="text/javascript"></script>
    </head>
    <body>
        <div class="container-fluid">
            <div class="header header-product">
                <img  src="resource/img/left_logo.png" alt="green apple slice">
                <div class="buscador">
                    <div class="buscador-content">
                        <img class="bt-title" src="resource/img/title_logo.png" alt="green apple slice">
                    </div>
                </div>
                <img src="resource/img/right_logo.png" alt="green apple slice">
            </div>
            <div class="container" style="margin-top: 1rem">
                <div class="product"> 
                    <div class="product-img">
                        <img src="<%= prod.getImagen() %>" alt="green apple slice">
                    </div>
                    <div class="product-title item-title"><%= prod.getNombre()%></div>
                    <spam class="product-code item-code">COD: <%= prod.getIdProducto()%></spam>
                    <div class="product-detail">
                        <div class="descripcion">
                            <div><%= prod.getDescripcion()%></div>
                        </div>
                        <div class="product-acordes">
                            <div class="product-acordes-title">Acordes:</div>
                            <div class="product-acordes-items">
                                <%for (Acorde aco : prod.getAcordes()) {%>
                                <div><%= aco.getNombre()%></div>
                                <% }%>
                            </div>

                        </div>
                        <div class="item-footer">
                            <div class="item-price"> 
                                Gs. <span><%= prod.getPrecio()%></span>    
                            </div>
                        </div>
                    </div>
                    <!--                    <div class="product-ficha">
                                            <div class="product-ficha-option">
                                                <div id="product-ficha-option-des" class="product-ficha-option-item product-ficha-option-sel">
                                                    Descripción
                                                </div>
                                                <div id="product-ficha-option-tec" class="product-ficha-option-item">
                                                    Caracteristicas
                                                </div>
                                            </div>
                                        </div>
                                        <div class="product-ficha-des product-ficha-item">
                                            Pantalla: 6.7", 1284 x 2778 pixels
                    
                                            Procesador: Apple A14 Bionic
                    
                                            Almacenamiento: 256GB
                    
                                            Expansión: sin microSD
                    
                                            Cámara: Cuádruple, 12MP+12MP +12MP+LiDAR
                    
                                            OS: iOS 14
                    
                                            Perfil: 7.4 mm
                    
                                            Peso: 228 g
                                        </div>
                                        <div class="product-ficha-tec product-ficha-item hidden">
                                            Ficha técnica
                                            Producto
                                            Smartphone
                                            Vendedor
                                            Alemania Cell
                                            Marca
                                            Apple
                                        </div>-->
                </div>
            </div>
            <div class="footer"></div>
        </div>
        <script src="resource/js/main.js" type="text/javascript"></script>
    </body>
</html>

