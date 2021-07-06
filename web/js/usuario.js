

function modificarDatosCuenta(){
    
    var tbody = document.getElementsByClassName("tabla_cuenta")[0].children[0];
    var input_correo = tbody.children[2].children[1].children[0];
    var input_nueva_clave = tbody.children[3].children[1].children[0];
    var input_confirmar_clave = tbody.children[4].children[1].children[0];
    var bttn_modificar = document.getElementById("bttn_cuenta");
    input_correo.disabled = false;
    input_nueva_clave.disabled = false;
    input_confirmar_clave.disabled = false;
    
    bttn_modificar.value = "CONFIRMAR MODIFICACIÓN";
    bttn_modificar.type = "submit";
    bttn_modificar.style.backgroundColor= "green";
    
    bttn_modificar.removeEventListener("click",modificarDatosCuenta);

    var bttn_cancelar = document.createElement("input");
    bttn_cancelar.id = "cancelar_cuenta";
    bttn_cancelar.type = "button";
    bttn_cancelar.className = "btn_modificar";
    bttn_cancelar.value = "CANCELAR";
    bttn_cancelar.addEventListener('click',function(){
        Cargar('html/usuario_perfil.jsp',"cuerpo");
    });
    
    var tr = document.createElement("tr");
    var td = document.createElement("td");
    td.setAttribute("colspan","6");
    td.appendChild(bttn_cancelar);
    tr.appendChild(td);
    tbody.appendChild(tr);                            
}

function modificarDatosPersonales(bttn_modificar){
    var tbody = bttn_modificar.parentNode.parentNode.parentNode;
    var input_nombre = tbody.childNodes[2].childNodes[3].childNodes[1];
    var input_apellidos = tbody.childNodes[2].childNodes[7].childNodes[1];
    var input_domicilio = tbody.childNodes[4].childNodes[3].childNodes[1];
    var input_poblacion = tbody.childNodes[6].childNodes[3].childNodes[1];
    var input_provincia = tbody.childNodes[6].childNodes[7].childNodes[1];
    var input_codigo_postal = tbody.childNodes[8].childNodes[3].childNodes[1];
    var input_telefono = tbody.childNodes[8].childNodes[7].childNodes[1];
    
    input_domicilio.disabled = false;
    input_poblacion.disabled = false;
    input_provincia.disabled = false;
    input_codigo_postal.disabled = false;
    input_telefono.disabled = false;
    
    input_domicilio.required = true;
    input_poblacion.required = true;
    input_provincia.required = true;
    input_codigo_postal.required = true;
    input_telefono.required = true;
   
    var bttn_confirmar = document.createElement("input");
    bttn_confirmar.className = "btn_modificar";
    bttn_confirmar.type = "submit";
    bttn_confirmar.value = "CONFIRMAR MODIFICACIÓN";
    bttn_confirmar.style.backgroundColor= "green";
    
    bttn_modificar.parentNode.replaceChild(bttn_confirmar, bttn_modificar);
    
    var bttn_cancelar = document.createElement("input");
    bttn_cancelar.id = "cancelar_personal";
    bttn_cancelar.type = "button";
    bttn_cancelar.className = "btn_modificar";
    bttn_cancelar.value = "CANCELAR";
    bttn_cancelar.addEventListener('click',function(){
        Cargar('html/usuario_perfil.jsp',"cuerpo");
    });
    var tr = document.createElement("tr");
    var td = document.createElement("td");
    td.setAttribute("colspan","6");
    td.appendChild(bttn_cancelar);
    tr.appendChild(td);
    tbody.appendChild(tr);
}