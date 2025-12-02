package modelo;

public class Usuario {
	
	private int idUsuario;
	private static int contador =0;
	private String nombre;
	private String correo;
	private String clave;
	
	public Usuario() {
		
	}
	
	public Usuario(String nombre, String correo, String clave) {

		contador++;
		this.idUsuario = contador;
		this.nombre = nombre;
		this.correo = correo;
		this.clave = clave;
	}
	
	public Usuario(int idUsuario, String nombre, String correo, String clave) {

		this.idUsuario = idUsuario;
		this.nombre = nombre;
		this.correo = correo;
		this.clave = clave;
	}
	
	public int getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getCorreo() {
		return correo;
	}
	public void setCorreo(String correo) {
		this.correo = correo;
	}
	public String getClave() {
		return clave;
	}
	public void setClave(String clave) {
		this.clave = clave;
	}

}
