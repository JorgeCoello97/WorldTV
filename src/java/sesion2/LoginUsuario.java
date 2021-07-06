package sesion2;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginUsuario extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        boolean correcto = false;
        String usuario = request.getParameter("usuario");
        String clave = request.getParameter("clave");
        
        String paginaActual = request.getParameter("pagina");
        
        HttpSession sesion = request.getSession(true);
        
        AccesoBD accesoBD = AccesoBD.getInstance();
        if ((usuario != null) && (clave!=null))
        {
            ResponseBD responseBD = accesoBD.comprobarUsuarioBD(usuario, clave);
            if (responseBD.isSuccess())
            {
                sesion.setAttribute("usuario", usuario);
                correcto = true;
            }
            else
            {
                String mensaje = (String) responseBD.getValor();
                sesion.setAttribute("mensaje", mensaje);
            }
        }
        else
        {
            sesion.setAttribute("mensaje", "Falta introducir el usuario o la clave");
        }
        
        if(correcto){
            if (paginaActual.equals("usuario_login.jsp")) {
                response.sendRedirect("html/usuario_perfil.jsp");
            }
            else if(paginaActual.equals("carrito_login.jsp")){
                response.sendRedirect("html/carrito.jsp");
            }  
        }
        else{
            response.sendRedirect("html/"+paginaActual);
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
