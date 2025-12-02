package controlador;

import java.io.IOException;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import dao.UsuarioDAO;
import modelo.Usuario;

@WebServlet("/UsuarioControlador")
public class UsuarioControlador extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private UsuarioDAO usuarioDAO;

    public UsuarioControlador() {
       super();
       usuarioDAO = new UsuarioDAO();
       
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String opcion = request.getParameter("opcion");

   
        if ("cerrarSesion".equals(opcion)) {
            HttpSession sesion = request.getSession(false);
            if (sesion != null) {
                sesion.invalidate();
            }
            response.sendRedirect("vistas/login.jsp"); 
            return;
        }

        
        HttpSession sesion = request.getSession(false); 
        if (sesion != null && sesion.getAttribute("usuario") != null) {
            response.sendRedirect("index.jsp");
            return;
        }

   
        if ("registro".equals(opcion)) {
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/vistas/registro.jsp");
            requestDispatcher.forward(request, response);
        } else if ("login".equals(opcion)) {
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/vistas/login.jsp");
            requestDispatcher.forward(request, response);
        } else {
            response.sendRedirect("index.jsp");
        }
    }
 protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String opcion = request.getParameter("opcion");

        if ("registrar".equals(opcion)) {
            String nombre = request.getParameter("nombre");
            String correo = request.getParameter("correo");
            String clave = request.getParameter("clave");

            Usuario usuario = new Usuario(nombre, correo, clave);

            if (usuarioDAO.registrar(usuario)) {
             
                request.setAttribute("mensaje", "Registro exitoso. Inicie sesión.");
                request.getRequestDispatcher("/vistas/login.jsp").forward(request, response);
            } else {
             
                request.setAttribute("mensaje", "Error al registrar usuario.");
                request.getRequestDispatcher("/vistas/registro.jsp").forward(request, response);
            }

        } else if ("login".equals(opcion)) {
            String correo = request.getParameter("correo");
            String clave = request.getParameter("clave");

            Usuario usuario = usuarioDAO.login("", correo, clave);

            if (usuario != null) {
              
                HttpSession sesion = request.getSession();
                sesion.setAttribute("usuario", usuario);
                response.sendRedirect("index.jsp");
            } else {
              
                request.setAttribute("mensaje", "Correo o clave incorrectos.");
                request.getRequestDispatcher("/vistas/login.jsp").forward(request, response);
            }
        }
    }
}

  