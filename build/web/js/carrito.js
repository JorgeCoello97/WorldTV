//Obtenemos el div productos_carrito
var productos_carrito = document.getElementsByClassName("productos_carrito")[0];
var precio_total = 0;

//Exploramos los items del carrito
for(item in carrito) {

    //Creamos el div producto
    var item_carrito = document.createElement("div");
    item_carrito.className = "producto";

    //Imagen item carrito
    var imagen_item_carrito = document.createElement("img");
    imagen_item_carrito.className = "img_producto";
    imagen_item_carrito.src = carrito[item].imagen;
    imagen_item_carrito.alt = carrito[item].nombre;

    //Añadimos el img al div producto
    item_carrito.appendChild(imagen_item_carrito);

    //Div informacion
    var informacion = document.createElement("div");
    informacion.className = "informacion";

    //Construimos atributos del item carrito
    var nombre_item_carrito = document.createElement("h3");
    nombre_item_carrito.textContent = carrito[item].nombre;
    var cantidad_item_carrito = document.createElement("p");
    cantidad_item_carrito.textContent = "Cantidad: ";
    
    //Construimos el number para cantidad del procucto
    var input_cantidad = document.createElement("input");
    input_cantidad.type = "number";
    input_cantidad.max = carrito[item].cantidad_maxima;
    input_cantidad.min = 1;
    input_cantidad.value = carrito[item].cantidad;
    input_cantidad.disabled = true;
    input_cantidad.style.fontSize = "medium";
    
    var input_cantidad_max = document.createElement("input");
    input_cantidad_max.type = "number";
    input_cantidad_max.className = "maximo";
    input_cantidad_max.value = carrito[item].cantidad_maxima;
    input_cantidad_max.style.display = "none";
    
    //Añadimos el input number de cantidad al mismo parrafo del texto "Cantidad: ".
    cantidad_item_carrito.appendChild(input_cantidad);
    
    var precio_item_carrito = document.createElement("p");
    precio_item_carrito.textContent = "Precio: " + carrito[item].precio + "€";

    //Añadimos los atributos al div informacion
    informacion.appendChild(nombre_item_carrito);
    informacion.appendChild(cantidad_item_carrito);
    informacion.appendChild(precio_item_carrito);
    informacion.appendChild(input_cantidad_max);

    //Div opciones
    var opciones = document.createElement("div");
    opciones.className = "opciones";

    //Boton eliminar item carrito
    var imagen_cerrar_producto = document.createElement("img");
    imagen_cerrar_producto.className = "img_cerrar_producto";
    imagen_cerrar_producto.src = "img/cerrar.png";
    imagen_cerrar_producto.alt = "Eliminar";
    
    //Boton editar cantidad item carrito
    var imagen_editar_producto = document.createElement("img");
    imagen_editar_producto.className = "img_editar_producto";
    imagen_editar_producto.src = "img/editar.png";
    imagen_editar_producto.alt = "Editar";
    

    //Añadir boton eliminar y editar
    opciones.appendChild(imagen_cerrar_producto);
    opciones.appendChild(imagen_editar_producto);

    //Añadimos div informacion y div opciones al div producto
    item_carrito.appendChild(informacion);
    item_carrito.appendChild(opciones);

    //Añadimos el div producto al div productos_carrito
    productos_carrito.appendChild(item_carrito);
    
    //Sumamos al precio total el precio del item actual del carrito
    precio_total = precio_total + carrito[item].precio;

    //Actualizamos el precio total del carrito
    document.getElementById("precio_total_carrito").textContent = precio_total + "€";

    //Añadimos evento onclick a la imagen eliminar item
    imagen_cerrar_producto.onclick = eliminarItem;
    
    //Añadimos evento onclick a la imagen editar item
    imagen_editar_producto.onclick = editarItem;
}

function eliminarItem() {
    var div_opciones = this.parentNode;
    var div_producto = div_opciones.parentNode;
    
    //Obtenemos el nombre del item a eliminar
    var nombre_item = div_producto.childNodes[1].childNodes[0].textContent;
    
    //Actualizamos el precio total del carrito
    precio_total = precio_total - carrito[nombre_item].precio;
    document.getElementById("precio_total_carrito").textContent = precio_total + "€";
    
    //Borramos la vista del item del carrito
    productos_carrito.removeChild(div_producto);
    delete carrito[nombre_item];
    
    //Almacenamos el carrito en el localStorage del navegador.
    localStorage.setItem("carrito",JSON.stringify(carrito));
    
    //Notificamos de que se ha eliminado el item del carrito
    alert("Se eliminará el item " + nombre_item + " del carrito");
}

function editarItem() {
    
    var div_opciones = this.parentNode;
    var div_producto = div_opciones.parentNode;
    
    //Obtenemos el nombre del item a eliminar
    var nombre_item = div_producto.childNodes[1].childNodes[0].textContent;
    
    //Obtenemos el input de la cantidad del item actual
    var input_cantidad = div_producto.childNodes[1].childNodes[1].childNodes[1];
    
    //Activamos el input cantidad para que se pueda modificar la cantidad del item
    input_cantidad.disabled = false;
    
    //Ocultamos los dos botones de eliminar y editar
    var imagen_eliminar_item = div_opciones.childNodes[0];
    var imagen_editar_item = div_opciones.childNodes[1];
    imagen_eliminar_item.style.display = "none";
    imagen_editar_item.style.display = "none";
    
    //Construimos boton confirmar cantidad item carrito
    var confirmar_cantidad_item = document.createElement("img");
    confirmar_cantidad_item.className = "img_confirmar_producto";
    confirmar_cantidad_item.src = "img/confirmar.png";
    confirmar_cantidad_item.alt = "Confirmar";
    confirmar_cantidad_item.onclick = confirmarItem;
    
    //Añadimos el boton confirmar cambios en el div_opciones
    div_opciones.appendChild(confirmar_cantidad_item);
}

function confirmarItem() {
    
    var div_opciones = this.parentNode;
    var div_producto = div_opciones.parentNode;
    var div_informacion = div_producto.childNodes[1];
    //Obtenemos el nombre del item a eliminar
    var nombre_item = div_producto.childNodes[1].childNodes[0].textContent;
    
    var cantidad_max = parseInt(div_informacion.lastElementChild.value);
    
    //Obtenemos el input de la cantidad del item actual
    var input_cantidad = div_producto.childNodes[1].childNodes[1].childNodes[1];
    var cantidad = input_cantidad.value;
    //Obtenemos el precio del item a eliminar
    var precio_item = div_producto.childNodes[1].childNodes[2];
    
    
    if(cantidad > 0  && cantidad <= cantidad_max){
        //Desactivamos el input cantidad para que se pueda modificar la cantidad del item
        input_cantidad.disabled = true;

        //Ocultamos boton confirmar cantidad item
        this.style.display = "none";

        //Hacemos visible los dos botones de eliminar y editar
        var imagen_eliminar_item = div_opciones.childNodes[0];
        var imagen_editar_item = div_opciones.childNodes[1];
        imagen_eliminar_item.style.display = "block";
        imagen_editar_item.style.display = "block";

        //Comprobamos si se ha modificado la cantidad del item del carrito
        if(carrito[nombre_item].cantidad != parseInt(input_cantidad.value)) {

            //Modificamos el precio total del carrito
            precio_total = precio_total - carrito[nombre_item].precio;
            precio_total = precio_total + carrito[nombre_item].precio_unitario * parseInt(input_cantidad.value);
            document.getElementById("precio_total_carrito").textContent = precio_total + "€";

            //Modificamos la cantidad y el precio del item del carrito en el objeto carrito
            carrito[nombre_item].cantidad = parseInt(input_cantidad.value);
            carrito[nombre_item].precio = carrito[nombre_item].precio_unitario * parseInt(input_cantidad.value);

            //Modificamos la cantidad del item en la vista
            precio_item.textContent = carrito[nombre_item].precio_unitario * parseInt(input_cantidad.value);
            precio_item.textContent = "Precio: " + precio_item.textContent + "€";

            //Almacenamos el carrito en el localStorage del navegador.
            localStorage.setItem("carrito",JSON.stringify(carrito));

            //Notificamos al usuario de que se han confirmado los cambios
            alert("Se han confirmado los cambios correctamente");
        }
        else {
            alert("No se han realizado cambios");
        }
    }
    else{
        if(cantidad == 0){

            //Actualizamos el precio total del carrito
            precio_total = precio_total - carrito[nombre_item].precio;
            document.getElementById("precio_total_carrito").textContent = precio_total + "€";

            //Borramos la vista del item del carrito
            productos_carrito.removeChild(div_producto);
            delete carrito[nombre_item];

            //Almacenamos el carrito en el localStorage del navegador.
            localStorage.setItem("carrito",JSON.stringify(carrito));

            //Notificamos de que se ha eliminado el item del carrito
            alert("Se eliminará el item " + nombre_item + " del carrito");
        }else if(cantidad < 0){
            alert("Por favor introduza un valor de unidades positivos para el producto '"+nombre_item+"'.");
        }else{
            alert("No hay "+ cantidad + " existencias disponibles actualmente del producto '"+nombre_item+"'.");
        }
    }
    
}