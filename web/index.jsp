<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
    <head>
        <title>WORLD TV</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <script src="./js/libCapas.js"></script>
        <link rel="stylesheet" type="text/css" href="css/estilo_inicio.css">
        <link rel="icon" type="image/ico" href="img/favicon.ico" sizes="64x64">
        <script>
            if(localStorage.getItem("carrito")) {
                carrito = JSON.parse(localStorage.getItem("carrito"));        
            }else{
                var carrito = new Object();
            }
        </script>
    </head>
    <body>
        <header>
            <input type="checkbox" id="btn_menu"/>
            <label class="imagen_menu" for="btn_menu"><img class="sandwich" src="img/sandwich.png" alt=""/></label>
            <nav>
                <a href="index.jsp">
                    <img class="logo" src="img/logo.png" alt="Página principal"/>
                </a>
                <ul>
                    <li class="enlace_inicio">
                        <a href="index.jsp" id="inicio">Inicio</a>
                    </li>
                    <li class="enlace_empresa" onclick="Cargar('html/empresa.html','cuerpo')">
                        <a href="#">Empresa</a>
                    </li>
                    <li class="enlace_contacto" onclick="Cargar('html/contacto.html','cuerpo')">
                        <a href="#">Contacto</a>
                    </li>
                    <li class="enlace_producto" onclick="Cargar('html/productos.jsp','cuerpo')">
                        <a href="#">Productos</a>
                    </li>
                    <li class="enlace_tienda" onclick="Cargar('RedirigirCarrito','cuerpo');return false">
                        <a href="#">Carrito</a>
                    </li>
                    <li class="enlace_usuario" onclick="Cargar('RedirigirUsuario','cuerpo');return false">
                        <a href="#">Usuario</a>
                    </li>
                    <li class="enlace_admin" onclick="Cargar('html/administrador.jsp','cuerpo')">
                        <a href="#">Administrador</a>
                    </li>
                </ul>
            </nav>
        </header>
        <div id="cuerpo">
            <div class="cabecera">
                <div class="divTituloCabecera"><h1 class="tituloCabecera">WORLD TV</h1></div>
                <div class="divLogoCabecera"><img class="logoCabecera" src="img/logoGrande.png" alt="WORLD TV"/></div>
                <div class="divContactoCabecera">
                    <p>Teléfono: +34 606 50 50 59</p>
                    <p>Email: miguelferrerfornali1999@gmail.com</p>
                </div>
            </div>
            <div class="noticias">
                <div id="slider">
                    <figure>
                        <img class="imagenesNoticias" src="img/noticia1.jpg" alt="Deporte"/>
                        <img class="imagenesNoticias" src="img/noticia4.jpg" alt="Noticias"/>
                        <img class="imagenesNoticias" src="img/noticia3.jpg" alt="Carreras"/>
                        <img class="imagenesNoticias" src="img/noticia2.jpg" alt="Cine"/>
                    </figure>
		</div>
            </div>
            <div class="div_inferior">
                <div class="div_descripcion">
                    <h2 id="sobre_nosotros">Sobre nosotros</h2>
                    <div class="seccion_descripcion">
                        WORLD TV es una pequeña empresa española, situada en Ondara (Alicante), centrada en el sector del entretenimiento. 
                        Nos encargamos de la distribución de canales de televisión, con el objetivo de que nuestros usuarios 
                        puedan disfrutar de un contenido explícito y de calidad cuando y donde deseen para su disfrute. En WORLD TV nos comprometemos a darle un servicio de calidad y cumplir sus deseos.
                        <br><br>
                        En nuestra página web tienes mucha más información. Explora las deferentes opciones de nuestro menú y sumérgete  
                        en el mundo WORLD TV. Ánimo! A que esperas... ;)
                    </div>
                </div>
                <div class="div_rrss">
                    <h2 id="redes_sociales">Redes sociales</h2>
                    <div>
                        <p><a href="https://twitter.com/?lang=es"><img class="imagenesDescripcion" src="img/twitter.png" alt="Twitter"/></a></p>
                        <p><label>Twitter</label></p>
                    </div>
                    <div>
                        <p><a href="https://www.instagram.com/?hl=es"><img class="imagenesDescripcion" src="img/instagram.png" alt="Instagram"/></a></p>
                        <label id="instagram">Instagram</label>
                    </div>
                    <div>
                        <p><a href="https://es-es.facebook.com/"><img class="imagenesDescripcion" src="img/facebook.png" alt="Facebook"/></a></p>
                        <p><label>Facebook</label></p>
                    </div>
                    <div>
                        <p><a href="https://es.linkedin.com/"><img class="imagenesDescripcion" src="img/linkedin.png" alt="Linkedin"/></a></p>
                        <p><label>LinkedIn</label></p>
                    </div>
                </div>
            </div>
        </div>
        
    </body>
</html>