<%@page contentType="text/html" import="sesion2.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="css/estilo_pedidoRealizado.css" rel="stylesheet" type="text/css"/>
        <title>PEDIDO REALIZADO</title>
        <script>
            function borrarCarrito(){
                if(localStorage.getItem("carrito")) {
                    localStorage.removeItem("carrito");
                    carrito = new Object();
                }
            }
        </script>
    </head>
    <body>
        <img src="img/cerrar.png" style="display:none;" onload="borrarCarrito()">
        <div class="container">
            <div class="pedido_realizado">
        <%
            AccesoBD accesoBD = AccesoBD.getInstance();
            ResponseBD responseBD = accesoBD.devolverCodigoUltimoPedido();
            if(responseBD.isSuccess()){
                int codigo_pedido = (Integer) responseBD.getValor();
        %>
                <h3>¡ENHORABUENA!</h3>
                <h3>SU PEDIDO CON IDENTIFICADOR <%=codigo_pedido%> HA SIDO REALIZADO CON ÉXITO</h3>
        <%
                String contrarembolso = (String)session.getAttribute("contrarembolso");
                if(contrarembolso != null){
                    session.removeAttribute("contrarembolso");
        %>
                <h5><%=contrarembolso%></h5>
        <%
                }
        %>        
                <p><img class="imagen_tick" src="img/tick.png" alt="Pedido realizado con éxito"/></p>
                <p class="botones">
                    <button class="boton" onclick="window.location.assign('http://localhost:8080/WORLD_TV/index.jsp')">Ir al inicio</button>
                </p>
        <%    
            }else{
        %>
                <h3>No se ha realizado su pedido.</h3>
                <p class="botones">
                    <button class="boton" onclick="window.location.assign('http://localhost:8080/WORLD_TV/index.jsp')">Ir al inicio</button>
                </p>
        <%        
            }
        %>
            </div>
        </div>
    </body>
</html>