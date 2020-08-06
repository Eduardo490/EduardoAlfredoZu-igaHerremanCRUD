import PROYECTO.acciones_alumno;
import PROYECTO.alumno;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author demon
 */
public class consultar_alumnos extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet consultar_alumnos</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Lista de alumnos inscritos</h1>"
                    + "<a href='index.html' >Regresar al menu</a>");
            
            //necesitamos la lista de alumnos
            List<alumno> lista = acciones_alumno.getAllAlumnos();
            
            out.println("<table border = '1'>"
                    + "<tr>"
                    + "<th>ID</th>"
                    + "<th>Nombre</th>"
                    + "<th>Password</th>"
                    + "<th>Email</th>"
                    + "<th>Pais</th>"
                    + "</tr>");
            //necesito obtener la consulta y rellenar la tabla
            for(alumno a : lista){
                out.println("<tr>"
                        
                        + "<td>"+a.getId()+"</td>"
                        + "<td>"+a.getNombre()+"</td>"
                        + "<td>"+a.getPassword()+"</td>"
                        + "<td>"+a.getEmail()+"</td>"
                        + "<td>"+a.getPais()+"</td>"
                        
                        + "<td><a href='Editaralumno?id2="+a.getId()+"'>Editar Datos</a></td>"
                        + "<td><a href='Eliminar_alumno?id="+a.getId()+"'>Eliminar</a></td>"
                        + "</tr>");
            
            }
            
            out.println("</table>");
            
            
            out.println("</body>");
            out.println("</html>");
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
