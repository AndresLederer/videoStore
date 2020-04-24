package videoStore;

public class Cliente {
	//atributos
	private String nombre;
	private String telefono;
	private String direccion;
	private int cantAlquileres;
	
	//setters
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public void setCantAlquileres(int cantAlquileres) {
		this.cantAlquileres = cantAlquileres;
	}
	
	//getters
	public String getNombre() {
		return nombre;
	}
	public String getTelefono() {
		return telefono;
	}
	public String getDireccion() {
		return direccion;
	}
	public int getCantAlquileres() {
		return cantAlquileres;
	}
	
	//constructor
	public Cliente (String nombre,String telefono,String direccion,int cantAlquileres) {
		setNombre(nombre);
		setTelefono(telefono);
		setDireccion(direccion);
		setCantAlquileres(cantAlquileres);
	}
	
	//metodos
	public void alquilarPelicula() {
		
	}
	
	@Override
	public String toString() {
		String rta = String.format("Cliente: %s\nTelefono: %s\nDirección: %s\nAlquileres totales: %d\n",getNombre(),getTelefono(),getDireccion(),getCantAlquileres());
		return rta;
	}
}

















