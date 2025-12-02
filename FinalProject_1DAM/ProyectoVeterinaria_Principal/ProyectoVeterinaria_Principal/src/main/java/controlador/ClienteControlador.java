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

@WebServlet("/ClienteControlador")
public class ClienteControlador extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ClienteControlador() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String opcion = request.getParameter("opcion");

		if ("crearTabla".equalsIgnoreCase(opcion)) {
			ClienteDAO clienteDAO = new ClienteDAO();
			boolean creada = clienteDAO.createTable();
			if (creada) {
				System.out.println("¡¡Tabla CLIENTE creada correctamente!!");
				request.setAttribute("mensaje", "¡Tabla CLIENTE creada con éxito!");
			} else {
				request.setAttribute("mensaje", "Error al crear la tabla CLIENTE.");
			}
			clienteDAO.getConexion().cerrarConexion();
			RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
			dispatcher.forward(request, response);

		} else if ("insertar".equalsIgnoreCase(opcion)) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/vistas/insertar.jsp");
			dispatcher.forward(request, response);

		} else if (opcion.equalsIgnoreCase("consultar")) {
			ClienteDAO clienteDAO = new ClienteDAO();
			ArrayList<Cliente> lista = new ArrayList<Cliente>();
			lista = clienteDAO.consultarClientes();
			for (Cliente cliente : lista)
				System.out.println(cliente);

			request.setAttribute("lista", lista);
			clienteDAO.getConexion().cerrarConexion();

			RequestDispatcher requestDispatcher = request.getRequestDispatcher("/vistas/consultar.jsp");
			requestDispatcher.forward(request, response);
		} else if ("editar".equalsIgnoreCase(opcion)) {
			try {
				int idCliente = Integer.parseInt(request.getParameter("idCliente"));
				ClienteDAO clienteDAO = new ClienteDAO();
				Cliente cliente = clienteDAO.obtenerPorId(idCliente);

				if (cliente != null) {
					request.setAttribute("cliente", cliente);
					RequestDispatcher dispatcher = request.getRequestDispatcher("/vistas/editar.jsp");
					dispatcher.forward(request, response);
				} else {
					request.setAttribute("error", "Cliente no encontrado");
					RequestDispatcher dispatcher = request.getRequestDispatcher("/vistas/error.jsp");
					dispatcher.forward(request, response);
				}
				clienteDAO.getConexion().cerrarConexion();
			} catch (NumberFormatException e) {
				request.setAttribute("error", "ID de cliente inválido");
				RequestDispatcher dispatcher = request.getRequestDispatcher("/vistas/error.jsp");
				dispatcher.forward(request, response);
			}
		}
	

		

		else if (opcion.equalsIgnoreCase("eliminar")) {
		    String confirmacion = request.getParameter("confirmacion");

		    if (confirmacion == null) {
		        
		        request.setAttribute("idCliente", request.getParameter("idCliente"));
		        RequestDispatcher rd = request.getRequestDispatcher("/vistas/eliminarCliente.jsp");
		        rd.forward(request, response);
		    } else if (confirmacion.equals("si")) {
		        
		        ClienteDAO clienteDAO = new ClienteDAO();
		        try {
		            int id = Integer.parseInt(request.getParameter("idCliente"));
		            if (clienteDAO.eliminar(id)) {
		                request.setAttribute("mensaje", "Cliente eliminado correctamente.");
		            }
		        } catch (NumberFormatException e) {
		            request.setAttribute("mensaje", "ID inválido.");
		        } finally {
		            clienteDAO.getConexion().cerrarConexion();
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
			ClienteDAO clienteDAO = new ClienteDAO();
			Cliente cliente = new Cliente();
			cliente.setNombre(request.getParameter("nombre"));
			cliente.setApellido(request.getParameter("apellido"));
			cliente.setEdad(Integer.valueOf(request.getParameter("edad")));
			cliente.setDireccion(request.getParameter("direccion"));
			cliente.setTelefono(request.getParameter("telefono"));
			cliente.setCorreo(request.getParameter("correo"));

			if (clienteDAO.insertar(cliente)) {
				System.out.println("Cliente insertado correctamente");
			}
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("/index.jsp");
			requestDispatcher.forward(request, response);
		} else if ("editar".equals(opcion)) {
			ClienteDAO clienteDAO = new ClienteDAO();
			Cliente cliente = new Cliente();

			cliente.setIdCliente(Integer.parseInt(request.getParameter("idCliente")));
			cliente.setNombre(request.getParameter("nombre"));
			cliente.setApellido(request.getParameter("apellido"));
			cliente.setEdad(Integer.valueOf(request.getParameter("edad")));
			cliente.setDireccion(request.getParameter("direccion"));
			cliente.setTelefono(request.getParameter("telefono"));
			cliente.setCorreo(request.getParameter("correo"));

			if (clienteDAO.editar(cliente)) {
				System.out.println("Cliente actualizado correctamente");
			} else {
				System.out.println("Error al actualizar el cliente");
			}

			clienteDAO.getConexion().cerrarConexion();
			RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
			dispatcher.forward(request, response);
		}

	}
}
