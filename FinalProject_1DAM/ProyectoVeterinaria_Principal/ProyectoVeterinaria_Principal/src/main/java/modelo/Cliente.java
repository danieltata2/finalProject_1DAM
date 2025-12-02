package modelo;

public class Cliente {
    private int idCliente;
    private String nombre;
    private String apellido;
    private int edad;
    private String direccion;
    private String telefono;
    private String correo;
    private static int contador = 0;

    
    public Cliente() {
    	
    }
 
    public Cliente(int idCliente, String nombre, String apellido, int edad, String direccion, String telefono, String correo) {
        this.idCliente = idCliente;
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
        this.direccion = direccion;
        this.telefono = telefono;
        this.correo = correo;
    }
    public Cliente(String nombre, String apellido, int edad, String direccion, String telefono, String correo) {
		super();
		this.idCliente = contador++;
		this.nombre = nombre;
		this.apellido = apellido;
		this.edad = edad;
		this.direccion = direccion;
		this.telefono = telefono;
		this.correo = correo;
	}

	public int getIdCliente() {
        return idCliente;
    }
	

    public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
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

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
       this.edad= edad;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        if (telefono.matches("\\d{9}")) {
            this.telefono = telefono;
        } else {
            throw new IllegalArgumentException("El teléfono debe tener 9 dígitos.");
        }
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String toString() {
        return "INFORMACIÓN DEL CLIENTE:\n" +
               "\tID: " + idCliente + "\n" +
               "\tNombre: " + nombre + "\n" +
               "\tApellido: " + apellido + "\n" +
               "\tEdad: " + edad + " años\n" +
               "\tDirección: " + direccion + "\n" +
               "\tTeléfono: " + telefono + "\n" +
               "\tCorreo: " + correo + "\n";
    }
}
