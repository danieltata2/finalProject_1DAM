package modelo;

import java.time.LocalDate;
import java.time.LocalTime;

import dao.AnimalDAO;
import dao.CitaDAO;
import dao.ClienteDAO;
import dao.VeterinarioDAO;

public class Principal {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		AnimalDAO animalDAO = null;
		CitaDAO citaDAO = null;
		ClienteDAO clienteDAO = null;
		VeterinarioDAO veterinarioDAO = null;
//ejecutar las tablas
		
		veterinarioDAO = new VeterinarioDAO();
		veterinarioDAO.createTable();
		veterinarioDAO.getConexion().cerrarConexion();
		
		clienteDAO = new ClienteDAO();
		clienteDAO.createTable();
		clienteDAO.getConexion().cerrarConexion();
		
		animalDAO = new AnimalDAO();
		animalDAO.createTable();
		animalDAO.getConexion().cerrarConexion();

		citaDAO = new CitaDAO();
		citaDAO.createTable();
		citaDAO.getConexion().cerrarConexion();
		//insertar 
		
		/*Cliente cliente = new Cliente ("Maria","Gomez",22,"zzzzz","123456789","sada@gmail.com");
		Animal animal = new Animal ("Tito","Canino","Hembra",cliente);
		
		clienteDAO = new ClienteDAO();
		clienteDAO.insertar(cliente);
		clienteDAO.getConexion().cerrarConexion();
		
		Veterinario veterinario = new Veterinario ("milenka","perez","987654321",34,"milie@gmail.com");
		Animal animaal = new Animal ("Tito","Canino","Hembra",cliente);
		
		veterinarioDAO = new VeterinarioDAO();
		veterinarioDAO.insertar(veterinario);
		veterinarioDAO.getConexion().cerrarConexion();

		
		LocalDate fecha = LocalDate.of(2025, 5, 10);     
		LocalTime hora = LocalTime.of(14, 30);           

		Cita cita = new Cita (animal,cliente,veterinario,fecha,hora);
		Animal animall = new Animal ("Tito","Canino","Hembra",cliente);
		
		citaDAO = new CitaDAO();
		citaDAO.insertar(cita);
		citaDAO.getConexion().cerrarConexion();
	}*/
	}
}
