package videoStore;

//import java.util.UUID;

public class Pelicula {
	//atributos
//	private final UUID  uuid = UUID.randomUUID();
	private final String titulo;
	private final int duracion;
	private final String genero;
	private final int anioEstreno;
	private final String paisOrigen;
	private final String descripcion;
	private final String clasificacionAudiencia;
	private final int cantCopias;
	private int copiasDisponibles;
	
	//setters
	public void setCopiasDisponibles(int copiasDisponibles){
		this.copiasDisponibles = copiasDisponibles;
	}
	
	//getters
//	public UUID getUuid() {
//		return uuid;
//	}
	
	public String getTitulo() {
		return titulo;
	}
	public int getDuracion() {
		return duracion;
	}
	public String getGenero() {
		return genero;
	}
	public int getAnioEstreno() {
		return anioEstreno;
	}
	public String getPaisOrigen() {
		return paisOrigen;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public String getClasificacionAudiencia() {
		return clasificacionAudiencia;
	}
	public int getCantCopias() {
		return cantCopias;
	}
	public int getCopiasDisponibles() {
		return copiasDisponibles;
	}
	
	//constructor
	public Pelicula(String titulo,int duracion,String genero,int anioEstreno,String paisOrigen,String descripcion,String clasificacionAudiencia,int cantCopias,int copiasDisponibles) {
		this.titulo = titulo;
		this.duracion = duracion;
		this.genero = genero;
		this.anioEstreno = anioEstreno;
		this.paisOrigen = paisOrigen;
		this.descripcion = descripcion;
		this.clasificacionAudiencia = clasificacionAudiencia;
		this.cantCopias = cantCopias;
		this.copiasDisponibles = copiasDisponibles;
	}
	
	//@Override de .equals() => atributo determinante TITULO de Pelicula
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Pelicula) {
			Pelicula otraPelicula = (Pelicula) obj;
			//seran iguales si ambas tienen el mismo TITULO y misma DESCRIPCION
			if(this.getTitulo().equals(otraPelicula.getTitulo())){
				return true;
			}else {
				return false;
			}
		}else {
			return false;
		}
	}
	
	//@Override de .toString()
	@Override
	public String toString() {
		String rta = String.format("Pelicula: %s\nDuracion: %d mins.\nGenero: %s\nEstreno: %d\nOrigen: %s\nDescripción: %s\nAudiencia: %s\nCopias totales: %d\nCopias disponibles: %d",getTitulo(),getDuracion(),getGenero(),getAnioEstreno(),getPaisOrigen(),getDescripcion(),getClasificacionAudiencia(),getCantCopias(),getCopiasDisponibles());
		return rta;
	}
	
	
	
}



















