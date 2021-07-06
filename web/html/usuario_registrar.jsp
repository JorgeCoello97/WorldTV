<%@page language="java" import="sesion2.*" contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
    <head>
        <title>REGISTRAR</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href="css/estilo_usuario_registrar.css" rel="stylesheet" type="text/css"/>
    </head>
    <%
        String mensaje = (String)session.getAttribute("mensaje");
        if (mensaje != null) {
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
        <div class="contenido_registrar">
            <div class="principal_registrar" id="principal_registrar">
            <h2 class="titulo_registrar">NUEVA CUENTA</h2>
            <hr>
            <%
                if(mensaje == null)
                { //ANTES ESTABA EN EL IF ESTOS CASOS OR's --> (mensaje != "Las contraseñas deben coincidir.") || (mensaje != "Nombre de usuario no disponible.")
            %>
            <form method="post" onsubmit="ProcesarForm(this,'RegistroUsuario','cuerpo');return false" >
                <table class="tabla_registrar">
                    <tr class="fila_vacia"><td></td><td></td><td></td><td></td><td></td><td></td></tr>
                    <tr>
                        <td>Nombre:</td> 
                        <td colspan="5">
                            <input class="input_registrar" type="text" pattern="[a-zA-Z]+" name="nombre" required/>
                        </td>
                        <td>Apellidos:</td> 
                        <td colspan="5">
                            <input class="input_registrar" type="text" pattern="[a-zA-Z ]{2,35}" name="apellidos" required/>
                        </td>
                    </tr>
                    <tr>
                        <td>Usuario:</td> 
                        <td colspan="5">
                            <input class="input_registrar" type="text" name="usuario" required/>
                        </td>
                        <td>Correo:</td> 
                        <td colspan="5">
                            <input class="input_registrar" type="text" pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,4}$" name="correo" required/>
                        </td>
                    </tr>
                    <tr>
                        <td>Domicilio:</td> 
                        <td colspan="5">
                            <input class="input_registrar" type="text" name="domicilio" required/>
                        </td>
                        <td>Población:</td> 
                        <td colspan="5">
                            <input class="input_registrar" type="text" pattern="[a-zA-Z]+" name="poblacion" required/>
                        </td>
                    </tr>
                    <tr>
                        <td>Provincia:</td> 
                        <td colspan="5">
                            <input class="input_registrar" type="text" pattern="[a-zA-Z]+" name="provincia" required/>
                        </td>
                        <td>Código Postal:</td> 
                        <td colspan="5">
                            <input class="input_registrar" type="text" pattern="[0-9][0-9][0-9][0-9][0-9]" name="cp" required/>
                        </td>
                    </tr>
                    <tr>
                        <td>Telefono:</td> 
                        <td colspan="5">
                            <input class="input_registrar" type="text" pattern="[0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9]" name="telefono" required/>
                        </td>
                        <td>Nueva Contraseña:</td> 
                        <td colspan="5">
                            <input class="input_registrar" type="password" value="" name="clave" required/>
                        </td>
                    </tr>
                    <tr><td></td>
                        <td colspan="5"></td>
                        <td>Confirmar Contraseña:</td> 
                        <td colspan="5">
                            <input class="input_registrar" type="password" value="" name="conf_clave" required/>
                        </td>
                    </tr>
                </table>
                <input type="submit" class="btn_registrar" value="REGISTRAR"/>
            </form>
            <%
                }
                else if (mensaje == "Las contraseñas deben coincidir.")
                {
            %>
            <form method="post" onsubmit="ProcesarForm(this,'RegistroUsuario','cuerpo');return false">
                <table class="tabla_registrar">
                    <%
                        String nombre_temporal = (String) session.getAttribute("nombre_temporal");
                        String apellidos_temporal = (String) session.getAttribute("apellidos_temporal");
                        String usuario_temporal = (String) session.getAttribute("usuario_temporal");
                        String poblacion_temporal = (String) session.getAttribute("poblacion_temporal");
                        String provincia_temporal = (String) session.getAttribute("provincia_temporal");
                        String codigo_postal_temporal = (String) session.getAttribute("codigo_postal_temporal");
                        String domicilio_temporal = (String) session.getAttribute("domicilio_temporal");
                        String telefono_temporal = (String) session.getAttribute("telefono_temporal");
                        String correo_temporal = (String) session.getAttribute("correo_temporal");
                        boolean datos_vacios = true;
                        
                        if((nombre_temporal != null) && (apellidos_temporal != null) && (usuario_temporal != null) && (poblacion_temporal != null) && (codigo_postal_temporal != null) && (domicilio_temporal != null) && (telefono_temporal != null) && (correo_temporal != null)) {
                            datos_vacios = false;
                        }
                        
                        if(datos_vacios == true)
                        {
                    %>
                    <tr class="fila_vacia"><td></td><td></td><td></td><td></td><td></td><td></td></tr>
                    <tr>
                        <td>Nombre:</td> 
                        <td colspan="5">
                            <input class="input_registrar" type="text" pattern="[a-zA-Z]+" name="nombre" required/>
                        </td>
                        <td>Apellidos:</td> 
                        <td colspan="5">
                            <input class="input_registrar" type="text" pattern="[a-zA-Z ]{2,35}" name="apellidos" required/>
                        </td>
                    </tr>
                    <tr>
                        <td>Usuario:</td> 
                        <td colspan="5">
                            <input class="input_registrar" type="text" name="usuario" required/>
                        </td>
                        <td>Correo:</td> 
                        <td colspan="5">
                            <input class="input_registrar" type="text" pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,4}$" name="correo" required/>
                        </td>
                    </tr>
                    <tr>
                        <td>Domicilio:</td> 
                        <td colspan="5">
                            <input class="input_registrar" type="text" name="domicilio" required/>
                        </td>
                        <td>Población:</td> 
                        <td colspan="5">
                            <input class="input_registrar" type="text" pattern="[a-zA-Z]+" name="poblacion" required/>
                        </td>
                    </tr>
                    <tr>
                        <td>Provincia:</td> 
                        <td colspan="5">
                            <input class="input_registrar" type="text" pattern="[a-zA-Z]+" name="provincia" required/>
                        </td>
                        <td>Código Postal:</td> 
                        <td colspan="5">
                            <input class="input_registrar" type="text" pattern="[0-9][0-9][0-9][0-9][0-9]" name="cp" required/>
                        </td>
                    </tr>
                    <tr>
                        <td>Telefono:</td> 
                        <td colspan="5">
                            <input class="input_registrar" type="text" pattern="[0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9]" name="telefono" required/>
                        </td>
                        <td>Nueva Contraseña:</td> 
                        <td colspan="5">
                            <input class="input_registrar" type="password" value="" name="clave" required/>
                        </td>
                    </tr>
                    <tr><td></td>
                        <td colspan="5"></td>
                        <td>Confirmar Contraseña:</td> 
                        <td colspan="5">
                            <input class="input_registrar" type="password" value="" name="conf_clave" required/>
                        </td>
                    </tr>
                    <%
                        }
                        else if(datos_vacios == false) {
                    %>
                    <tr class="fila_vacia"><td></td><td></td><td></td><td></td><td></td><td></td></tr>
                    <tr>
                        <td>Nombre:</td> 
                        <td colspan="5">
                            <input class="input_registrar" type="text" pattern="[a-zA-Z]+" name="nombre" value="<%=nombre_temporal%>" required/>
                        </td>
                        <td>Apellidos:</td> 
                        <td colspan="5">
                            <input class="input_registrar" type="text" pattern="[a-zA-Z ]{2,35}" name="apellidos" value="<%=apellidos_temporal%>" required/>
                        </td>
                    </tr>
                    <tr>
                        <td>Usuario:</td> 
                        <td colspan="5">
                            <input class="input_registrar" type="text" name="usuario" value="<%=usuario_temporal%>" required/>
                        </td>
                        <td>Correo:</td> 
                        <td colspan="5">
                            <input class="input_registrar" type="text" pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,4}$" name="correo" value="<%=correo_temporal%>" required/>
                        </td>
                    </tr>
                    <tr>
                        <td>Domicilio:</td> 
                        <td colspan="5">
                            <input class="input_registrar" type="text" name="domicilio" value="<%=domicilio_temporal%>" required/>
                        </td>
                        <td>Población:</td> 
                        <td colspan="5">
                            <input class="input_registrar" type="text" pattern="[a-zA-Z]+" name="poblacion" value="<%=poblacion_temporal%>" required/>
                        </td>
                    </tr>
                    <tr>
                        <td>Provincia:</td> 
                        <td colspan="5">
                            <input class="input_registrar" type="text" pattern="[a-zA-Z]+" name="provincia" value="<%=provincia_temporal%>" required/>
                        </td>
                        <td>Código Postal:</td> 
                        <td colspan="5">
                            <input class="input_registrar" type="text" pattern="[0-9][0-9][0-9][0-9][0-9]" name="cp" value="<%=codigo_postal_temporal%>" required/>
                        </td>
                    </tr>
                    <tr>
                        <td>Telefono:</td> 
                        <td colspan="5">
                            <input class="input_registrar" type="text" pattern="[0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9]" name="telefono" value="<%=telefono_temporal%>" required/>
                        </td>
                        <td>Nueva Contraseña:</td> 
                        <td colspan="5">
                            <input class="input_registrar" type="password" value="" name="clave" required/>
                        </td>
                    </tr>
                    <tr><td></td>
                        <td colspan="5"></td>
                        <td>Confirmar Contraseña:</td> 
                        <td colspan="5">
                            <input class="input_registrar" type="password" value="" name="conf_clave" required/>
                        </td>
                    </tr>
                    <%
                        }
                    %>
                </table>
                <input type="submit" class="btn_registrar" value="REGISTRAR"/>
            </form>
            <%
                }else if(mensaje == "Nombre de usuario no disponible.")
                {
            %>
            <form method="post" onsubmit="ProcesarForm(this,'RegistroUsuario','cuerpo');return false" >
                <table class="tabla_registrar">
                    <tr class="fila_vacia"><td></td><td></td><td></td><td></td><td></td><td></td></tr>
                    <tr>
                        <td>Nombre:</td> 
                        <td colspan="5">
                            <input class="input_registrar" type="text" pattern="[a-zA-Z]+" name="nombre" required/>
                        </td>
                        <td>Apellidos:</td> 
                        <td colspan="5">
                            <input class="input_registrar" type="text" pattern="[a-zA-Z ]{2,35}" name="apellidos" required/>
                        </td>
                    </tr>
                    <tr>
                        <td>Usuario:</td> 
                        <td colspan="5">
                            <input class="input_registrar" type="text" name="usuario" required/>
                        </td>
                        <td>Correo:</td> 
                        <td colspan="5">
                            <input class="input_registrar" type="text" pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,4}$" name="correo" required/>
                        </td>
                    </tr>
                    <tr>
                        <td>Domicilio:</td> 
                        <td colspan="5">
                            <input class="input_registrar" type="text" name="domicilio" required/>
                        </td>
                        <td>Población:</td> 
                        <td colspan="5">
                            <input class="input_registrar" type="text" pattern="[a-zA-Z]+" name="poblacion" required/>
                        </td>
                    </tr>
                    <tr>
                        <td>Provincia:</td> 
                        <td colspan="5">
                            <input class="input_registrar" type="text" pattern="[a-zA-Z]+" name="provincia" required/>
                        </td>
                        <td>Código Postal:</td> 
                        <td colspan="5">
                            <input class="input_registrar" type="text" pattern="[0-9][0-9][0-9][0-9][0-9]" name="cp" required/>
                        </td>
                    </tr>
                    <tr>
                        <td>Telefono:</td> 
                        <td colspan="5">
                            <input class="input_registrar" type="text" pattern="[0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9]" name="telefono" required/>
                        </td>
                        <td>Nueva Contraseña:</td> 
                        <td colspan="5">
                            <input class="input_registrar" type="password" value="" name="clave" required/>
                        </td>
                    </tr>
                    <tr><td></td>
                        <td colspan="5"></td>
                        <td>Confirmar Contraseña:</td> 
                        <td colspan="5">
                            <input class="input_registrar" type="password" value="" name="conf_clave" required/>
                        </td>
                    </tr>
                </table>
                <input type="submit" class="btn_registrar" value="REGISTRAR"/>
            </form>    
            <%
                }
            %>
            <hr class="separador_botones_registrar">
            <input type="button" class="btn_volver" value="VOLVER AL LOGIN" onclick="Cargar('html/usuario_login.jsp','cuerpo')"/>
            </div>
        </div>
    </body>
</html>
