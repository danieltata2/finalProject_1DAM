package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import conexionBBDD.ConexionBBDD;
import modelo.Animal;
import modelo.Cliente;

public class AnimalDAO {
	private ConexionBBDD conexion = new ConexionBBDD();
	private Connection connection;
	private Statement sentencia;
	private PreparedStatement sentenciaParametrizada;
	private ResultSet resultSet;

	public AnimalDAO() {
		this.connection = conexion.conectar();
	}

	// Crear tabla animal
	public boolean createTable() {
		String queryCreate = "CREATE TABLE IF NOT EXISTS animal (" + "idAnimal SERIAL PRIMARY KEY,"
				+ "nombre VARCHAR(50) NOT NULL," + "especie VARCHAR(50) NOT NULL," + "genero VARCHAR(50),"
				+ "idCliente INT NOT NULL," + "FOREIGN KEY (idCliente) REFERENCES cliente(idCliente) ON DELETE CASCADE )";
		try {
			sentencia = connection.createStatement();
			sentencia.executeUpdate(queryCreate);
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

// Insertar en la tabla animal
	public boolean insertar(Animal animal) {
		if (animal != null) {
			String queryInsert = "INSERT INTO animal (nombre, especie, genero, idCliente)" + " VALUES (?, ?, ?, ?);";

			try {
				sentenciaParametrizada = connection.prepareStatement(queryInsert);
				sentenciaParametrizada.setString(1, animal.getNombre());
				sentenciaParametrizada.setString(2, animal.getEspecie());
				sentenciaParametrizada.setString(3, animal.getGenero());
				sentenciaParametrizada.setInt(4, animal.getIdCliente());
				sentenciaParametrizada.executeUpdate();
				return true;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}

	// Consultar todos los animales y eso devuelve una lista de animales

	public ArrayList<Animal> consultarAnimales() {
		ArrayList<Animal> animales = new ArrayList<>();
		String querySelect = "SELECT * FROM animal ORDER BY idAnimal;";
		try {
			sentencia = connection.createStatement();
			resultSet = sentencia.executeQuery(querySelect);
			while (resultSet.next()) {
				animales.add(new Animal(resultSet.getInt("idAnimal"), resultSet.getString("nombre"),
						resultSet.getString("especie"), resultSet.getString("genero"), resultSet.getInt("idCliente")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return animales;
	}

	// Eliminar animal
	public boolean eliminar(int idAnimal) {
		String queryDelete = "DELETE FROM animal WHERE idAnimal = ?";
		try {
			sentenciaParametrizada = connection.prepareStatement(queryDelete);
			sentenciaParametrizada.setInt(1, idAnimal);
			sentenciaParametrizada.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	// consultarPorId animal por id
	public Animal consultarPorIdAnimal(int idAnimal) {
		Animal animal = null;
		String querySelect = "SELECT * FROM animal WHERE idAnimal = ?;";
		try {
			sentenciaParametrizada = connection.prepareStatement(querySelect);
			sentenciaParametrizada.setInt(1, idAnimal);
			resultSet = sentenciaParametrizada.executeQuery();
			if (resultSet.next()) {
				animal= new Animal(resultSet.getInt("idAnimal"), resultSet.getString("nombre"),
						resultSet.getString("especie"), resultSet.getString("genero"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return animal;
	}
	public boolean editar(Animal animal) {

		String query = "UPDATE animal SET nombre = ?, especie = ?, genero = ?  WHERE idAnimal = ?";

		try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
			preparedStatement.setString(1, animal.getNombre());
			preparedStatement.setString(2, animal.getEspecie());
			preparedStatement.setString(3, animal.getGenero());
			 preparedStatement.setInt(4, animal.getIdAnimal());
			
			preparedStatement.executeUpdate();
			return true;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	// Consultar animal por su nombre
	public Animal consultarPorNombre(String nombre) {
	    Animal animal = null;
	    String querySelect = "SELECT * FROM animal WHERE nombre = ?;"; 
	    try {
	        sentenciaParametrizada = connection.prepareStatement(querySelect);
	        sentenciaParametrizada.setString(1, nombre); 
	        resultSet = sentenciaParametrizada.executeQuery();
	        if (resultSet.next()) {
	            animal = new Animal(
	                resultSet.getInt("idAnimal"),
	                resultSet.getString("nombre"),
	                resultSet.getString("especie"),
	                resultSet.getString("genero"),
	                resultSet.getInt("idCliente")
	            );
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return animal; 
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
