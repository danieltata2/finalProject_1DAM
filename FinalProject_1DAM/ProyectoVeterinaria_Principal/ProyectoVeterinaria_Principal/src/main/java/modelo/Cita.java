package modelo;

import java.time.LocalDate;

import java.time.LocalTime;

public class Cita {
	private static int contador = 0;
	private int idCita;
	private int idAnimal;
	private int idCliente;
	private int idVeterinario;
	private LocalDate fecha;
	private LocalTime hora;

	
	public Cita() {
		
	}
	
	
	public Cita(int idAnimal,int idCliente, int idVeterinario, LocalDate fecha, LocalTime hora) {

		contador++;
		this.idCita = contador;
		this.idAnimal = idAnimal;
		this.idCliente = idCliente;
		this.idVeterinario= idVeterinario;
		this.fecha = fecha;
		this.hora = hora;

	}

	public Cita(int idCita,int idAnimal, int idCliente, int idVeterinario, LocalDate fecha, LocalTime hora) {

		this.idCita = idCita;
		this. idAnimal =  idAnimal;
		this.idCliente = idCliente;
		this.idVeterinario = idVeterinario;
		this.fecha = fecha;
		this.hora = hora;
	}

	public int getIdCita() {
		return idCita;
	}

	public void setIdCita(int idCita) {
		this.idCita = idCita;
	}


	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	public LocalTime getHora() {
		return hora;
	}

	public void setHora(LocalTime hora) {
		this.hora = hora;
	}
	
	

public int getIdAnimal() {
		return idAnimal;
	}


	public void setIdAnimal(int idAnimal) {
		this.idAnimal = idAnimal;
	}


	public int getIdCliente() {
		return idCliente;
	}


	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}


	public int getIdVeterinario() {
		return idVeterinario;
	}


	public void setIdVeterinario(int idVeterinario) {
		this.idVeterinario = idVeterinario;
	}




}