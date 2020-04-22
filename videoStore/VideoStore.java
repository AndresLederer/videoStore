package videoStore;

import java.util.*;

public class VideoStore {
	private static ArrayList <Pelicula> libreriaPeliculas;
	private static ArrayList <Cliente> agendaClientes ;
	private static ArrayList <BoletaPrestamo> boletasPrestamo;
	private static Scanner scn = new Scanner(System.in);;
	
	
	public static void main(String [] args) {
		//instancio Peliculas
		Pelicula p1 = new Pelicula("Titanic",130,"Drama",1998,"Estados Unidos","Basada en hechos reales. El undimiento del Titanic.","G",5,5);
		Pelicula p2 = new Pelicula("Harry Potter 1",120,"Acci�n",2002,"Estados Unidos","La historia del ni�o que vivi�","PG-13",8,8);
		Pelicula p3 = new Pelicula("Planet Earth",110,"Documental",2016,"United Kingdom","Un detallado documental de la BBC sobre la crisis clim�tica","G",12,12);
		Pelicula p4 = new Pelicula("Narnia",90,"Aventura",2012,"Estados Unidos","La aventura de cuatro hermanos en un mundo fant�stico","PG-17",1,1);
		//instancio libreria de peliculas 
		libreriaPeliculas = new ArrayList <Pelicula>();
		//cargo peliculas a la libreria
		libreriaPeliculas.add(p1);
		libreriaPeliculas.add(p2);
		libreriaPeliculas.add(p3);
		libreriaPeliculas.add(p4);
		
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
		
		//instancio arraylist de boletas
		boletasPrestamo = new ArrayList <BoletaPrestamo>();
		
		mainMenu();
		
		verAlquileresVigentes();
	}
	
	//OTROS METODOS ------------------------------------------------------------------------------------------------------------------------------------
	
//	//corrobora que una pelicula este en la libreriaPeliculas
//	private static boolean buscarPelicula(Pelicula peliculaBuscada) {
//		boolean existe = false;
//		for(Pelicula p : libreriaPeliculas) {
//			if(p.equals(peliculaBuscada)) existe = true;
//		}
//		return existe;
//	}
	
	//busca pelicula por nombre -- devuelve true si esta en la libreria, de lo contrario devuelve false
	private static boolean buscarPeliculaPorNombre(String tituloBuscado) {
		boolean existe = false;
		for(Pelicula p : libreriaPeliculas) {
			if(p.getTitulo().equals(tituloBuscado)) {
				existe = true;
			}
		}
		return existe;
	}
	
	//chequea que haya copias disponibles de una peli -- devuelve true si hay stock, false de lo contrario
	private static boolean checkCopiasDisponibles(String peliculaBuscada) {
		boolean stock = false;
		for(Pelicula p : libreriaPeliculas) {
			if(p.getTitulo().equals(peliculaBuscada) && p.getCopiasDisponibles()>0) {
				stock = true;
			}
		}
		return stock;
	}
	
	//chequea si existe un cliente con el nombre pasado por parametro -- si existe devuelve true, de lo contrario devuelve false
	private static boolean checkClienteExistente(String nombreCliente) {
		boolean clienteExiste = false;
		for(Cliente c : agendaClientes) {
			if(c.getNombre().equals(nombreCliente)) clienteExiste = true;
		}
		return clienteExiste;
	}
	
	//muestra por cmd las boletas de los alquileres vigentes
	private static void verAlquileresVigentes() {
		for(BoletaPrestamo b : boletasPrestamo) {
			System.out.println(b.toString());
		}
	}

	//inicia aqluiler de pelicula
	private static void alquilarPelicula() {
		System.out.printf("Indique el titulo de la pelicula: "); //recibo el titulo de la pelicula que se quiere alquilar
		String tituloBuscado = scn.nextLine(); 
		
		//corroboro q sea una pelicula existente en mi libreria de peliculas
		if(buscarPeliculaPorNombre(capitalizeEachWord(tituloBuscado))) { 
			System.out.println("La pelicula buscada existe en el VideoStore");
			//la pelicula existe en mi libreria, me fijo que haya alguna disponible para alquilar
			if(checkCopiasDisponibles(capitalizeEachWord(tituloBuscado))) { 
				System.out.println("Hay copias disponibles para ser alquiladas");
				//la pelicula esta disponible para se alquilada -- procedo a identificar al cliente
				System.out.printf("Nombre y apellido del cliente: ");
				String nombreCliente = scn.nextLine();
				//chequedo si es un cliente regular
				if(checkClienteExistente(capitalizeEachWord(nombreCliente))) { 
					//es un cliente regular => le hago su boleta y la agrego al AList de Boletas de Prestamos
					BoletaPrestamo boletaNueva = creaBoletaNueva(capitalizeEachWord(tituloBuscado),capitalizeEachWord(nombreCliente));
					boletasPrestamo.add(boletaNueva);
				}else { //si se trata de un clienten nuevo
					//creo cliente nuevo y lo agrego a la agenda de clientes
					Cliente clienteNuevo = creaClienteNuevo();
					agendaClientes.add(clienteNuevo);
					//creo una boleta y la agrego al AList de Boletas de Prestamos
					BoletaPrestamo boletaNueva = creaBoletaNueva(capitalizeEachWord(tituloBuscado),capitalizeEachWord(nombreCliente));
					boletasPrestamo.add(boletaNueva);
				}
			//la pelicula existe en mi libreria  pero no tengo ninguna para alquilar -- vuelva mas tarde :/
			}else {
				System.out.println("En este momento no hay copias disponibles para ser alquiladas");
			}
		}else { //la pelicula no existe en mi libreria -- vaya a otra tienda :(
			System.out.println("La pelicula buscada no existe en el VideoStore");
		}

	}
	
	private static Cliente creaClienteNuevo() {
		System.out.println("NUEVO CLIENTE");
		System.out.printf("Nombre y apellid: ");
		String nombreClienteNuevo = scn.nextLine();
		System.out.printf("Telefono: ");
		String telClienteNuevoString = scn.nextLine();
		System.out.printf("Direcci�n: ");
		String direcClienteNuevo = scn.nextLine();
		Cliente clienteNuevo = new Cliente(nombreClienteNuevo,telClienteNuevoString,direcClienteNuevo);
		return clienteNuevo;
	}
	
	private static BoletaPrestamo creaBoletaNueva(String peliculaAlquilada,String nombreCliente) {
		BoletaPrestamo nuevaBoleta = new BoletaPrestamo(peliculaAlquilada,nombreCliente);
		return nuevaBoleta;
	}
	
	
	//OTROS MEOTODOS AUXILIARES ---------------------------------------------------------------------------------------------------------------------------
	
	//mainMenu
	private static void mainMenu() {
		int opcion;
		do {
			System.out.println("<<< VIDEO STORE >>>\n\n");
			System.out.println("[1] Alquilar pelicula");
			System.out.println("[0] Salir");
			System.out.printf("\n\nIndique una opci�n: "); 
			opcion = scn.nextInt();
		} while(opcion != 0 || opcion < 0 || opcion > 1);
		switch(opcion) {
			case 0:
				System.out.println("Ha finalizado la ejecuci�n");
			break;
			case 1:
				alquilarPelicula();
			break;
			
		}
//		System.exit(0);
	}
	
	//recibe un string y lo devuelve con la primera letra de cada palabra en mayuscula
	private static String capitalizeEachWord(String string) {
		//meto la frase en un array
		String [] palabras = string.split(" ");
		//capitalizo cada palabra
		int i;
		for(i=0;i<palabras.length;i++) {
			palabras[i] = palabras[i].substring(0, 1).toUpperCase() + palabras[i].substring(1).toLowerCase();
		}
		string = String.join(" ",palabras);
		return string;
	}
}
