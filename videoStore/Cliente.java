package videoStore;

public class Cliente {
	//atributos
	private String nombre;
	private String telefono;
	private String direccion;
	
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
	
	//constructor
	public Cliente (String nombre,String telefono,String direccion) {
		setNombre(nombre);
		setTelefono(telefono);
		setDireccion(direccion);
	}
	
	//metodos
	public void alquilarPelicula() {
		
	}
	
	@Override
	public String toString() {
		String rta = String.format("Cliente: %s\nTelefono: %s\nDirección: %s\n",getNombre(),getTelefono(),getDireccion());
		return rta;
	}
}

















