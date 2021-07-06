<%@page language="java" import="sesion2.*" contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>LOGIN</title>
        <link href="css/estilo_usuario_login.css" rel="stylesheet" type="text/css"/>
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
        <div class="contenido_login">
            <div class="principal_login" id="principal_login">
                <form method="post" onsubmit="ProcesarForm(this,'LoginUsuario','cuerpo');return false" >

                    <h2 class="titulo_login">LOGIN</h2>
                    <hr class="separador_login">

                    <div class="imgcontainer_login">
                        <img src="img/login.png" alt="Avatar" class="avatar_login">
                    </div>
                    <div class="wrapper_login">
                        <label for="usuario"><b>Nombre de usuario</b></label>
                        <input  class="input_login" type="text" name="usuario" id="usuario" required>

                        <label for="clave"><b>Contraseña</b></label>
                        <input  class="input_login" type="password" name="clave" id="clave" required>
                        
                        <input  type="submit" class="btn_iniciar_login" value="INICIAR SESIÓN"/>
                        <hr class="separador_botones_login">
                        <input type="text" name="pagina" value="usuario_login.jsp" hidden> <%--pagina actual --%>
                        <input type="button" class="btn_registrar_login" value="REGISTRARSE" onclick="Cargar('html/usuario_registrar.jsp','cuerpo')" />
                    </div>
                </form>
            </div>
        </div>
    </body>
</html>
