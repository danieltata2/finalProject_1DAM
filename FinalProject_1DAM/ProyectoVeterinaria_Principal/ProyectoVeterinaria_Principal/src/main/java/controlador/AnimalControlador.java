package controlador;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import dao.AnimalDAO;
import dao.ClienteDAO;
import dao.VeterinarioDAO;
import modelo.Animal;
import modelo.Cliente;

@WebServlet("/AnimalControlador")
public class AnimalControlador extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AnimalControlador() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String opcion = request.getParameter("opcion");

		if ("crearTabla".equalsIgnoreCase(opcion)) {
			AnimalDAO animalDAO = new AnimalDAO();
			boolean creada = animalDAO.createTable();
			if (creada) {
				System.out.println("¡¡Tabla ANIMAL creada correctamente!!");
				request.setAttribute("mensaje", "¡Tabla ANIMAL creada con éxito!");
			} else {
				request.setAttribute("mensaje", "Error al crear la tabla ANIMAL.");
			}
			animalDAO.getConexion().cerrarConexion();
			RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
			dispatcher.forward(request, response);

		} else if (opcion.equalsIgnoreCase("insertar")) {
			ClienteDAO clienteDAO = new ClienteDAO();
			ArrayList<Cliente> clientes = clienteDAO.consultarClientes();
			request.setAttribute("clientes", clientes);
			clienteDAO.getConexion().cerrarConexion();

			RequestDispatcher requestDispatcher = request.getRequestDispatcher("/vistas/insertarAnimal.jsp");
			requestDispatcher.forward(request, response);

		} else if ("consultar".equalsIgnoreCase(opcion)) {
		    AnimalDAO animalDAO = new AnimalDAO();
		    ClienteDAO clienteDAO = new ClienteDAO();

		    ArrayList<Animal> lista = animalDAO.consultarAnimales();
		    ArrayList<Cliente> listaClientes = clienteDAO.consultarClientes();

		    request.setAttribute("lista", lista);
		    request.setAttribute("listaClientes", listaClientes);

		    animalDAO.getConexion().cerrarConexion();
		    clienteDAO.getConexion().cerrarConexion();

		    RequestDispatcher dispatcher = request.getRequestDispatcher("/vistas/consultarAnimal.jsp");
		    dispatcher.forward(request, response);
		} else if ("editar".equalsIgnoreCase(opcion)) {
			try {
				int idAnimal = Integer.parseInt(request.getParameter("idAnimal"));
				AnimalDAO animalDAO = new AnimalDAO();
				Animal animal = animalDAO.consultarPorIdAnimal(idAnimal);

				if (animal != null) {
					request.setAttribute("animal", animal);
					RequestDispatcher dispatcher = request.getRequestDispatcher("/vistas/editarAnimal.jsp");
					dispatcher.forward(request, response);
				} else {
					request.setAttribute("error", "Animal no encontrado");
					RequestDispatcher dispatcher = request.getRequestDispatcher("/vistas/editarAnimal.jsp");
					dispatcher.forward(request, response);
				}
				animalDAO.getConexion().cerrarConexion();
			} catch (NumberFormatException e) {
				request.setAttribute("error", "ID de animal inválido");
				RequestDispatcher dispatcher = request.getRequestDispatcher("/vistas/editarAnimal.jsp");
				dispatcher.forward(request, response);
			}
		} else if (opcion.equalsIgnoreCase("eliminar")) {
			String confirmacion = request.getParameter("confirmacion");

			if (confirmacion == null) {
				
				request.setAttribute("idAnimal", request.getParameter("idAnimal"));
				RequestDispatcher rd = request.getRequestDispatcher("/vistas/eliminarAnimal.jsp");
				rd.forward(request, response);
			} else if (confirmacion.equals("si")) {
		
				AnimalDAO animalDAO = new AnimalDAO();
				try {
					int id = Integer.parseInt(request.getParameter("idAnimal"));
					if (animalDAO.eliminar(id)) {
						request.setAttribute("mensaje", "Animal eliminado correctamente.");
					}
				} catch (NumberFormatException e) {
					request.setAttribute("mensaje", "ID inválido.");
				} finally {
					animalDAO.getConexion().cerrarConexion();
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
			AnimalDAO animalDAO = new AnimalDAO();
			Animal animal = new Animal();

			animal.setNombre(request.getParameter("nombre"));
			animal.setEspecie(request.getParameter("especie"));
			animal.setGenero(request.getParameter("genero"));

			try {
				int idCliente = Integer.valueOf(request.getParameter("idCliente"));
				animal.setIdCliente(idCliente);

			} catch (NumberFormatException e) {
				System.out.println("ERROR: idCliente inválido o vacío.");
				request.setAttribute("error", "ID de Cliente no válido.");
				request.getRequestDispatcher("/vistas/insertarAnimal.jsp").forward(request, response);
				return;
			}

			if (animalDAO.insertar(animal)) {
				System.out.println("Animal insertado correctamente");
			}
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("/index.jsp");
			requestDispatcher.forward(request, response);
		} else if ("editar".equals(opcion)) {
			AnimalDAO animalDAO = new AnimalDAO();
			Animal animal = new Animal();

			try {
				int idAnimal = Integer.parseInt(request.getParameter("idAnimal"));
				animal.setIdAnimal(idAnimal); 
			} catch (NumberFormatException e) {
				System.out.println("ID de Animal inválido.");
				request.setAttribute("error", "ID inválido.");
				request.getRequestDispatcher("/vistas/editarAnimal.jsp").forward(request, response);
				return;
			}

			animal.setNombre(request.getParameter("nombre"));
			animal.setEspecie(request.getParameter("especie"));
			animal.setGenero(request.getParameter("genero"));

			if (animalDAO.editar(animal)) {
				System.out.println("Animal actualizado correctamente");
			} else {
				System.out.println("Error al actualizar el animal");
			}

			animalDAO.getConexion().cerrarConexion();
			RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
			dispatcher.forward(request, response);
		}

	}
}
