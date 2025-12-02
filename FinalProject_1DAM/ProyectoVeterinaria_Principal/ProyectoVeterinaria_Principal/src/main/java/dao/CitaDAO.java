package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import conexionBBDD.ConexionBBDD;
import modelo.Animal;
import modelo.Cita;
import modelo.Cliente;
import modelo.Veterinario;

public class CitaDAO {
	private ConexionBBDD conexion = new ConexionBBDD();
	private Connection connection;
	private Statement sentencia;
	private PreparedStatement sentenciaParametrizada;
	private ResultSet resultSet;

	public CitaDAO() {
		this.connection = conexion.conectar();
	}

	// Crear tabla cita
	public boolean createTable() {
		String queryCreate = "CREATE TABLE IF NOT EXISTS cita (" + "idCita SERIAL PRIMARY KEY,"
				+ "idCliente INT NOT NULL," + "idAnimal INT NOT NULL," + "idVeterinario INT NOT NULL,"
				+ "fecha DATE NOT NULL," + "hora TIME NOT NULL,"
				+ "FOREIGN KEY (idAnimal) REFERENCES animal(idAnimal) ON DELETE CASCADE,"
				+ "FOREIGN KEY (idCliente) REFERENCES cliente(idCliente) ON DELETE CASCADE,"
				+ "FOREIGN KEY (idVeterinario) REFERENCES veterinario(idVeterinario) ON DELETE CASCADE " + ")";
		try {
			sentencia = connection.createStatement();
			sentencia.executeUpdate(queryCreate);
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	// Insertar cita
	public boolean insertar(Cita cita) {
		if (cita != null) {
			String queryInsert = "INSERT INTO cita" + "(idAnimal, idCliente, idVeterinario, fecha, hora) "
					+ "VALUES (?, ?, ?, ?, ?);";
			try {
				sentenciaParametrizada = connection.prepareStatement(queryInsert);
				sentenciaParametrizada.setInt(1, cita.getIdAnimal()); // idAnimal
				sentenciaParametrizada.setInt(2, cita.getIdCliente()); // idCliente
				sentenciaParametrizada.setInt(3, cita.getIdVeterinario()); // idVeterinario
				sentenciaParametrizada.setDate(4, java.sql.Date.valueOf(cita.getFecha())); // fecha
				sentenciaParametrizada.setTime(5, java.sql.Time.valueOf(cita.getHora())); // hora
				sentenciaParametrizada.executeUpdate();
				return true;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}

	// Eliminar cita por idCita
	public boolean eliminar(int idCita) {
		String queryDelete = "DELETE FROM cita WHERE idCita = ?";
		try {
			sentenciaParametrizada = connection.prepareStatement(queryDelete);
			sentenciaParametrizada.setInt(1, idCita);
			sentenciaParametrizada.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	// Consultar todas las citas
	public ArrayList<Cita> consultarCitas() {
		ArrayList<Cita> citas = new ArrayList<>();
		String querySelect = "SELECT * FROM cita ORDER BY idCita;";
		try {
			sentencia = connection.createStatement();
			resultSet = sentencia.executeQuery(querySelect);
			while (resultSet.next()) {

				Cita cita = new Cita(resultSet.getInt("idCita"), 
						resultSet.getInt("idAnimal"),
						resultSet.getInt("idCliente"), 
						resultSet.getInt("idVeterinario"),
						resultSet.getDate("fecha").toLocalDate(), 
						resultSet.getTime("hora").toLocalTime());

				citas.add(cita);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return citas;
	}

	// Editar una cita existente
	public boolean editar(Cita cita) {
		String queryUpdate = "UPDATE cita SET idAnimal = ?,idCliente = ?,  idVeterinario = ?, fecha = ?, hora = ? WHERE idCita = ?";
		try {
			sentenciaParametrizada = connection.prepareStatement(queryUpdate);
			
			sentenciaParametrizada.setInt(1, cita.getIdAnimal());
			sentenciaParametrizada.setInt(2, cita.getIdCliente());
			sentenciaParametrizada.setInt(3, cita.getIdVeterinario());
			sentenciaParametrizada.setDate(4, java.sql.Date.valueOf(cita.getFecha()));
			sentenciaParametrizada.setTime(5, java.sql.Time.valueOf(cita.getHora()));
			sentenciaParametrizada.setInt(6, cita.getIdCita());
			sentenciaParametrizada.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	// Obtener una cita por su ID
	public Cita obtenerPorId(int idCita) {
		String query = "SELECT * FROM cita WHERE idCita = ?";
		try {
			sentenciaParametrizada = connection.prepareStatement(query);
			sentenciaParametrizada.setInt(1, idCita);
			resultSet = sentenciaParametrizada.executeQuery();

			if (resultSet.next()) {
				return new Cita(resultSet.getInt("idCita"),resultSet.getInt("idAnimal"), resultSet.getInt("idCliente"), 
						resultSet.getInt("idVeterinario"), resultSet.getDate("fecha").toLocalDate(),
						resultSet.getTime("hora").toLocalTime());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null; // Si no se encuentra la cita
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
