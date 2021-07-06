package sesion2;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class CancelarPedido extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        AccesoBD accesoBD = new AccesoBD();
        HttpSession sesion = request.getSession(true);
        int codigo_pedido = Integer.parseInt(request.getParameter("codigo_pedido"));
        int cod_usuario = Integer.parseInt(request.getParameter("cod_usuario"));
        
        ResponseBD responseBD = accesoBD.cancelarPedido(cod_usuario, codigo_pedido);
        if (responseBD.isSuccess()) {
            int tuplas_afectadas = (Integer) responseBD.getValor();
            
            if(tuplas_afectadas == 0){
                sesion.setAttribute("mensaje", "No se ha podido cancelar el pedido");
            }else {
                sesion.setAttribute("mensaje", "Se ha cancelado el pedido correctamente");
            }
            
        } else {
            String mensaje = (String) responseBD.getValor();
            sesion.setAttribute("mensaje", mensaje);
        }
        
        response.sendRedirect("html/usuario_historial.jsp");
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
