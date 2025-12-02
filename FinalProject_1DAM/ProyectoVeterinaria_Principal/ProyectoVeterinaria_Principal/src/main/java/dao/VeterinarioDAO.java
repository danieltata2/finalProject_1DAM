package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import conexionBBDD.ConexionBBDD;
import modelo.Veterinario;

	public class VeterinarioDAO {
	    private ConexionBBDD conexion = new ConexionBBDD();
	    private Connection connection;
	    private Statement sentencia;
	    private PreparedStatement sentenciaParametrizada;
	    private ResultSet resultSet;

	    // Constructor
	    public VeterinarioDAO() {
	        this.connection = conexion.conectar();
	    }

	    // Crear tabla veterinario
	    public boolean createTable() {
	        String queryCreate =  "CREATE TABLE IF NOT EXISTS veterinario (" +
	                "idVeterinario SERIAL PRIMARY KEY," +
	                "nombre VARCHAR(50) NOT NULL," +
	                "apellido VARCHAR(50) NOT NULL," +
	                "telefono VARCHAR(20)," +
	                "edad int ," +
	                "especialidad VARCHAR(100)" +
	                ")";
	        try {
	            sentencia = connection.createStatement();
	            sentencia.executeUpdate(queryCreate);
	            return true;
	        } catch (SQLException e) {
	            e.printStackTrace();
	            return false;
	        }
	    }
	   //Insertar veterinario
	    public boolean insertar(Veterinario veterinario) {
	        if(veterinario != null) {
	            String queryInsert = "INSERT INTO veterinario"
	                                + "(nombre, apellido, telefono, edad, especialidad) "
	                                + "VALUES (?, ?, ?, ?, ?);";
	            try {
	                sentenciaParametrizada = connection.prepareStatement(queryInsert);
	                sentenciaParametrizada.setString(1, veterinario.getNombre());
	                sentenciaParametrizada.setString(2, veterinario.getApellido());
	                sentenciaParametrizada.setString(3, veterinario.getTelefono());
	                sentenciaParametrizada.setInt(4, veterinario.getEdad());
	                sentenciaParametrizada.setString(5, veterinario.getEspecialidad());
	                
	                sentenciaParametrizada.executeUpdate();
	                return true;
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
	        return false;
	    }
	    public ArrayList<Veterinario> consultarVeterinarios() {
	        ArrayList<Veterinario> veterinarios = new ArrayList<>();
	        String querySelect = "SELECT * FROM veterinario ORDER BY idVeterinario;";
	        try {
	            sentencia = connection.createStatement();
	            resultSet = sentencia.executeQuery(querySelect);
	            while(resultSet.next()) {
	            	veterinarios.add(new Veterinario(resultSet.getInt("idVeterinario"),
	                        resultSet.getString("nombre"),
	                        resultSet.getString("apellido"),
	                        resultSet.getString("telefono"),
	                        resultSet.getInt("edad"),
	                        resultSet.getString("especialidad")));

	                                                
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return veterinarios;
	    }
	    
	  //Eliminar veterinario
	    public boolean eliminar(int idVeterinario) {
	        String queryDelete = "DELETE FROM veterinario WHERE idVeterinario = ?";
	        try {
	            sentenciaParametrizada = connection.prepareStatement(queryDelete);
	            sentenciaParametrizada.setInt(1, idVeterinario);
	            sentenciaParametrizada.executeUpdate();
	            return true;
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return false;
	    }
	 

	    // Editar veterinario
	    public boolean editar(Veterinario veterinario) {
	        if (veterinario != null) {
	            String queryUpdate = "UPDATE veterinario SET nombre = ?, apellido = ?, telefono = ?, edad = ?, especialidad = ? WHERE idVeterinario = ?";
	            try {
	                sentenciaParametrizada = connection.prepareStatement(queryUpdate);
	                sentenciaParametrizada.setString(1, veterinario.getNombre());
	                sentenciaParametrizada.setString(2, veterinario.getApellido());
	                sentenciaParametrizada.setString(3, veterinario.getTelefono());
	                sentenciaParametrizada.setInt(4, veterinario.getEdad());
	                sentenciaParametrizada.setString(5, veterinario.getEspecialidad());
	                sentenciaParametrizada.setInt(6, veterinario.getIdVeterinario());
	                sentenciaParametrizada.executeUpdate();
	                return true;
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
	        return false;
	    }

	    public Veterinario obtenerPorId(int idVeterinario) {
	        String query = "SELECT * FROM veterinario WHERE idVeterinario = ?";
	        try {
	            sentenciaParametrizada = connection.prepareStatement(query);
	            sentenciaParametrizada.setInt(1, idVeterinario);
	            resultSet = sentenciaParametrizada.executeQuery();
	            
	            if (resultSet.next()) {
	                return new Veterinario(
	                    resultSet.getInt("idVeterinario"),
	                    resultSet.getString("nombre"),
	                    resultSet.getString("apellido"),
	                    resultSet.getString("telefono"),
	                    resultSet.getInt("edad"),
	                    resultSet.getString("especialidad")
	                );
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return null; // si no se encuentra el veterinario
	    }
	    
	 // Consultar veterinario por su nombre
	    public Veterinario consultarPorNombre(String nombre) {
	        Veterinario veterinario = null;
	        String querySelect = "SELECT * FROM veterinario WHERE LOWER(nombre) = LOWER(?);";  
	        try {
	            sentenciaParametrizada = connection.prepareStatement(querySelect);
	            sentenciaParametrizada.setString(1, nombre.trim());  
	            resultSet = sentenciaParametrizada.executeQuery();  
	            
	            if (resultSet.next()) {  
	                veterinario = new Veterinario(
	                    resultSet.getInt("idVeterinario"),
	                    resultSet.getString("nombre"),
	                    resultSet.getString("apellido"),
	                    resultSet.getString("telefono"),
	                    resultSet.getInt("edad"),
	                    resultSet.getString("especialidad"));
	            }
	        } catch (SQLException e) {
	   
	            System.err.println("Error al consultar veterinario por nombre: " + e.getMessage());
	            e.printStackTrace();
	        }
	        return veterinario; 
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

