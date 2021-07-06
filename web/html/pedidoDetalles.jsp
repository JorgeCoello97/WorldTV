<%@page import="java.util.List"%>
<%@page contentType="text/html" import="sesion2.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>USUARIO</title>
        <link href="css/estilo_pedido_detalles.css" rel="stylesheet" type="text/css"/>
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
        int codigo_pedido = (Integer) session.getAttribute("pedido");
        AccesoBD accesoBD = AccesoBD.getInstance();
        ResponseBD responseBD = accesoBD.obtenerDetallePedidoCancelar(codigo_pedido);
            
        if(!responseBD.isSuccess()){
             mensaje = (String) responseBD.getValor();
        %>
        <img src="img/cerrar.png" style="display:none;" onload="alert('<%=mensaje%>')">
        <%
        }
        else
        {
            List<ModeloGeneralBD> detallesPedidos = responseBD.getValorlista();
            if(detallesPedidos.isEmpty()){
                 mensaje = "No hay productos en el pedido.";
        %>
        <img src="img/cerrar.png" style="display:none;" onload="alert('<%=mensaje%>')">
        <%
                ResponseBD responseBD_cancelarPedidoMedianteCodigo = accesoBD.cancelarPedidoMedianteCodigo(codigo_pedido);
                if(!responseBD_cancelarPedidoMedianteCodigo.isSuccess()) {
                    mensaje= (String) responseBD.getValor();
                }
                else {
                %>
                    <img src="img/cerrar.png" style="display:none;" onload="Cargar('html/usuario_historial.jsp','cuerpo')">
                <%
                }
            }
            else
            {
        %>
        <div class="contenido_detalles">
            <h2 class="titulo_detalles"> Productos del Pedido '<%=codigo_pedido%>'</h2>
            <div class="table_productos" id="principal_detalles">
                <div class="table_productos_thead">
                    <div class="table_productos_thead_tr">
                        <div class="table_productos_th">Nombre Producto</div>
                        <div class="table_productos_th">Unidades</div>
                        <div class="table_productos_th">Precio Total</div>
                        <div class="table_productos_th" style="display: none;">Acción</div>
                    </div>
                </div>
                <div class="table_productos_tbody">
                    <%
                    for (int i = 0; i < detallesPedidos.size(); i++) {
                        DetallePedidoCancelar dpc = (DetallePedidoCancelar) detallesPedidos.get(i);
                    %>
                        <form class="table_productos_tr" method="post" onsubmit="ProcesarForm(this,'CancelarProductos','cuerpo');return false" >
                            <div class="table_productos_td">
                                <input type="text" style="display: none" name="codigo_pedido"value="<%=codigo_pedido%>" />
                                <input type="text" style="display: none" name="codigo_producto"value="<%=dpc.getCodigo()%>" />
                                <%=dpc.getNombreProducto()%>
                            </div>
                                <div class="table_productos_td"><input class="input_detalle" type="text" name="unidades"value="<%=dpc.getUnidades()%>" /></div>
                                <div class="table_productos_td"><input class="input_detalle"type="text" name="precio"value="<%=dpc.getPrecio()%>" /></div>
                            <div class="table_productos_td_input"><input class="bttn_cancelar" type="submit" value="CANCELAR PRODUCTO"/></div>
                        </form>
                    <%    
                    }
                    if(detallesPedidos.size() < 5){ //relleno
                    %>
                    <div class="table_productos_tr"></div>
                    <div class="table_productos_tr"></div>
                    <div class="table_productos_tr"></div>
                    <div class="table_productos_tr"></div>
                    <div class="table_productos_tr"></div>
                    <%
                    }   
                    %>
                </div>
            </div>
        </div>
        <div class="div_boton_volver">
            <input class="boton_volver" type="button" value="Volver" onclick="Cargar('html/usuario_historial.jsp','cuerpo')"/>
        </div>
        <%
            }
        }
        %>
    </body>
</html>
