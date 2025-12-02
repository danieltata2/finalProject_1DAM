package controlador;

import java.io.IOException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.AnimalDAO;
import dao.CitaDAO;
import dao.ClienteDAO;
import dao.VeterinarioDAO;
import modelo.Animal;
import modelo.Cita;
import modelo.Cliente;
import modelo.Veterinario;

@WebServlet("/CitaControlador")
public class CitaControlador extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public CitaControlador() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String opcion = request.getParameter("opcion");

		if ("crearTabla".equalsIgnoreCase(opcion)) {
			CitaDAO citaDAO = new CitaDAO();
			boolean creada = citaDAO.createTable();
			if (creada) {
				System.out.println("¡¡Tabla CITA creada correctamente!!");
				request.setAttribute("mensaje", "¡Tabla CITA creada con éxito!");
			} else {
				request.setAttribute("mensaje", "Error al crear la tabla CITA.");
			}
			citaDAO.getConexion().cerrarConexion();
			RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
			dispatcher.forward(request, response);

		} else if (opcion.equalsIgnoreCase("insertar")) {

			
			ClienteDAO clienteDAO = new ClienteDAO();
			AnimalDAO animalDAO = new AnimalDAO();
			VeterinarioDAO veterinarioDAO = new VeterinarioDAO();

			request.setAttribute("clientes", clienteDAO.consultarClientes());
			request.setAttribute("animales", animalDAO.consultarAnimales());
			request.setAttribute("veterinarios", veterinarioDAO.consultarVeterinarios());

		
			clienteDAO.getConexion().cerrarConexion();
			animalDAO.getConexion().cerrarConexion();
			veterinarioDAO.getConexion().cerrarConexion();

			RequestDispatcher requestDispatcher = request.getRequestDispatcher("/vistas/insertarCita.jsp");
			requestDispatcher.forward(request, response);
		} else if (opcion.equalsIgnoreCase("consultar")) {
			CitaDAO citaDAO = new CitaDAO();
			ClienteDAO clienteDAO = new ClienteDAO();
			AnimalDAO animalDAO = new AnimalDAO();
			VeterinarioDAO veterinarioDAO = new VeterinarioDAO();

			ArrayList<Cita> lista = citaDAO.consultarCitas();
			ArrayList<Cliente> clientes = clienteDAO.consultarClientes();
			ArrayList<Animal> animales = animalDAO.consultarAnimales();
			ArrayList<Veterinario> veterinarios = veterinarioDAO.consultarVeterinarios();

			request.setAttribute("lista", lista);
			request.setAttribute("clientes", clientes);
			request.setAttribute("animales", animales);
			request.setAttribute("veterinarios", veterinarios);

			
			citaDAO.getConexion().cerrarConexion();
			clienteDAO.getConexion().cerrarConexion();
			animalDAO.getConexion().cerrarConexion();
			veterinarioDAO.getConexion().cerrarConexion();

			RequestDispatcher requestDispatcher = request.getRequestDispatcher("/vistas/consultarCita.jsp");
			requestDispatcher.forward(request, response);
		} else if ("editar".equalsIgnoreCase(opcion)) {
			try {
				int idCita = Integer.parseInt(request.getParameter("idCita"));
				CitaDAO citaDAO = new CitaDAO();
				Cita cita = citaDAO.obtenerPorId(idCita);

				if (cita != null) {
					request.setAttribute("cita", cita);

				
					ClienteDAO clienteDAO = new ClienteDAO();
					AnimalDAO animalDAO = new AnimalDAO();
					VeterinarioDAO veterinarioDAO = new VeterinarioDAO();

					request.setAttribute("clientes", clienteDAO.consultarClientes());
					request.setAttribute("animales", animalDAO.consultarAnimales());
					request.setAttribute("veterinarios", veterinarioDAO.consultarVeterinarios());

					clienteDAO.getConexion().cerrarConexion();
					animalDAO.getConexion().cerrarConexion();
					veterinarioDAO.getConexion().cerrarConexion();

					RequestDispatcher dispatcher = request.getRequestDispatcher("/vistas/editarCita.jsp");
					dispatcher.forward(request, response);
				} else {
					request.setAttribute("error", "Cita no encontrada");
					RequestDispatcher dispatcher = request.getRequestDispatcher("/vistas/error.jsp");
					dispatcher.forward(request, response);
				}

				citaDAO.getConexion().cerrarConexion();
			} catch (NumberFormatException e) {
				request.setAttribute("error", "ID de cita inválido");
				RequestDispatcher dispatcher = request.getRequestDispatcher("/vistas/error.jsp");
				dispatcher.forward(request, response);
			}
		} else if (opcion.equalsIgnoreCase("eliminar")) {
			String confirmacion = request.getParameter("confirmacion");

			if (confirmacion == null) {
			
				request.setAttribute("idCita", request.getParameter("idCita"));
				RequestDispatcher rd = request.getRequestDispatcher("/vistas/eliminarCita.jsp");
				rd.forward(request, response);
			} else if (confirmacion.equals("si")) {
			
				CitaDAO citaDAO = new CitaDAO();
				try {
					int id = Integer.parseInt(request.getParameter("idCita"));
					if (citaDAO.eliminar(id)) {
						request.setAttribute("mensaje", "Cita eliminada correctamente.");
					}
				} catch (NumberFormatException e) {
					request.setAttribute("mensaje", "ID inválido.");
				} finally {
					citaDAO.getConexion().cerrarConexion();
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
			CitaDAO citaDAO = new CitaDAO();
			Cita cita = new Cita();

			int idCliente = Integer.parseInt(request.getParameter("idCliente"));
			int idAnimal = Integer.parseInt(request.getParameter("idAnimal"));
			int idVeterinario = Integer.parseInt(request.getParameter("idVeterinario"));

			cita.setIdCliente(idCliente);
			cita.setIdAnimal(idAnimal);
			cita.setIdVeterinario(idVeterinario);
			cita.setFecha(LocalDate.parse(request.getParameter("fecha")));
			cita.setHora(LocalTime.parse(request.getParameter("hora")));

			if (citaDAO.insertar(cita)) {
				System.out.println("Cita insertada correctamente");
			} else {
				System.out.println("Error al insertar la cita");
			}

			RequestDispatcher requestDispatcher = request.getRequestDispatcher("/index.jsp");
			requestDispatcher.forward(request, response);

		} else if ("editar".equals(opcion)) {
			CitaDAO citaDAO = new CitaDAO();
			Cita cita = new Cita();

			cita.setIdCita(Integer.parseInt(request.getParameter("idCita")));
			cita.setIdAnimal(Integer.parseInt(request.getParameter("idAnimal")));
			cita.setIdCliente(Integer.parseInt(request.getParameter("idCliente")));
			cita.setIdVeterinario(Integer.parseInt(request.getParameter("idVeterinario")));
			cita.setFecha(LocalDate.parse(request.getParameter("fecha")));
			cita.setHora(LocalTime.parse(request.getParameter("hora")));

			if (citaDAO.editar(cita)) {
				System.out.println("Cita actualizada correctamente");
			} else {
				System.out.println("Error al actualizar la cita");
			}

			citaDAO.getConexion().cerrarConexion();
			RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
			dispatcher.forward(request, response);
		}
	}
}
