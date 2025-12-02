package dao;



import java.sql.Connection;

import java.sql.PreparedStatement;

import java.sql.ResultSet;

import java.sql.SQLException;



import conexionBBDD.ConexionBBDD;

import modelo.Usuario;



public class UsuarioDAO {



	private ConexionBBDD conexion = new ConexionBBDD();

	private Connection connection;



	public UsuarioDAO() {

		this.connection = conexion.conectar();

	}



	// REGISTRO

	public boolean registrar(Usuario usuario) {

		String queryRegister = "INSERT INTO usuarios (nombre, correo, clave) VALUES (?, ?, ?)";

		try (PreparedStatement sentencia = connection.prepareStatement(queryRegister)) {
			sentencia.setString(1, usuario.getNombre());
			sentencia.setString(2, usuario.getCorreo());
			sentencia.setString(3, usuario.getClave());
			sentencia.executeUpdate();
			return true;
		} catch (SQLException e) {
			System.err.println("Error al registrar usuario: " + e.getMessage());
			return false;
		}
	}
	// LOGIN
	public Usuario login(String nombre, String correo, String clave) {
		String queryLogin = "SELECT * FROM usuarios WHERE correo = ? AND clave = ?";

		try (PreparedStatement sentencia = connection.prepareStatement(queryLogin)) {
			sentencia.setString(1, correo);
			sentencia.setString(2, clave);

			ResultSet rs = sentencia.executeQuery();
			if (rs.next()) {
				Usuario usuario = new Usuario();
				usuario.setIdUsuario(rs.getInt("id")); 
				usuario.setNombre(rs.getString("nombre"));
				usuario.setCorreo(rs.getString("correo"));
				usuario.setClave(rs.getString("clave"));
				return usuario;
			}
		} catch (SQLException e) {
			System.err.println("Error al iniciar sesión: " + e.getMessage());
		}
		return null;

	}

}

