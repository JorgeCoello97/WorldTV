
//Obtenemos el div productos_carrito
var productos_carrito = document.getElementsByClassName("productos_carrito")[0];
var precio_total = 0;

//Exploramos los items del carrito
for(item in carrito) {

    
    //Creamos el div producto_carrito
    var item_carrito = document.createElement("div");
    item_carrito.className = "producto_carrito";
    
    //Imagen item carrito
    var imagen_item_carrito = document.createElement("img");
    imagen_item_carrito.className = "imagen_producto";
    imagen_item_carrito.src = carrito[item].imagen;
    imagen_item_carrito.alt = carrito[item].nombre;
    
    //Añadimos el img al div producto
    item_carrito.appendChild(imagen_item_carrito);
    
    //Div informacion_producto
    var informacion_producto = document.createElement("div");
    informacion_producto.className = "informacion_producto";

    //Construimos la tabla de dentro del div informacion_producto
    var tabla_info_producto = document.createElement("table");
    tabla_info_producto.className = "tabla_info_producto";
    
    //Rellenamos la tabla con las filas y columnas oportunas
    //Primera fila del nombre
    var fila_nombre = document.createElement("tr");
    var columna1_nombre = document.createElement("td");
    columna1_nombre.className = "atributo";
    columna1_nombre.textContent = "Nombre:";
    var columna2_nombre = document.createElement("td");
    columna2_nombre.textContent = carrito[item].nombre;
    fila_nombre.appendChild(columna1_nombre);
    fila_nombre.appendChild(columna2_nombre);
    
    //Segunda fila de la cantidad
    var fila_cantidad = document.createElement("tr");
    var columna1_cantidad = document.createElement("td");
    columna1_cantidad.className = "atributo";
    columna1_cantidad.textContent = "Cantidad:";
    var columna2_cantidad = document.createElement("td");
    columna2_cantidad.textContent = carrito[item].cantidad;
    fila_cantidad.appendChild(columna1_cantidad);
    fila_cantidad.appendChild(columna2_cantidad);
    
    //Tercera fila del precio de ese producto del carrito segun la cantidad elegida
    var fila_precio = document.createElement("tr");
    var columna1_precio = document.createElement("td");
    columna1_precio.className = "atributo";
    columna1_precio.textContent = "Precio:";
    var columna2_precio = document.createElement("td");
    columna2_precio.textContent = carrito[item].precio + "€";
    fila_precio.appendChild(columna1_precio);
    fila_precio.appendChild(columna2_precio);
    
    //Añadimos las 3 filas en orden a la tabla_info_producto
    tabla_info_producto.appendChild(fila_nombre);
    tabla_info_producto.appendChild(fila_cantidad);
    tabla_info_producto.appendChild(fila_precio);
    
    //Añadimos la tabla_info_producto al div informacion_producto
    informacion_producto.appendChild(tabla_info_producto);
    
    //Añadimos el div informacion_producto al div item_carrito
    item_carrito.appendChild(informacion_producto);
    
    productos_carrito.appendChild(item_carrito);
    
    //Sumamos al precio total el precio del item actual del carrito
    precio_total = precio_total + carrito[item].precio;

    //Actualizamos el precio total del carrito
    document.getElementById("precio_total_carrito").textContent = precio_total + "€";
}

function modificarDatosEnvio(bttn_modificar) {
    
    var input_domicilio = document.getElementById("domicilio");
    var input_poblacion = document.getElementById("poblacion");
    var input_provincia = document.getElementById("provincia");
    var input_codigo_postal = document.getElementById("cp");
    var input_telefono = document.getElementById("telefono");
    
    input_domicilio.disabled = false;
    input_poblacion.disabled = false;
    input_provincia.disabled = false;
    input_codigo_postal.disabled = false;
    input_telefono.disabled = false;
    
    var bttn_confirmar = document.createElement("input");
    bttn_confirmar.className = "btn_modificar_envio";
    bttn_confirmar.type = "submit";
    bttn_confirmar.id = "bttn_confirmar_envio";
    bttn_confirmar.value = "CONFIRMAR MODIFICACIÓN";
    bttn_confirmar.style.backgroundColor= "green";
    bttn_confirmar.addEventListener('click',function(){
        var bttn_finalizar_compra = document.getElementById("bttn_finalizar_compra");
   
        var nuevo_bttn_finalizar_compra = document.createElement("button");
        nuevo_bttn_finalizar_compra.setAttribute("id", "bttn_finalizar_compra");
        nuevo_bttn_finalizar_compra.setAttribute("class", "btn_finalizar_compra");
        nuevo_bttn_finalizar_compra.textContent = "FINALIZAR COMPRA";
        nuevo_bttn_finalizar_compra.addEventListener('click', function(){
            
            var input_contrarembolso = document.getElementById("tipo_contrarembolso");
            if  (document.getElementById("tarjeta").className == "tarjetaInvisible"){
                input_contrarembolso.checked = true;
            }else{
                input_contrarembolso.checked = false;
            }

            if(input_contrarembolso.checked){
                ProcesarCarritoContrarembolso(carrito,'Tramitacion','cuerpo',input_contrarembolso.checked);
            }else{
                ProcesarCarrito(carrito,'Tramitacion','cuerpo');
            }
        });

        bttn_finalizar_compra.parentNode.replaceChild(nuevo_bttn_finalizar_compra, bttn_finalizar_compra);
    });
    bttn_modificar.parentNode.replaceChild(bttn_confirmar, bttn_modificar);
    
    var bttn_finalizar_compra = document.getElementById("bttn_finalizar_compra");
   
    var nuevo_bttn_finalizar_compra = document.createElement("button");
    nuevo_bttn_finalizar_compra.setAttribute("id", "bttn_finalizar_compra");
    nuevo_bttn_finalizar_compra.setAttribute("class", "btn_finalizar_compra");
    nuevo_bttn_finalizar_compra.textContent = "FINALIZAR COMPRA";
    nuevo_bttn_finalizar_compra.addEventListener('click', comprobarDatosFacturacion);
    
    bttn_finalizar_compra.parentNode.replaceChild(nuevo_bttn_finalizar_compra, bttn_finalizar_compra);

}

function modificarDatosFacturacion() {
    
    var bttn_modificar = document.getElementById("bttn_modificar_tipo_pago");
    
    var input_contrarembolso = document.getElementById("tipo_contrarembolso");
    var input_tarjeta = document.getElementById("tipo_tarjeta");
    
    
    
    var bttn_confirmar = document.createElement("input");
    bttn_confirmar.className = "btn_modificar_facturacion";
    bttn_confirmar.type = "button";
    bttn_confirmar.id = "bttn_confirmar_tipo_pago";
    bttn_confirmar.value = "CONFIRMAR MODIFICACIÓN";
    bttn_confirmar.style.backgroundColor= "green";
    bttn_modificar.parentNode.replaceChild(bttn_confirmar, bttn_modificar);
    
    input_tarjeta.disabled = false;
    input_contrarembolso.disabled = false;
    
    if  (document.getElementById("tarjeta").className == "tarjetaVisible"){
        bttn_confirmar.style.marginTop = "9.7em";
        var input_num_tarjeta = document.getElementById("num_tarjeta");
        var input_caducidad = document.getElementById("caducidad");
        var input_cvv = document.getElementById("cvv");
        input_num_tarjeta.disabled = false;
        input_caducidad.disabled = false;
        input_cvv.disabled = false;
    }
    
    bttn_confirmar.addEventListener('click',confirmarDatosFacturacion);
    
    var bttn_finalizar_compra = document.getElementById("bttn_finalizar_compra");
   
    var nuevo_bttn_finalizar_compra = document.createElement("button");
    nuevo_bttn_finalizar_compra.setAttribute("id", "bttn_finalizar_compra");
    nuevo_bttn_finalizar_compra.setAttribute("class", "btn_finalizar_compra");
    nuevo_bttn_finalizar_compra.textContent = "FINALIZAR COMPRA";
    nuevo_bttn_finalizar_compra.addEventListener('click', comprobarDatosFacturacion);
    
    bttn_finalizar_compra.parentNode.replaceChild(nuevo_bttn_finalizar_compra, bttn_finalizar_compra);
}

function confirmarDatosFacturacion() {
    var bttn_confirmar = document.getElementById("bttn_confirmar_tipo_pago");
    var correcto = true;
    var input_contrarembolso = document.getElementById("tipo_contrarembolso");
    var input_tarjeta = document.getElementById("tipo_tarjeta");
    
    var bttn_modificar = document.createElement("input");
    var input_num_tarjeta = document.getElementById("num_tarjeta");
    var input_caducidad = document.getElementById("caducidad");
    var input_cvv = document.getElementById("cvv");
    
    
    input_tarjeta.disabled = true;
    input_contrarembolso.disabled = true;
    
    if  (document.getElementById("tarjeta").className == "tarjetaVisible"){
        
        bttn_modificar.style.marginTop = "9.7em";
        
        if (input_num_tarjeta.value.length == 0 && input_caducidad.value.length == 0 &&input_cvv.value.length == 0) {
            alert("Falta rellenar los datos de la tarjeta!!");
            correcto = false;
        } else {
            if (input_num_tarjeta.value.length == 0){
                alert("Falta el número de tarjeta!");
                correcto = false;
            }
            if (input_caducidad.value.length == 0){
                alert("Falta indicar la caducidad de la tarjeta!");
                correcto = false;
            }
            if (input_cvv.value.length == 0){
                alert("Falta el código CVV de la tarjeta!");
                correcto = false;
            }
        }
        
        
    }
    
    if(correcto == true) {
        if  (document.getElementById("tarjeta").className == "tarjetaInvisible"){
            input_contrarembolso.checked = true;
        }else{
            input_contrarembolso.checked = false;
        }
        bttn_modificar.className = "btn_modificar_facturacion";
        bttn_modificar.type = "button";
        bttn_modificar.value = "MODIFICAR";
        bttn_modificar.id = "bttn_modificar_tipo_pago";
        bttn_modificar.addEventListener('click', modificarDatosFacturacion);
        
        input_num_tarjeta.disabled = true;
        input_caducidad.disabled = true;
        input_cvv.disabled = true;
        
        var bttn_finalizar_compra = document.getElementById("bttn_finalizar_compra");
   
        var nuevo_bttn_finalizar_compra = document.createElement("button");
        nuevo_bttn_finalizar_compra.setAttribute("id", "bttn_finalizar_compra");
        nuevo_bttn_finalizar_compra.setAttribute("class", "btn_finalizar_compra");
        nuevo_bttn_finalizar_compra.textContent = "FINALIZAR COMPRA";
        nuevo_bttn_finalizar_compra.addEventListener('click', function(){
            var input_contrarembolso = document.getElementById("tipo_contrarembolso");
            if  (document.getElementById("tarjeta").className == "tarjetaInvisible"){
                input_contrarembolso.checked = true;
            }else{
                input_contrarembolso.checked = false;
            }

            if(input_contrarembolso.checked){
                ProcesarCarritoContrarembolso(carrito,'Tramitacion','cuerpo',input_contrarembolso.checked);
            }else{
                ProcesarCarrito(carrito,'Tramitacion','cuerpo');
            }
        });
        bttn_confirmar.parentNode.replaceChild(bttn_modificar, bttn_confirmar);
        bttn_finalizar_compra.parentNode.replaceChild(nuevo_bttn_finalizar_compra, bttn_finalizar_compra);
        
    }
}

function ponerInvisibleTarjeta(){ //click en radio contrarembolso
    var input_contrarembolso = document.getElementById("tipo_contrarembolso");
    input_contrarembolso.checked = true;
    var tarjeta = document.getElementById("tarjeta");
    tarjeta.className = "tarjetaInvisible";
    var bttn_confirmar = document.getElementById("bttn_confirmar_tipo_pago");
    bttn_confirmar.style.marginTop = null;
    
    var bttn_finalizar_compra = document.getElementById("bttn_finalizar_compra");
    bttn_finalizar_compra.addEventListener('click', function () {
        ProcesarCarrito(carrito,'Tramitacion','cuerpo');
    });
    
}

function ponerVisibleTarjeta() { //click radio tarjeta
    
    var input_contrarembolso = document.getElementById("tipo_contrarembolso");
    input_contrarembolso.checked = false;
    var tarjeta = document.getElementById("tarjeta");
    tarjeta.className = "tarjetaVisible";
    var bttn_confirmar = document.getElementById("bttn_confirmar_tipo_pago");
    bttn_confirmar.style.marginTop = "9.5em";
    
    var input_num_tarjeta = document.getElementById("num_tarjeta");
    var input_caducidad = document.getElementById("caducidad");
    var input_cvv = document.getElementById("cvv");
    input_num_tarjeta.disabled = false;
    input_caducidad.disabled = false;
    input_cvv.disabled = false;
    
}

function comprobarDatosFacturacion() {
    var input_contrarembolso = document.getElementById("tipo_contrarembolso");
    var bttn_finalizar_compra = document.getElementById("bttn_finalizar_compra");
    var input_num_tarjeta = document.getElementById("num_tarjeta");
    var input_caducidad = document.getElementById("caducidad");
    var input_cvv = document.getElementById("cvv");
    var button_confirmar_datos_facturacion = document.getElementById("bttn_confirmar_tipo_pago");
    var button_confirmar_envio = document.getElementById("bttn_confirmar_envio");
    
    var domicilio = document.getElementById("domicilio").value;
    if (domicilio == null){ domicilio = ""};
    
    var poblacion = document.getElementById("poblacion").value;
    if (poblacion == null){ poblacion = ""};
    
    var provincia = document.getElementById("provincia").value;
    if (provincia == null){ provincia = ""};
    
    var codigo_postal = document.getElementById("cp").value;
    if (codigo_postal == null){ codigo_postal = ""};
    
    var telefono = document.getElementById("telefono").value;
    if (telefono == null){ telefono = ""};
    
    var correcto = true;
    
    
    if(domicilio == "" || poblacion == "" || provincia == "" || codigo_postal == "" || telefono == ""){
        alert("Faltan rellenar algunos datos de envio!");
        correcto = false;
    }
    
    
    if(button_confirmar_datos_facturacion != null){
        alert("Confirma la modificación de los datos de facturación");
        correcto = false;
    }
    
    if (button_confirmar_envio != null) {
        alert("Confirma la modificación de los datos de envio");
        correcto = false;
    }
    
    if(correcto == true){
        if  (document.getElementById("tarjeta").className == "tarjetaInvisible"){
            input_contrarembolso.checked = true;
        }else{
            input_contrarembolso.checked = false;
        }
        
        if(input_contrarembolso.checked){
            ProcesarCarritoContrarembolso(carrito,'Tramitacion','cuerpo',input_contrarembolso.checked);
        }else{
            ProcesarCarrito(carrito,'Tramitacion','cuerpo');
        }
    }
}