package modelo;

public class Animal {
    private static int contador = 0;
    private int idAnimal;
    private String nombre;
    private String especie;
    private String genero;
    private int idCliente;
    
    public Animal() {
    	
    }
    public Animal(int idAnimal, String nombre,String especie, String genero) {
   	 this.idAnimal = idAnimal;
        this.nombre = nombre;
        this.especie = especie;
        this.genero = genero;
  
   }
    

    public Animal(String nombre, String especie, String genero, int idCliente) {
        this.idAnimal = ++contador;
        this.nombre = nombre;
        this.especie = especie;
        this.genero = genero;
        this.idCliente = idCliente;
    }
    
    
    public Animal(int idAnimal, String nombre,String especie, String genero, int idCliente) {
    	 this.idAnimal = idAnimal;
         this.nombre = nombre;
         this.especie = especie;
         this.genero = genero;
         this.idCliente = idCliente;
    }

    public int getIdAnimal() {
        return idAnimal;
    }

    public void setIdAnimal(int idAnimal) {
		this.idAnimal = idAnimal;
	}

	public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEspecie() {
        return especie;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String toString() {
        return "INFORMACIÓN DEL ANIMAL:\n" +
               "\tID: " + idAnimal + "\n" +
               "\tNombre: " + nombre + "\n" +
               "\tEspecie: " + especie + "\n" +
               "\tGénero: " + genero + "\n" +
               "\tIdCliente: " + idCliente + "\n";
    }
}
