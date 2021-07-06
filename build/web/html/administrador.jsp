<%@page import="java.util.List" %>
<%@page language="java" import="sesion2.*" contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Administración</title>
        <link href="css/estilo_administrador.css" rel="stylesheet" type="text/css"/>
    </head>
     <%
        //Utilizamos una variable en la sesión para informar de los mensajes de Error
        String mensaje = (String)session.getAttribute("mensaje");
        if (mensaje != null) { //Eliminamos el mensaje consumido
            session.removeAttribute("mensaje");
    %>
    <body>  
      <img src="img/cerrar.png" style="display:none;" onload="alert('<%=mensaje%>')">
  
        <%
            if(mensaje == "Se ha modificado los datos del usuario correctamente"){
                mensaje = null;
            }
            else if(mensaje == "Se ha creado la nueva cuenta de usuario correctamente"){
                mensaje = null;
            }else if(mensaje == "No se ha modificado nada" ||  mensaje == "Se han modificado los datos personales del usuario correctamente"){
                mensaje = null;
            }
        } else {%>
    <body>    
        <%
        }
        AccesoBD accesoBD = AccesoBD.getInstance();
        //Se obtiene el usuario actual registrado en el servicio web, del entorno de sesión
        String adminActual = (String)session.getAttribute("admin");
        if (adminActual == null) //No hay ningun usuario con sesión iniciada aún
        {
        //Mostramos el formulario para la introducción del usuario y la clave (inicio de sesion)
        %>
        <div class="contenido_login">
            <div class="principal_login">
                <form method="post" onsubmit="ProcesarForm(this,'LoginAdmin','cuerpo');return false">
                    <h2>LOGIN</h2>
                    <hr>

                    <div class="imgcontainer_login">
                        <img src="img/login.png" alt="Avatar" class="avatar_login">
                    </div>

                    <div class="wrapper_login">
                        <label for="usuario"><b>Nombre de usuario</b></label>
                        <input type="text" id="usuario" name="usuario" required>

                        <label for="clave"><b>Contraseña</b></label>
                        <input type="password"  id="clave" name="clave" required>

                        <input type="submit" class="btn_enviar_login" value="INICIAR SESIÓN"/>
                    </div>
                </form>
            </div>
        </div>
        <% 
        }
        else{
        %>
        <div class="contenido_inicio">
            <div class="div_modificar_productos_inicio">
                <h2>MODIFICAR PRODUCTOS</h2>
                <hr>
                <div class="productos_inicio">
                    <%
                        ResponseBD responseBD_obtenerProductos = accesoBD.obtenerProductosBD();
                        
                        if(responseBD_obtenerProductos.isSuccess()) {
                        
                            List<ModeloGeneralBD> productos = responseBD_obtenerProductos.getValorlista();
                            for(int i = 0; i < productos.size(); i++) {

                                ProductoBD producto = (ProductoBD) productos.get(i);
                    %>
                    <div class="producto_inicio">
                        <table>
                            <tr>
                                <td class="nombres_negrita_inicio">Nombre:</td>
                                <td><%=producto.getNombre()%></td>
                            </tr>
                            <tr>
                                <td class="nombres_negrita_inicio">Precio:</td>
                                <td><%=producto.getPrecio()%> €</td>
                            </tr>
                            <tr><td></td> <td></td></tr>
                            
                            <tr>
                                <td colspan="2" class="fila_botones_inicio">
                                    <button class="boton_inicio">Modificar Paquete</button>
                                    <button class="boton_inicio">Eliminar Paquete</button>
                                </td>
                            </tr>
                        </table>
                    </div>
                    <%
                        } 
                    }
                    else {
                        %>
                        <p><%=(String)responseBD_obtenerProductos.getValor()%></p>
                        <%  
                    }
                    %>
                </div>
            </div>
            <div class="div_ver_usuarios_y_pedidos_inicio">
                <div class="div_ver_usuarios_inicio">
                    <h2>USUARIOS REGISTRADOS</h2>
                    <hr>
                    <div class="usuarios_registrados_inicio">
                        <%
                        ResponseBD responseBD_obtenerUsuarios = accesoBD.obtenerUsuariosBD();
                        
                        if(responseBD_obtenerProductos.isSuccess()) {
                            
                            List<ModeloGeneralBD> usuarios = responseBD_obtenerUsuarios.getValorlista();
                            for(int i = 0; i < usuarios.size(); i++) {

                                UsuarioBD usuario = (UsuarioBD) usuarios.get(i);
                        %>
                        <div class="usuario_registrado_inicio">
                            <table>
                                <tr>
                                    <td class="nombres_negrita_inicio">ID:</td>
                                    <td><%=usuario.getCodigo()%></td>
                                </tr>
                                <tr>
                                    <td class="nombres_negrita_inicio">Usuario:</td>
                                    <td><%=usuario.getUsuario()%></td>
                                </tr>
                            </table>
                        </div>
                        <%
                            }
                        }
                        else {
                            %>
                            <p><%=(String)responseBD_obtenerUsuarios.getValor()%></p>
                            <%  
                        }
                        %>
                    </div>
                </div>
                <div class="div_ver_pedidos_inicio">
                    <h2>PEDIDOS REALIZADOS</h2>
                    <hr>
                    <div class="pedidos_realizados_inicio">
                        <%
                            ResponseBD responseBD_obtenerPedidos = accesoBD.obtenerPedidosBD();
                            
                            if(responseBD_obtenerPedidos.isSuccess()) {
                            
                                List<ModeloGeneralBD> pedidos = responseBD_obtenerPedidos.getValorlista();
                                for (int i = 0; i < pedidos.size(); i++) {
                                    
                                    PedidoBD pedido = (PedidoBD) pedidos.get(i);
                                    
                                    ResponseBD responseBD_obtenerUsuario = accesoBD.obtenerDatosUsuarioMedianteCodigo(pedido.getCod_usuario());
                                    
                                    if(responseBD_obtenerUsuario.isSuccess()) {
                                    
                                        UsuarioBD usuario = (UsuarioBD)responseBD_obtenerUsuario.getValor();
                         %>
                        <div class="pedido_realizado_inicio">
                            <table>
                                <tr>
                                    <td class="nombres_negrita_inicio">Código Pedido:</td>
                                    <td><%=pedido.getCodigo()%></td>
                                </tr>
                                <tr>
                                    <td class="nombres_negrita_inicio">Usuario:</td>
                                    <td><%=usuario.getNombre()%></td>
                                </tr>
                                <tr>
                                    <td class="nombres_negrita_inicio">Fecha:</td>
                                    <td><%=pedido.getFecha()%></td>
                                </tr>
                                <tr>
                                    <td class="nombres_negrita_inicio">Importe:</td>
                                    <td><%=pedido.getImporte()%> €</td>
                                </tr>
                                <tr>
                                    <td class="nombres_negrita_inicio">Estado:</td>
                                    <td><%=pedido.getEstado()%></td>
                                </tr>
                            </table>
                        </div>
                        <%
                                }
                                else {
                                    %>
                                    <p><%=(String)responseBD_obtenerUsuario.getValor()%></p>
                                    <%  
                                }
                            }
                        }
                        else {
                            %>
                            <p><%=(String)responseBD_obtenerPedidos.getValor()%></p>
                            <%  
                        }
                        %>
                    </div>
                </div>
            </div>
        </div>
        <%
        }%>
    </body>
</html>
