package videoStore;

import java.util.*;

public class VideoStore {
	private static ArrayList <Pelicula> libreriaPeliculas;
	private static ArrayList <Cliente> agendaClientes ;
	private static Scanner scn;
	
	
	public static void main(String [] args) {
		//instancio Clientes
		Cliente c1 = new Cliente("Jael Stainer","+20 112 457852","Ganzngen Street 17");
		Cliente c2 = new Cliente("Anais Stainer","+20 112 159753","Larrea Street 230");
		Cliente c3 = new Cliente("SeHee Young","+54 223 5395183","Whiteladies Road 24");
		Cliente c4 = new Cliente("Louisa Ximenez","+32 20 6147455","Pembrook Street 90");
		//instancio agenda de clientes
		agendaClientes = new ArrayList <Cliente>();
		//cargo clientes a la agenda
		agendaClientes.add(c1);
		agendaClientes.add(c2);
		agendaClientes.add(c3);
		agendaClientes.add(c4);
		
		
		//instancio Peliculas
		Pelicula p1 = new Pelicula("Titanic",130,"Drama",1998,"Estados Unidos","Basada en hechos reales. El undimiento del Titanic.","G",5,5);
		Pelicula p2 = new Pelicula("Harry Potter 1",120,"Acción",2002,"Estados Unidos","La historia del niño que vivió","PG-13",8,8);
		Pelicula p3 = new Pelicula("Planet Earth",110,"Documental",2016,"United Kingdom","Un detallado documental de la BBC sobre la crisis climática","G",12,12);
		//instancio libreria de peliculas 
		libreriaPeliculas = new ArrayList <Pelicula>();
		//cargo peliculas a la libreria
		libreriaPeliculas.add(p1);
		libreriaPeliculas.add(p2);
		libreriaPeliculas.add(p3);
		
		scn = new Scanner(System.in);
		System.out.println("Indique el titulo de la pelicula: ");
		String tituloBuscado = scn.nextLine();

		if(buscarPeliculaPorNombre(tituloBuscado)) {
			System.out.println("La pelicula buscada existe en el VideoStore");
		}else {
			System.out.println("La pelicula buscada no existe en el VideoStore");
		}
	}
	
//	//corrobora que una pelicula este en la libreriaPeliculas
//	private static boolean buscarPelicula(Pelicula peliculaBuscada) {
//		boolean existe = false;
//		for(Pelicula p : libreriaPeliculas) {
//			if(p.equals(peliculaBuscada)) existe = true;
//		}
//		return existe;
//	}
	
	private static boolean buscarPeliculaPorNombre(String tituloBuscado) {
		boolean existe = false;
		for(Pelicula p : libreriaPeliculas) {
			if(p.getTitulo().equals(tituloBuscado)) {
				existe = true;
			}
		}
		return existe;
	}
}
