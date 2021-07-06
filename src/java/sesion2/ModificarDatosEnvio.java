package sesion2;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ModificarDatosEnvio extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String domicilio = request.getParameter("domicilio");
        String poblacion = request.getParameter("poblacion");
        String provincia = request.getParameter("provincia");
        String codigo_postal = request.getParameter("cp");
        String telefono = request.getParameter("telefono");
        
        AccesoBD accesoBD = AccesoBD.getInstance();
        HttpSession sesion = request.getSession();
        String usuario = (String) sesion.getAttribute("usuario");
        
        ResponseBD responseBD_obtenerDatosUsuario = accesoBD.obtenerDatosUsuario(usuario);
        if(responseBD_obtenerDatosUsuario.isSuccess()){
            UsuarioBD usuarioBD = (UsuarioBD) responseBD_obtenerDatosUsuario.getValor();
            usuarioBD.setDomicilio(domicilio);
            usuarioBD.setPoblacion(poblacion);
            usuarioBD.setProvincia(provincia);
            usuarioBD.setCod_postal(codigo_postal);
            usuarioBD.setTelefono(telefono);
            
            ResponseBD responseBD_modificarDatosPersonales = accesoBD.modificarDatosPersonales(usuarioBD);
            if(responseBD_modificarDatosPersonales.isSuccess()){
                int tuplas_afectadas = (Integer) responseBD_modificarDatosPersonales.getValor();
            
                if(tuplas_afectadas == 0){
                    sesion.setAttribute("mensaje", "No se ha modificado nada.");
                }else {
                    sesion.setAttribute("mensaje", "Se han modificado los datos de envio del usuario correctamente.");
                }
                
            }else{
                String mensaje = (String) responseBD_modificarDatosPersonales.getValor();
                sesion.setAttribute("mensaje", mensaje);
            }
            
        }else{
            String mensaje = (String) responseBD_obtenerDatosUsuario.getValor();
            sesion.setAttribute("mensaje", mensaje);
        }
        
        response.sendRedirect("html/resguardo.jsp");
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
