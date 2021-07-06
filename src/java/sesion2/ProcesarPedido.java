package sesion2;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.stream.JsonParser;
import javax.json.stream.JsonParser.Event;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ProcesarPedido extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String carritoJSON = request.getParameter("carrito");
        
        AccesoBD accesoBD = AccesoBD.getInstance();
        HttpSession sesion = request.getSession(true);
        
        List <ProductoCarrito> carrito = new ArrayList();
        
        JsonReader jsonReader = Json.createReader(new StringReader(carritoJSON));
        JsonObject jobj = jsonReader.readObject();
        boolean carrito_vacio = false;
        
        if(jobj.size() > 0) {
            JsonParser jsonParser = Json.createParser(new StringReader(carritoJSON));
            
            while(jsonParser.hasNext()) {
            
                Event e = jsonParser.next();
                
                if(e == Event.KEY_NAME) {
                
                    String indice = jsonParser.getString();
                    switch(indice) {
                        case "Paquete Futbol":
                        case "Paquete Contacto":
                        case "Paquete Baloncesto":
                        case "Paquete Motor":
                        case "Paquete Series y Peliculas":
                        case "Paquete Documentales":
                        case "Paquete Publico":
                        case "Paquete Niños":
                            ProductoCarrito nuevo = new ProductoCarrito();
                            JsonObject producto = jobj.getJsonObject(indice);
                            nuevo.setCantidad(producto.getInt("cantidad"));
                            nuevo.setNombre(producto.getString("nombre"));
                            nuevo.setImagen(producto.getString("imagen"));
                            nuevo.setPrecio(producto.getInt("precio"));
                            nuevo.setPrecioUnitario(producto.getInt("precio_unitario"));
                            carrito.add(nuevo);
                            break;
                    }
                }
            }
        }
        else {
            carrito_vacio = true; 
        }
        
        if(carrito_vacio == false){
            boolean hay_stock = true;
            int i = 0;
            String mensaje = "";
            while( i < carrito.size() && hay_stock){
                ProductoCarrito producto = carrito.get(i);
                String nombre_producto = producto.getNombre();
                int cantidad = producto.getCantidad();

                ResponseBD responseBD_comprobarExistenciasProducto = accesoBD.comprobarExistenciasProducto(nombre_producto, cantidad); 

                hay_stock = responseBD_comprobarExistenciasProducto.isSuccess();

                if(hay_stock == false){
                    ResponseBD responseBD_obtenerExistenciasProducto = accesoBD.obtenerExistenciasProducto(nombre_producto);

                    if(responseBD_obtenerExistenciasProducto.isSuccess()){
                        int existencias = (Integer) responseBD_obtenerExistenciasProducto.getValor();
                        mensaje = "No hay existencias suficientes. Producto: "+nombre_producto + " (Max:"+existencias+")";
                    }
                }
                i++;
            }
            if(hay_stock){
                response.sendRedirect("html/resguardo.jsp");
            }else{
                sesion.setAttribute("mensaje", mensaje);
                response.sendRedirect("html/carrito.jsp");
            }
        }else{
            sesion.setAttribute("mensaje", "No hay productos en el carrito.");
            response.sendRedirect("html/carrito.jsp");
        }
        
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
