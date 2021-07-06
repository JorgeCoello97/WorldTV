<%@page import="sesion2.*" contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>USUARIO</title>
        <link href="css/estilo_usuario_perfil.css" rel="stylesheet" type="text/css"/>
        <script src="js/usuario.js" type="text/javascript"></script>
    </head>
    <%
        String mensaje = (String)session.getAttribute("mensaje");
        if (mensaje != null) { //Eliminamos el mensaje consumido
            session.removeAttribute("mensaje");
    %>
    <body>  
        <img src="img/cerrar.png" style="display:none;" onload="alert('<%=mensaje%>')">
  
        <%
           
        } else {%>
    <body>    
        <%
        }
        %>
        <div class="contenido_perfil">
            <div class="menu_y_logout">
                    <div class="menu_perfil">
                        <table class="opciones_perfil">
                            <tr>
                                <td id="opcion_perfil" class="opcion_seleccionada" onclick="Cargar('html/usuario_perfil.jsp','cuerpo')">
                                    PERFIL
                                </td>
                            </tr>
                            <tr>
                                <td  id="opcion_historial" onclick="Cargar('html/usuario_historial.jsp','cuerpo');">
                                    HISTORIAL
                                </td>
                            </tr>
                        </table>
                    </div>
                    <img class="img_logout" src="img/logout.png" alt="Cerrar Sesión" onclick="Cargar('CerrarSesion','cuerpo');return false"/>
            </div>
            <div class="principal_perfil" id="principal_perfil">
        <%
        AccesoBD accesoBD = AccesoBD.getInstance();
        String usuarioActual = (String)session.getAttribute("usuario");
        ResponseBD responseBD = accesoBD.obtenerDatosUsuario(usuarioActual);
        if (responseBD.isSuccess()) {
                UsuarioBD usuarioBD = (UsuarioBD) responseBD.getValor();
        %>
                <div class="wrapper_perfil">
                        <div class="cuenta_perfil">
                            <h3 class="titulo_cuenta_perfil">Datos de la cuenta</h3>
                            <%
                                if( mensaje == "Las contraseñas deben coincidir." )
                                {
                            %>
                            <form method="post" onsubmit="ProcesarForm(this,'ModificarCuenta','cuerpo');return false" >
                                <table class="tabla_cuenta">
                                    <tr class="fila_vacia"><td></td><td></td><td></td><td></td><td></td><td></td></tr>
                                    <tr>
                                        <td>Usuario:</td> 
                                        <td colspan="5">
                                            <input class="input_perfil" type="text" name="usuario" id="usuario" value="<%=usuarioBD.getUsuario()%>" disabled/>
                                        </td>
                                    </tr>
                                    <tr>
                                     <tr>
                                        <td>Correo:</td> 
                                        <td colspan="5">
                                            <input class="input_perfil" type="text" name="correo" id="correo" pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,4}$" value="<%=usuarioBD.getCorreo()%>" disabled required/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>Nueva Contraseña:</td> 
                                        <td colspan="5">
                                            <input class="input_perfil" style="border: 2px solid red;" type="password" name="nueva_clave" id="nueva_clave" value="" required/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>Confirmar Contraseña:</td> 
                                        <td colspan="5">
                                            <input class="input_perfil" style="border: 2px solid red;" type="password" name="conf_nueva_clave" id="conf_nueva_clave" value="" required/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td colspan="6">
                                            <input type="submit" class="btn_modificar" style="background-color: green;" value="CONFIRMAR MODIFICACIÓN" />
                                        </td>
                                    </tr>
                                </table>
                            </form>
                                <%
                                }
                                else
                                {
                                %>
                            <form method="post" onsubmit="ProcesarForm(this,'ModificarCuenta','cuerpo');return false" >
                                <table class="tabla_cuenta">
                                    <tr class="fila_vacia"><td></td><td></td><td></td><td></td><td></td><td></td></tr>
                                    <tr>
                                        <td>Usuario:</td> 
                                        <td colspan="5">
                                            <input class="input_perfil" type="text" name="usuario" id="usuario" value="<%=usuarioBD.getUsuario()%>" disabled/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>Correo:</td> 
                                        <td colspan="5">
                                            <input class="input_perfil" type="text" name="correo" pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,4}$" id="correo" value="<%=usuarioBD.getCorreo()%>" disabled required/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>Nueva Contraseña:</td> 
                                        <td colspan="5">
                                            <input class="input_perfil" type="password" name="nueva_clave" id="nueva_clave"  disabled required/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>Confirmar Contraseña:</td> 
                                        <td colspan="5">
                                            <input class="input_perfil" type="password" name="conf_nueva_clave" id="conf_nueva_clave"  disabled required/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td colspan="6">
                                            <input type="button" id="bttn_cuenta" class="btn_modificar" value="MODIFICAR" onclick="modificarDatosCuenta()"/>
                                        </td>
                                    </tr>
                                </table>
                            </form>
                                <%}
                                %>       
                        </div>
                    
                        <div class="personales_perfil">
                            <h3 class="titulo_personal_perfil">Datos personales</h3>
                            <form method="post" onsubmit="ProcesarForm(this,'ModificarDatosPersonales','cuerpo');return false" >
                                <table class="tabla_personal">
                                    <tr class="fila_vacia"><td></td><td></td><td></td><td></td><td></td><td></td></tr>
                                    <tr>
                                        <td>Nombre:</td> 
                                        <td colspan="2">
                                            <input class="input_perfil" type="text" name="nombre" pattern="[a-zA-Z]+" value="<%=usuarioBD.getNombre()%>" disabled/>
                                        </td>
                                        <td>Apellidos:</td> 
                                        <td colspan="2">
                                            <input class="input_perfil" type="text" name="apellidos" pattern="[a-zA-Z ]{2,35}" value="<%=usuarioBD.getApellidos()%>" disabled/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>Domicilio:</td> 
                                        <td colspan="5">
                                            <input class="input_perfil" type="text" name="domicilio" value="<%=usuarioBD.getDomicilio()%>" disabled/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>Población:</td> 
                                        <td colspan="2">
                                            <input class="input_perfil" type="text" name="poblacion" pattern="[a-zA-Z]+" value="<%=usuarioBD.getPoblacion()%>" disabled/>
                                        </td>
                                        <td>Provincia:</td> 
                                        <td colspan="2">
                                            <input class="input_perfil" type="text" name="provincia" pattern="[a-zA-Z]+" value="<%=usuarioBD.getProvincia()%>" disabled/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>Código Postal:</td> 
                                        <td colspan="2">
                                            <input class="input_perfil" type="text" name="cp" pattern="[0-9][0-9][0-9][0-9][0-9]" value="<%=usuarioBD.getCod_postal()%>" disabled/>
                                        </td>
                                        <td>Teléfono:</td> 
                                        <td colspan="2">
                                            <input class="input_perfil" type="tel" name="telefono" pattern="[0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9]" value="<%=usuarioBD.getTelefono()%>" disabled/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td colspan="6">
                                            <input type="button" id="bttn_personal" class="btn_modificar" value="MODIFICAR" onclick="modificarDatosPersonales(this)"/>
                                        </td>
                                    </tr>
                                </table>
                            </form>
                        </div>
                </div>
        <%
        } else {
        %>
            <img src="img/cerrar.png" style="display:none;" onload="alert('<%=(String)responseBD.getValor()%>')">
        <%
        }
        %> 
            </div>
        </div>
    </body>
</html>