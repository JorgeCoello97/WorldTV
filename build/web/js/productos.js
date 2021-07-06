function addCarrito(button){
    
    var producto = new Object();
    var nuevo_producto = true;
    var div_boton_compra = button.parentNode;
    var div_galeria = div_boton_compra.parentNode;
    
    producto.cantidad = parseInt(div_boton_compra.childNodes[3].value);
    producto.nombre = div_galeria.childNodes[3].textContent;
    producto.precio_unitario = parseInt(div_galeria.childNodes[5].textContent.slice(0,div_galeria.childNodes[5].textContent.length-1));
    producto.precio = producto.precio_unitario*producto.cantidad;
    producto.imagen = div_galeria.childNodes[1].src.slice(div_galeria.childNodes[1].src.indexOf("img"),div_galeria.childNodes[1].src.length); 
    producto.cantidad_maxima = parseInt(div_boton_compra.childNodes[3].max);
    if(producto.cantidad > 0 && producto.cantidad <= producto.cantidad_maxima)
    {
        for (var item in carrito) {
            switch (item) {
                case "Paquete Futbol":
                case "Paquete Contacto":
                case "Paquete Baloncesto":
                case "Paquete Motor":
                case "Paquete Series y Peliculas":
                case "Paquete Documentales":
                case "Paquete Publico":
                case "Paquete Niños":
                    if(item == producto.nombre){
                        carrito[item].cantidad = carrito[item].cantidad + producto.cantidad;
                        carrito[item].precio = carrito[item].precio + producto.precio;
                        nuevo_producto = false; 

                        //Notificamos
                        alert("Ha(n) sido añadida(s) " + producto.cantidad + " unidad(es) del producto: " + producto.nombre);
                    }
                    break;
            }
        }

        if(nuevo_producto == true && producto.cantidad > 0){
            carrito[producto.nombre] = producto;

            //Notificamos
            alert("Han sido añadidas " + producto.cantidad + " unidades del producto: " + producto.nombre);
        }
        else if (producto.cantidad == 0) {
            alert("Introduzca las unidades que desea adquirir.");
        }

        //Almacenamos el carrito en el localStorage del navegador.
        localStorage.setItem("carrito",JSON.stringify(carrito));
    }else{
        if(producto.cantidad == 0){
            alert("Por favor introduza las unidades que desee del producto '"+producto.nombre+"'.");
        }else if(producto.cantidad < 0){
            alert("Por favor introduza un valor de unidades positivos para el producto '"+producto.nombre+"'.");
        }else{
            alert("No hay "+ producto.cantidad + " existencias disponibles actualmente del producto '"+producto.nombre+"'.");
        }
    }
}
