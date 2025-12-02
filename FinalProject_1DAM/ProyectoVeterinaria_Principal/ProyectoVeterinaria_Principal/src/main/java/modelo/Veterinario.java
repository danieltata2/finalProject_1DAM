package modelo;

public class Veterinario {
	
	private int idVeterinario;
	private String nombre;
	private String apellido;
	private String telefono;
	private int edad;
	private String especialidad;
	private static int contador = 0;

	public Veterinario() {
		
	}
	
	
	public Veterinario(String nombre, String apellido, String telefono, int edad, String especialidad) {
		
		this.idVeterinario=contador++;
		this.nombre = nombre;
		this.apellido = apellido;
		this.telefono = telefono;
		this.edad = edad;
		this.especialidad = especialidad;

		
	}

	public Veterinario(int idVeterinario, String nombre, String apellido, String telefono, int edad, String especialidad) {
		
		this.idVeterinario = idVeterinario;
		this.nombre = nombre;
		this.apellido = apellido;
		this.telefono = telefono;
		this.edad = edad;
		this.especialidad = especialidad;
		
	}

	public int getIdVeterinario() {
		return idVeterinario;
	}

	

	public void setIdVeterinario(int idVeterinario) {
		this.idVeterinario = idVeterinario;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	public String getEspecialidad() {
		return especialidad;
	}

	public void setEspecialidad(String especialidad) {
		this.especialidad = especialidad;
	}

	
	
	
	public String toString() {
		return "INFORMACION DEL VETERINARIO: \n"
				+ "\tID: "+this.idVeterinario+"\n"
				+ "\tNombre: "+this.nombre+"\n"
				+ "\tApellido: "+this.apellido+"\n"
				+ "\tTelefono: "+this.telefono+" \n"
				+ "\tEdad: "+this.edad+" años\n"
				+ "\tEspecialidad: "+this.especialidad+"\n";
	}


}
