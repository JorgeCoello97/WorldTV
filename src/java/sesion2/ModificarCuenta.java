package sesion2;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ModificarCuenta extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession sesion = request.getSession(true);
        
        String usuario = (String) sesion.getAttribute("usuario");
        String correo = request.getParameter("correo");
        String nueva_clave = request.getParameter("nueva_clave");
        String conf_nueva_clave = request.getParameter("conf_nueva_clave");
        
        if(nueva_clave.equals(conf_nueva_clave)) {
            AccesoBD accesoBD = AccesoBD.getInstance();
            ResponseBD responseBD = accesoBD.modificarCorreo_ClaveUsuario(usuario, correo, nueva_clave);
            
            if(responseBD.isSuccess()) {
            
                int tuplas_afectadas = (Integer)responseBD.getValor();
                
                if(tuplas_afectadas == 0) {
                
                    sesion.setAttribute("mensaje","No se ha modificado nada.");
                }
                else {
                    
                    sesion.setAttribute("mensaje","Se ha modificado los datos de la cuenta del usuario correctamente.");
                }
            }
            else {
            
                String mensaje = (String) responseBD.getValor();
                sesion.setAttribute("mensaje",mensaje);
            }
        }
        else {
            
            sesion.setAttribute("mensaje","Las contraseñas deben coincidir.");
        }
        
        response.sendRedirect("html/usuario_perfil.jsp");
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
