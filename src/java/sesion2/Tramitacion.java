package sesion2;

import java.io.IOException;
import java.io.StringReader;
import java.sql.Savepoint;
import java.util.ArrayList;
import java.util.List;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.stream.JsonParser;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Tramitacion extends HttpServlet {

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
            
                JsonParser.Event e = jsonParser.next();
                
                if(e == JsonParser.Event.KEY_NAME) {
                
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
        
        String nombre_usuario = (String) sesion.getAttribute("usuario");
        int importe_pedido = 0;
        
        for (ProductoCarrito productoCarrito : carrito) {
            importe_pedido += productoCarrito.getPrecio();
        }
        
        boolean insertado = false;
        
        ResponseBD responseBD_insertarPedido = accesoBD.insertarPedido(nombre_usuario,importe_pedido);
        if(responseBD_insertarPedido.isSuccess()){
            int tuplas_afectadas1 = (Integer) responseBD_insertarPedido.getValor();
            
            Savepoint savepoint = responseBD_insertarPedido.getSavepoint();
            ResponseBD responseBD_insertarDetalles = accesoBD.insertarDetalles(savepoint, carrito);
            
            if(responseBD_insertarDetalles.isSuccess()){
                
                insertado = true;
                
            }else{
                String mensaje = (String) responseBD_insertarDetalles.getValor();
                sesion.setAttribute("mensaje", mensaje);
            }
        }else{
            String mensaje = (String) responseBD_insertarPedido.getValor();
            sesion.setAttribute("mensaje", mensaje);
        }
        
        String contrarembolso = request.getParameter("contrarembolso");
        
        if(contrarembolso!= null){
            sesion.setAttribute("contrarembolso", "Recuerde que el pago debe realizarlo contrarembolso.");
        }
        if (insertado == true) {
            response.sendRedirect("html/pedidoRealizado.jsp");
        }else {
            response.sendRedirect("html/resguardo.jsp");
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
