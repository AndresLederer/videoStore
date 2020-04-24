package videoStore;

import java.util.Calendar;
import java.util.Date;
import java.text.*;

public class BoletaPrestamo {
	//atributos
	private String nombrePelicula;
	private String nombreCliente;
	private final Date fechaAlquiler; 
	private Calendar calendario;
	private Date fechaDevolucion;
	private SimpleDateFormat fechaPers = new SimpleDateFormat("dd/MM/yyyy");
	
	//setters
	public void setNombrePelicula(String nombrePelicula) {
		this.nombrePelicula = nombrePelicula;
	}
	public void setNombreCliente(String nombreCliente) {
		this.nombreCliente = nombreCliente;
	}
//	public void setFechaAlquiler(Date fechaAlquiler) {
//		this.fechaAlquiler = fechaAlquiler;
//	}
//	public void setFechaDevolucion(Date fechaDevolucion) {
//		this.fechaDevolucion = fechaDevolucion;
//	}
	
	//getters
	public String getNombrePelicula() {
		return nombrePelicula;
	}
	public String getNombreCliente() {
		return nombreCliente;
	}
	public Date getFechaAlquiler() {
		return fechaAlquiler;
	}
	public Date getFechaDevolucion() {
		return fechaDevolucion;
	}
	
	//Constructor
	public BoletaPrestamo(String nombrePelicula,String nombreCliente) {
		setNombrePelicula(nombrePelicula);
		setNombreCliente(nombreCliente);
		
		fechaAlquiler = new Date(); //la fecha de alquiler sera igual a la FECHA ACTUAL
		calendario = Calendar.getInstance();
		calendario.setTime(fechaAlquiler); //indico la fecha de alquiler en el Calendario
		calendario.add(Calendar.DATE,3); //avanzo 3 dias en el calendario
		fechaDevolucion = calendario.getTime();//indico que la fecha de devolucion sera 3 dias desp de la fecha de alquiler
	}
	
	//metodos
	
	//@Override de .toString()
	@Override
	public String toString() {
		String rta = String.format("Pelicula alquilada: %s\nAlquilada a: %s\nFecha de alquiler: %s\nFecha de devolución: %s",getNombrePelicula(),getNombreCliente(),fechaPers.format(getFechaAlquiler()),fechaPers.format(getFechaDevolucion()));
		return rta;
	}
	
	
}





















