<%@page import="java.util.List" %>
<%@page contentType="text/html" import="sesion2.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
    <head>
        <title>PRODUCTOS</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href="css/estilo_productos.css" rel="stylesheet" type="text/css"/>
        <script src="js/productos.js" type="text/javascript"></script>
    </head>
    <body>
        <%
            AccesoBD accesoBD = AccesoBD.getInstance();
            ResponseBD responseBD = accesoBD.obtenerProductosBD();
            
        if(!responseBD.isSuccess()){
            String mensaje = (String) responseBD.getValor();
        %>
        <img src="img/cerrar.png" style="display:none;" onload="alert('<%=mensaje%>')">
        <%
        }
        else
        {
            List<ModeloGeneralBD> productos = responseBD.getValorlista();
            if(productos.isEmpty()){
                String mensaje = "No hay productos en la BBDD.";
        %>
        <img src="img/cerrar.png" style="display:none;" onload="alert('<%=mensaje%>')">
        <%  }
            else
            {
        %>
        <div class="container">
            <%
                for (int i = 0; i < productos.size(); i++) {
                    ProductoBD producto = (ProductoBD) productos.get(i);
            %>     
            <div class="galeria">
                <img src=<%=producto.getImagen()%> alt=<%=producto.getNombre()%>>
                <div class="descripcion"><%=producto.getNombre()%></div>
                <div class="precio"><%=producto.getPrecio()%>€</div>
                <div class="div_boton_compra">
                    <button class="boton_compra" onclick="addCarrito(this)">Comprar</button>
                    <input class="cantidad" id="cantidad_paqueteFutbol" type="number" max=<%=producto.getExistencia()%> min="0" value="0"/>
                </div>
            </div>
            <%
                }
        %>
        </div>
    <%      }
        }
    %>
    </body>
</html>

