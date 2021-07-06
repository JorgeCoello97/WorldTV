package sesion2;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ModificarDatosPersonales extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession sesion = request.getSession();
        AccesoBD accesoBD = AccesoBD.getInstance();
        
        String usuario = (String) sesion.getAttribute("usuario");
        String nombre = request.getParameter("nombre");
        String apellidos = request.getParameter("apellidos");
        String domicilio = request.getParameter("domicilio");
        String poblacion = request.getParameter("poblacion");
        String provincia = request.getParameter("provincia");
        String codigo_postal = request.getParameter("cp");
        String telefono = request.getParameter("telefono");
        
        UsuarioBD usuarioBD = new UsuarioBD();
        usuarioBD.setUsuario(usuario);
        usuarioBD.setNombre(nombre);
        usuarioBD.setApellidos(apellidos);
        usuarioBD.setDomicilio(domicilio);
        usuarioBD.setPoblacion(poblacion);
        usuarioBD.setProvincia(provincia);
        usuarioBD.setCod_postal(codigo_postal);
        usuarioBD.setTelefono(telefono);

        ResponseBD responseBD = accesoBD.modificarDatosPersonales(usuarioBD);
        
        if(responseBD.isSuccess()) {
        
            int tuplas_afectadas = (Integer)responseBD.getValor();
            
            if(tuplas_afectadas == 0) {
            
                sesion.setAttribute("mensaje","No se ha modificado nada.");
            }
            else {
            
                sesion.setAttribute("mensaje","Se han modificado los datos personales del usuario correctamente.");
            }
        }
        else {
            
            String mensaje = (String) responseBD.getValor();
            sesion.setAttribute("mensaje",mensaje);
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
