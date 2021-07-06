<%@page language="java" import="sesion2.*" contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>RESGUARDO</title>
        <link href="css/estilo_resguardo.css" rel="stylesheet" type="text/css"/>
        <script src="js/resguardo.js" type="text/javascript"></script>
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
        AccesoBD accesoBD = AccesoBD.getInstance();
        
        String usuarioActual = (String)session.getAttribute("usuario");
        ResponseBD responseBD = accesoBD.obtenerDatosUsuario(usuarioActual);
        if(responseBD.isSuccess())
        {
            UsuarioBD usuarioBD = (UsuarioBD) responseBD.getValor();
        %>
            <div class="principal">
                <div class="div_datos_y_productos">
                    <div class="div_datos">
                        <div class="datos_cuenta">
                            <h3 class="titulos">DATOS ENVÍO</h3>
                            <form method="post" onsubmit="ProcesarForm(this,'ModificarDatosEnvio','cuerpo');return false">
                                <table class="tabla_cuenta">
                                    <tr>
                                        <td>Nombre:</td> 
                                        <td>
                                            <input class="input_resguardo" type="text" name="nombre" id="nombre" value="<%=usuarioBD.getNombre()%>" disabled required/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>Apellidos:</td> 
                                        <td>
                                            <input class="input_resguardo" type="text" name="apellidos" id="apellidos" value="<%=usuarioBD.getApellidos()%>" disabled required/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>Domicilio:</td> 
                                        <td>
                                            <input class="input_resguardo" type="text" name="domicilio" id="domicilio" value="<%=usuarioBD.getDomicilio()%>" disabled required/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>Población:</td> 
                                        <td>
                                            <input class="input_resguardo" type="text" name="poblacion" id="poblacion" value="<%=usuarioBD.getPoblacion()%>" disabled required/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>Provincia:</td> 
                                        <td>
                                            <input class="input_resguardo" type="text" name="provincia" id="provincia" value="<%=usuarioBD.getProvincia()%>" disabled required/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>Código Postal:</td> 
                                        <td>
                                            <input class="input_resguardo" type="text" name="cp" id="cp" value="<%=usuarioBD.getCod_postal()%>" disabled required/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>Teléfono:</td> 
                                        <td>
                                            <input class="input_resguardo" type="text" name="telefono" id="telefono" value="<%=usuarioBD.getTelefono()%>" disabled required/>
                                        </td>
                                    </tr>
                                </table>
                                <input type="button" id="bttn_modificar_envio" class="btn_modificar_envio" onclick="modificarDatosEnvio(this)" value="MODIFICAR"/>
                            </form>
                        </div>
                        <div class="datos_facturacion">
                            <h3 class="titulos">DATOS FACTURACION</h3>
                                <fieldset>
                                    <legend>Método de pago:</legend>
                                    <p>
                                        <label>
                                            <input class="input_resguardo" type="radio" name="tipo_pago" id="tipo_contrarembolso" value="Contrarembolso" onchange="ponerInvisibleTarjeta()" disabled checked="true" >Contrarembolso
                                        </label>
                                    </p>
                                    <p>
                                        <label>
                                            <input class="input_resguardo" type="radio" name="tipo_pago" id="tipo_tarjeta" value="Tarjeta" onchange="ponerVisibleTarjeta(this)" disabled>Tarjeta
                                        </label>
                                    </p>
                                </fieldset>
                                <br>
                                <div class="tarjetaInvisible" id="tarjeta">
                                    <table>
                                        <tr style="display: none;"><td></td><td></td></tr>
                                        <tr>
                                            <td>Número tarjeta:</td>
                                            <td colspan="2"><input id="num_tarjeta" type="text" name="num_tarjeta" required/></td>
                                        </tr>
                                        <tr>
                                            <td>Caducidad:</td>
                                            <td colspan="2"><input id="caducidad" type="month" name="caducidad" required/></td>
                                        </tr>
                                        <tr>
                                            <td>CVV:</td>
                                            <td colspan="2"><input id="cvv" type="number" name="cvv" required/></td>
                                        </tr>
                                    </table>
                                </div>
                                <input type="button" class="btn_modificar_facturacion" id="bttn_modificar_tipo_pago" onclick="modificarDatosFacturacion()" value="MODIFICAR"/>
                        </div>
                    </div>
                    <div class="div_productos">
                        <h3 class="titulos">PRODUCTOS CARRITO</h3>
                        <div class="productos_carrito">
                            <!--AQUÍ SE AÑADIRÁN LOS PRODUCTOS DE CLASE: producto_carrito (insertados con el DOM en resguardo.js)-->
                        </div>
                        <p class="total_carrito" name="total_carrito">Total: <span id="precio_total_carrito">0€</span></p>
                    </div>
                </div>
            <div class="div_finalizar_compra">
                <button  id="bttn_finalizar_compra" class="btn_finalizar_compra" onclick="comprobarDatosFacturacion()">FINALIZAR COMPRA</button>
                <button  id="bttn_volver" class="btn_volver" onclick="Cargar('html/carrito.jsp','cuerpo')">VOLVER AL CARRITO</button>
            </div>
        </div>
        <%    
        }
        else{
        %>
            <img src="img/cerrar.png" style="display:none;" onload="alert('<%=(String)responseBD.getValor()%>')">
        <%
        }
        %>
    </body>
</html>
