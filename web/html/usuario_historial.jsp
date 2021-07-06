<%@page import="java.util.List"%>
<%@page contentType="text/html" import="sesion2.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="css/estilo_usuario_historial.css" rel="stylesheet" type="text/css"/>
        <title>HISTORIAL</title>
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
        <div class="contenido_perfil">
            <div class="menu_y_logout">
                    <div class="menu_perfil">
                        <table class="opciones_perfil">
                            <tr>
                                <td id="opcion_perfil"  onclick="Cargar('html/usuario_perfil.jsp','cuerpo')">
                                    PERFIL
                                </td>
                            </tr>
                            <tr>
                                <td  id="opcion_historial" class="opcion_seleccionada" onclick="Cargar('html/usuario_historial.jsp','cuerpo');">
                                    HISTORIAL
                                </td>
                            </tr>
                        </table>
                    </div>
                    <img class="img_logout" src="img/logout.png" alt="Cerrar Sesión" onclick="Cargar('CerrarSesion','cuerpo');return false"/>
            </div>
            <div class="principal_perfil">
        <%
            String usuarioActual = (String)session.getAttribute("usuario");
            AccesoBD accesoBD = AccesoBD.getInstance();
            ResponseBD responseBD = accesoBD.obtenerPedidosUsuario(usuarioActual);
            if(responseBD.isSuccess()){
        %>
            <h2 class="titulo_historial">LISTADO DE PEDIDOS REALIZADOS</h2>
            <div class="pedidos_historial">
                <%
                List<ModeloGeneralBD> pedidos = responseBD.getValorlista();
                for (int i = pedidos.size()-1; i > -1; i--) {
                    
                        PedidoBD pedido = (PedidoBD) pedidos.get(i);
                    %>
                <div class="pedido_historial">
                    
                    <form method="post" onsubmit="ProcesarForm(this,'AbrirDetallesPedido','cuerpo');return false">
                        <img src="img/pedido.png" class="img_pedido_historial" alt="Pedido"/>
                        <input style="display: none" type="text" value="<%=pedido.getCodigo()%>" name="codigo_pedido"/>
                        <table>
                            <tr>
                                <td colspan="2" style="text-align: center"><b>Código <%=pedido.getCodigo()%></b></td>

                            </tr>
                            <tr>
                                <td>Fecha:</td>
                                <td><%=pedido.getFecha()%></td>
                            </tr>
                            <tr>
                                <td>Importe:</td>
                                <td><%=pedido.getImporte()%>€</td>
                            </tr>
                            <tr>
                                <td>Estado:</td>
                                <td><%=pedido.getEstado()%></td>
                            </tr>
                        </table>
                     <%
                        if(pedido.getEstado() == "Pendiente") {
                    %>
                        <input type="submit" class="btn_cancelar_pedido" value="Abrir Detalles"/>
                    <%
                        }
                    %>    
                    </form>   
                    <form method="post" onsubmit="ProcesarForm(this,'CancelarPedido','cuerpo');return false">
                        <div class="informacion_historial">
                            <input style="display: none" type="text" value="<%=pedido.getCodigo()%>" name="codigo_pedido"/>
                            <input style="display: none" type="text" value="<%=pedido.getCod_usuario()%>" name="cod_usuario"/>
                            
                            <%
                                if(pedido.getEstado() == "Pendiente") {
                            %>
                            <input type="submit" class="btn_cancelar_pedido" value="Cancelar Pedido"/>
                            <%
                                }
                            %>
                        </div>
                   
                    </form>
                </div> 
                <%
                    }
                %>
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
</html>