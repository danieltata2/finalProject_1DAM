package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import conexionBBDD.ConexionBBDD;
import modelo.Cliente;

public class ClienteDAO {
	private ConexionBBDD conexion = new ConexionBBDD();
	private Connection connection;
	private Statement sentencia;
	private PreparedStatement sentenciaParametrizada;
	private ResultSet resultSet;

	public ClienteDAO() {
		this.connection = conexion.conectar();
	}

	public boolean createTable() {
		String queryCreate = "CREATE TABLE IF NOT EXISTS cliente (" + "idCliente SERIAL PRIMARY KEY,"
				+ "nombre VARCHAR(50) NOT NULL," + "apellido VARCHAR(50) NOT NULL," + "edad int ,"
				+ "direccion VARCHAR(50) NOT NULL," + "telefono VARCHAR(20)," + "correo VARCHAR(100)" + ")";
		try {
			sentencia = connection.createStatement();
			sentencia.executeUpdate(queryCreate);
			return true;
		} catch (SQLException e) {
			System.out.println("Error al crear la tabla cliente");
			e.printStackTrace();
			return false;
		}
	}

	// Método para insertar un cliente en la base de datos
	public boolean insertar(Cliente cliente) {
		if (cliente != null) {
			String queryInsert = "INSERT INTO cliente" + "(nombre, apellido, edad, direccion, telefono, correo) "
					+ "VALUES (?, ?, ?, ?, ?, ?);";
			try {
				sentenciaParametrizada = connection.prepareStatement(queryInsert);
				sentenciaParametrizada.setString(1, cliente.getNombre());
				sentenciaParametrizada.setString(2, cliente.getApellido());
				sentenciaParametrizada.setInt(3, cliente.getEdad());
				sentenciaParametrizada.setString(4, cliente.getDireccion());
				sentenciaParametrizada.setString(5, cliente.getTelefono());
				sentenciaParametrizada.setString(6, cliente.getCorreo());

				sentenciaParametrizada.executeUpdate();
				return true;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}

	// Método para consultar todos los clientes
	public ArrayList<Cliente> consultarClientes() {
		ArrayList<Cliente> clientes = new ArrayList<Cliente>();
		String querySelect = "SELECT * FROM cliente ORDER BY idCliente;";
		try {
			sentencia = connection.createStatement();
			resultSet = sentencia.executeQuery(querySelect);
			while (resultSet.next()) {
				clientes.add(new Cliente(resultSet.getInt("idCliente"), resultSet.getString("nombre"),
						resultSet.getString("apellido"), resultSet.getInt("edad"), resultSet.getString("direccion"),
						resultSet.getString("telefono"), resultSet.getString("correo")));

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return clientes;
	}
	
	// eliminar un cliente por su ID
	public boolean eliminar(int idCliente) {
		String queryDelete = "DELETE FROM cliente WHERE idCliente = ?";
		try {
			sentenciaParametrizada = connection.prepareStatement(queryDelete);
			sentenciaParametrizada.setInt(1, idCliente);
			sentenciaParametrizada.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean editar(Cliente cliente) {

		String query = "UPDATE cliente SET nombre = ?, apellido = ?, edad = ?, direccion = ?, telefono = ?, correo = ? WHERE idCliente = ?";

		try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
			preparedStatement.setString(1, cliente.getNombre());
			preparedStatement.setString(2, cliente.getApellido());
			preparedStatement.setInt(3, cliente.getEdad());
			preparedStatement.setString(4, cliente.getDireccion());
			preparedStatement.setString(5, cliente.getTelefono());
			preparedStatement.setString(6, cliente.getCorreo());
			preparedStatement.setInt(7, cliente.getIdCliente());

			preparedStatement.executeUpdate();
			return true;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public Cliente obtenerPorId(int idCliente) {
	    String query = "SELECT * FROM cliente WHERE idCliente = ?";
	    try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
	    	preparedStatement.setInt(1, idCliente);
	        try (ResultSet resultSet = preparedStatement.executeQuery()) {
	            if (resultSet .next()) {
	                return new Cliente(
	                		resultSet .getInt("idCliente"),
	                		resultSet .getString("nombre"),
	                		resultSet .getString("apellido"),
	                		resultSet.getInt("edad"),
	                		resultSet.getString("direccion"),
	                		resultSet.getString("telefono"),
	                		resultSet.getString("correo")
	                );
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return null;
	}
	
	// Consultar cliente por su nombre
	public Cliente consultarPorNombre(String nombre) {
	    Cliente cliente = null;
	    String querySelect = "SELECT * FROM cliente WHERE nombre = ?;";  
	    try {
	        sentenciaParametrizada = connection.prepareStatement(querySelect);
	        sentenciaParametrizada.setString(1, nombre); 
	        resultSet = sentenciaParametrizada.executeQuery();  
	        if (resultSet.next()) {  
	            cliente = new Cliente(
	                resultSet.getInt("idCliente"),
	                resultSet.getString("nombre"),
	                resultSet.getString("apellido"),
	                resultSet.getInt("edad"),
	                resultSet.getString("direccion"),
	                resultSet.getString("telefono"),
	                resultSet.getString("correo")
	            );
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return cliente;  // Devuelve el cliente encontrado o null si no se encuentra
	}

	

	public ConexionBBDD getConexion() {
		return conexion;
	}

	public void setConexion(ConexionBBDD conexion) {
		this.conexion = conexion;
	}

	public Connection getConnection() {
		return connection;
	}

	public void setConnection(Connection connection) {
		this.connection = connection;
	}

	public Statement getSentencia() {
		return sentencia;
	}

	public void setSentencia(Statement sentencia) {
		this.sentencia = sentencia;
	}

	public ResultSet getResultSet() {
		return resultSet;
	}

	public void setResultSet(ResultSet resultSet) {
		this.resultSet = resultSet;
	}

}
