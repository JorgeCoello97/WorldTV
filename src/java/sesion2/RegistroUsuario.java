
package sesion2;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class RegistroUsuario extends HttpServlet {


    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession sesion = request.getSession(true);
        
        String nombre = request.getParameter("nombre");
        String apellidos = request.getParameter("apellidos");
        String usuario = request.getParameter("usuario");
        String provincia = request.getParameter("provincia");
        String poblacion = request.getParameter("poblacion");
        String codigo_postal = request.getParameter("cp");
        String domicilio = request.getParameter("domicilio");
        String telefono = request.getParameter("telefono");
        String correo = request.getParameter("correo");
        String clave = request.getParameter("clave");
        String conf_clave = request.getParameter("conf_clave");
        
        boolean insertado = false;
        AccesoBD accesoBD = AccesoBD.getInstance();
        
        ResponseBD responseBD_disponibilidadUsuario = accesoBD.comprobarDisponibilidadUsuario(usuario);
        if (responseBD_disponibilidadUsuario.isSuccess()) {
            
            if (clave.equals(conf_clave)) {
                ResponseBD responseBD_insertarUsuario = accesoBD.insertarUsuario(nombre, apellidos, usuario, domicilio, provincia, poblacion, codigo_postal, telefono, correo, clave);
                if(responseBD_insertarUsuario.isSuccess()){
                    int tuplas_afectadas = (Integer) responseBD_insertarUsuario.getValor();
                    if(tuplas_afectadas == 0){
                        sesion.setAttribute("mensaje","No se ha registrado el usuario.");
                    }else {
                        sesion.setAttribute("mensaje","Se ha creado la nueva cuenta de usuario correctamente.");
                        sesion.setAttribute("usuario",usuario);
                        //sesion.removeAttribute("usuario");
                        sesion.removeAttribute("nombre_temporal");
                        sesion.removeAttribute("apellidos_temporal");
                        sesion.removeAttribute("usuario_temporal");
                        sesion.removeAttribute("poblacion_temporal");
                        sesion.removeAttribute("provincia_temporal");
                        sesion.removeAttribute("codigo_postal_temporal");
                        sesion.removeAttribute("telefono_temporal");
                        sesion.removeAttribute("correo_temporal");
                        
                        insertado = true;
                    }
                }else{
                    String mensaje = (String) responseBD_insertarUsuario.getValor();
                    sesion.setAttribute("mensaje", mensaje);
                }    
            }else{
                sesion.setAttribute("mensaje", "Las contraseñas deben coincidir.");
                
                sesion.setAttribute("nombre_temporal",nombre);
                sesion.setAttribute("apellidos_temporal",apellidos);
                sesion.setAttribute("usuario_temporal",usuario);
                sesion.setAttribute("poblacion_temporal",poblacion);
                sesion.setAttribute("provincia_temporal",provincia);
                sesion.setAttribute("codigo_postal_temporal",codigo_postal);
                sesion.setAttribute("domicilio_temporal",domicilio);
                sesion.setAttribute("telefono_temporal",telefono);
                sesion.setAttribute("correo_temporal",correo);
            }
        }else{
            String mensaje = (String) responseBD_disponibilidadUsuario.getValor();
            sesion.setAttribute("mensaje", mensaje);
        }
        
        if (insertado) {
            response.sendRedirect("html/usuario_login.jsp");
            
        }else {
            response.sendRedirect("html/usuario_registrar.jsp");
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

