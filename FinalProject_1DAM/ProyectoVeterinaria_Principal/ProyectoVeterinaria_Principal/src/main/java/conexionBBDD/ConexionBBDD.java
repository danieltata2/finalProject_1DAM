package conexionBBDD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBBDD {
	private static final String DRIVER = "org.postgresql.Driver";
	private static final String URL = "jdbc:postgresql://localhost:5432/proyectofinal";
	private static final String USUARIO = "postgres";
	private static final String PASSWORD = "1234";
	private Connection conexion = null;
	
	static{
		try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Connection conectar() {
		try {
			conexion = DriverManager.getConnection(URL, USUARIO, PASSWORD);
			System.out.println("Conexión a BDDD OK");
		} catch (SQLException e) {
			System.err.println("Error en la conexión a BBDD");
			e.printStackTrace();
		}
		return conexion;
	}
	
	public void cerrarConexion() {
		try {
			conexion.close();
			System.out.println("¡¡Conexión con BBDD cerrada!!");
		} catch (SQLException e) {
			System.err.println("Error al cerrar la BBDD");
			e.printStackTrace();
		}
	}

}
