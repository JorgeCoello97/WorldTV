<%@page language="java" import="sesion2.*" contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>CARRITO</title>
        <link href="css/estilo_carrito.css" rel="stylesheet" type="text/css"/>
        <script src="js/carrito.js" type="text/javascript"></script>
    </head>
    <%
        String mensaje = (String)session.getAttribute("mensaje");
        if (mensaje != null) {
            session.removeAttribute("mensaje");
    %>
    <body>  
        <img src="img/cerrar.png" style="display:none;" onload="alert('<%=mensaje%>')">
  
    <%
    } 
    else 
    {
    %>
    <body>    
    <%
    }    
        if(mensaje == "No hay productos en el carrito.")
        {
    %>
            <div class="contenido_carrito">
                <div class="principal_carrito">
                    <h2 class="titulo_carrito">SIN PRODUCTOS EN EL CARRITO</h2>
                    <hr class="separador_carrito">
                    <div class="productos_carrito">
                        <!--AQUÍ SE AÑADIRÁN LOS ITEMS DEL CARRITO-->
                    </div>
                </div>
                <div class="resumen_carrito">
                    <h2 class="titulo_carrito">Resumen Compra</h2>
                    <hr class="separador_carrito">
                    <p class="total_carrito">Total: <span id="precio_total_carrito">0€</span></p>
                    <button id="comprar" class="btn_comprar_carrito" value="Comprar" onclick="ProcesarCarrito(carrito,'ProcesarPedido','cuerpo')">
                        COMPRAR
                    </button>
                </div>
                <div class="div_cerrar_sesion">
                    <img class="img_logout" src="img/logout.png" alt="Cerrar Sesión" onclick="Cargar('CerrarSesion','cuerpo');return false"/>
                </div>
            </div>
    <%
        }
        else 
        { 
    %>
            <div class="contenido_carrito">
                <div class="principal_carrito">
                    <h2 class="titulo_carrito">Productos añadidos al carritos</h2>
                    <hr class="separador_carrito">
                    <div class="productos_carrito">
                        <!--AQUÍ SE AÑADIRÁN LOS ITEMS DEL CARRITO-->
                    </div>
                </div>
                <div class="div_resumen_logout">
                    <div class="resumen_carrito">
                        <h2 class="titulo_carrito">Resumen Compra</h2>
                        <hr class="separador_carrito">
                        <p class="total_carrito">Total: <span id="precio_total_carrito">0€</span></p>
                        <button id="comprar" class="btn_comprar_carrito" value="Comprar" onclick="ProcesarCarrito(carrito,'ProcesarPedido','cuerpo')">
                            COMPRAR
                        </button>
                    </div>
                    <div class="div_cerrar_sesion">
                        <img class="img_logout" src="img/logout.png" alt="Cerrar Sesión" onclick="Cargar('CerrarSesion','cuerpo');return false"/>
                    </div>
                </div>
            </div>
    <%
        }    
    %>
    </body>
</html>
