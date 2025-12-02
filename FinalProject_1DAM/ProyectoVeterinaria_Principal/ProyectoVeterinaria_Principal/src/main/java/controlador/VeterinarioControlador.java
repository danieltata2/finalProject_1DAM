package controlador;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ClienteDAO;
import dao.VeterinarioDAO;
import modelo.Cliente;
import modelo.Veterinario;

@WebServlet("/VeterinarioControlador")
public class VeterinarioControlador extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public VeterinarioControlador() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String opcion = request.getParameter("opcion");

		if ("crearTabla".equalsIgnoreCase(opcion)) {
			VeterinarioDAO veterinarioDAO = new VeterinarioDAO();
			boolean creada = veterinarioDAO.createTable();
			if (creada) {
				System.out.println("¡¡Tabla CLIENTE creada correctamente!!");
				request.setAttribute("mensaje", "¡Tabla CLIENTE creada con éxito!");
			} else {
				request.setAttribute("mensaje", "Error al crear la tabla CLIENTE.");
			}
			veterinarioDAO.getConexion().cerrarConexion();
			RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
			dispatcher.forward(request, response);

		} else if (opcion.equalsIgnoreCase("insertar")) {
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("/vistas/insertarVeterinario.jsp");
			requestDispatcher.forward(request, response);
		} else if (opcion.equalsIgnoreCase("consultar")) {
			VeterinarioDAO veterinarioDAO = new VeterinarioDAO();
			ArrayList<Veterinario> lista = new ArrayList<Veterinario>();
			lista = veterinarioDAO.consultarVeterinarios();
			for (Veterinario veterinario : lista)
				System.out.println(veterinario);

			request.setAttribute("lista", lista);
			veterinarioDAO.getConexion().cerrarConexion();

			RequestDispatcher requestDispatcher = request.getRequestDispatcher("/vistas/consultarVeterinario.jsp");
			requestDispatcher.forward(request, response);
		} else if ("editar".equalsIgnoreCase(opcion)) {
			try {
				int idVeterinario = Integer.parseInt(request.getParameter("idVeterinario"));
				VeterinarioDAO veterinarioDAO = new VeterinarioDAO();
				Veterinario veterinario = veterinarioDAO.obtenerPorId(idVeterinario);

				if (veterinario != null) {
					request.setAttribute("veterinario", veterinario);
					RequestDispatcher dispatcher = request.getRequestDispatcher("/vistas/editarVeterinario.jsp");
					dispatcher.forward(request, response);
				} else {
					request.setAttribute("error", "veterinario no encontrado");
					RequestDispatcher dispatcher = request.getRequestDispatcher("/vistas/error.jsp");
					dispatcher.forward(request, response);
				}
				veterinarioDAO.getConexion().cerrarConexion();
			} catch (NumberFormatException e) {
				request.setAttribute("error", "ID de cveterinario inválido");
				RequestDispatcher dispatcher = request.getRequestDispatcher("/vistas/error.jsp");
				dispatcher.forward(request, response);
			}
		}

		else if (opcion.equalsIgnoreCase("eliminar")) {
		    String confirmacion = request.getParameter("confirmacion");

		    if (confirmacion == null) {
		       
		        request.setAttribute("idVeterinario", request.getParameter("idVeterinario"));
		        RequestDispatcher rd = request.getRequestDispatcher("/vistas/eliminarVeterinario.jsp");
		        rd.forward(request, response);
		    } else if (confirmacion.equals("si")) {
		    
		        VeterinarioDAO veterinarioDAO = new VeterinarioDAO();
		        try {
		            int id = Integer.parseInt(request.getParameter("idVeterinario"));
		            if (veterinarioDAO.eliminar(id)) {
		                request.setAttribute("mensaje", "Veterinario eliminado correctamente.");
		            }
		        } catch (NumberFormatException e) {
		            request.setAttribute("mensaje", "ID inválido.");
		        } finally {
		            veterinarioDAO.getConexion().cerrarConexion();
		        }
		        RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
		        rd.forward(request, response);
		    }
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String opcion = request.getParameter("opcion");

		if (opcion.equals("insertar")) {
			VeterinarioDAO veterinarioDAO = new VeterinarioDAO();
			Veterinario veterinario = new Veterinario();
			veterinario.setNombre(request.getParameter("nombre"));
			veterinario.setApellido(request.getParameter("apellido"));
			veterinario.setTelefono(request.getParameter("telefono"));
			veterinario.setEdad(Integer.valueOf(request.getParameter("edad")));
			veterinario.setEspecialidad(request.getParameter("especialidad"));

			if (veterinarioDAO.insertar(veterinario)) {
				System.out.println("Veterinario insertado correctamente");
			}
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("/index.jsp");
			requestDispatcher.forward(request, response);
		} else if ("editar".equals(opcion)) {
			VeterinarioDAO veterinarioDAO = new VeterinarioDAO();
			Veterinario veterinario = new Veterinario();

			veterinario.setIdVeterinario(Integer.parseInt(request.getParameter("idVeterinario")));
			veterinario.setNombre(request.getParameter("nombre"));
			veterinario.setApellido(request.getParameter("apellido"));
			veterinario.setTelefono(request.getParameter("telefono"));
			veterinario.setEdad(Integer.valueOf(request.getParameter("edad")));
			veterinario.setEspecialidad(request.getParameter("especialidad"));

			if (veterinarioDAO.editar(veterinario)) {
				System.out.println("Veterinario actualizado correctamente");
			} else {
				System.out.println("Error al actualizar el veterinario");
			}

			veterinarioDAO.getConexion().cerrarConexion();
			RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
			dispatcher.forward(request, response);
		}

	}

}
