<%@page language="java" import="sesion2.*" contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>LOGIN</title>
        <link href="css/estilo_carrito_login.css" rel="stylesheet" type="text/css"/>
    </head>
    <%
        String mensaje = (String)session.getAttribute("mensaje");
        if (mensaje != null) 
        {
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
        %>
        <div class="contenido_carrito_login">
            <div class="principal_carrito_login">
                <h2 class="titulo_carrito_login">LOGIN</h2>
                <hr>

                <div class="imgcontainer_carrito_login">
                    <img src="img/login.png" alt="Avatar" class="avatar_carrito_login">
                </div>

                <div class="wrapper_carrito_login">
                    <form method="post" onsubmit="ProcesarForm(this,'LoginUsuario','cuerpo');return false" >
                        <label for="usuario"><b>Nombre de usuario</b></label>
                        <input class="input_carrito_login" type="text"  name="usuario" id="usuario" required>

                        <label for="clave"><b>Contraseña</b></label>
                        <input class="input_carrito_login" type="password" name="clave" id="clave" required>
                        <input type="text" name="pagina" value="carrito_login.jsp" hidden> <%--pagina actual --%>
                        <input type="submit" class="btn_iniciar_carrito_login" value="INICIAR SESIÓN" />
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>
