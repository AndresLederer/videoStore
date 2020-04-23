package videoStore;

import java.text.SimpleDateFormat;
import java.util.*;
//import java.io.*;

public class VideoStore {
	private static ArrayList <Pelicula> libreriaPeliculas;
	private static ArrayList <Cliente> agendaClientes ;
	private static ArrayList <BoletaPrestamo> boletasPrestamo;
	private static Scanner scn;// = new Scanner(System.in);
	
	private static Date actualDate = new Date();
	private static SimpleDateFormat formatoPers = new SimpleDateFormat("dd/MM/yyyy");
	
	public static void main(String [] args) {
		//instancio Peliculas
		Pelicula p1 = new Pelicula("Titanic",130,"Drama",1998,"Estados Unidos","Basada en hechos reales. El undimiento del Titanic.","G",5,5);
		Pelicula p2 = new Pelicula("Harry Potter 1",120,"Acción",2002,"Estados Unidos","La historia del niño que vivió","PG-13",8,8);
		Pelicula p3 = new Pelicula("Planet Earth",110,"Documental",2016,"United Kingdom","Un detallado documental de la BBC sobre la crisis climática","G",12,12);
		Pelicula p4 = new Pelicula("Narnia",90,"Aventura",2012,"Estados Unidos","La aventura de cuatro hermanos en un mundo fantástico","PG-17",1,1);
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
		Cliente c3 = new Cliente("Tim Young","+54 223 5395183","Whiteladies Road 24");
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
		
		//menu principal del programa
		mainMenu();
		
	}
	
	// METODOS ------------------------------------------------------------------------------------------------------------------------------------
	
	//mainMenu
	private static void mainMenu() {
		scn = new Scanner(System.in);
		int opcion;
		do {
			do {
//				clearConsole();
				System.out.println(formatoPers.format(actualDate));
				System.out.println("<<< VIDEO STORE >>>\n\n");
				System.out.println("[1] Alquilar pelicula");
				System.out.println("[2] Devoluciones del día");
				System.out.println("[3] Alquileres vigentes");
				System.out.println("[0] Salir");
				System.out.printf("\n\nIndique una opción: "); 
				opcion = scn.nextInt();
			} while(opcion < 0 || opcion > 3);
			switch(opcion) {
			case 0:
				System.out.println("Ha finalizado la ejecución");
//				System.exit(0);
				break;
			case 1:
				if(alquilarPelicula()) 
					System.out.println("Pelicula alquilada");
				else
					System.out.println("Pelicula no alquilada");
				break;
			case 2:
				verDevolucionesDelDia();
				break;
			case 3:
				verAlquileresVigentes();
				break;
			}
		} while(opcion != 0);
	}
	
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
		System.out.println("\n[ALQUILERES VIGENTES]");
		int alquileresVigentes = 0;
		for(BoletaPrestamo b : boletasPrestamo) {
			if(b.getFechaDevolucion().after(actualDate) || b.getFechaDevolucion().equals(actualDate)) {
				System.out.println("--------------------------------------");
				System.out.println(b.toString());
				System.out.println("--------------------------------------");
				alquileresVigentes++;
			}
		}
		System.out.println("\nALQUILERES VIGENTES TOTALES: "+alquileresVigentes+"\n");
	}

	//inicia aqluiler de pelicula
	private static boolean alquilarPelicula() {
		boolean peliculaAlquilada; //check de que efectivamente se haya alquilado la pelicula
		
		System.out.printf("Indique el titulo de la pelicula: "); //recibo el titulo de la pelicula que se quiere alquilar
		scn = new Scanner(System.in);
		String tituloBuscado = scn.nextLine(); 
		
		//corroboro q sea una pelicula existente en mi libreria de peliculas
		if(buscarPeliculaPorNombre(capitalizeEachWord(tituloBuscado))) { 
			System.out.println("La pelicula buscada existe en el VideoStore");
			//la pelicula existe en mi libreria, me fijo que haya alguna disponible para alquilar
			if(checkCopiasDisponibles(capitalizeEachWord(tituloBuscado))) { 
				peliculaAlquilada = true; //la pelicula se va a alquilar
				//reduzco en 1 la cantidad de peliculas disponibles para dicho titulo
				reduceCantDisponible(capitalizeEachWord(tituloBuscado));
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
				peliculaAlquilada = false; 
				System.out.println("En este momento no hay copias disponibles para ser alquiladas");
			}
		}else { //la pelicula no existe en mi libreria -- vaya a otra tienda :(
			peliculaAlquilada = false;
			System.out.println("La pelicula buscada no existe en el VideoStore");
		}
		
		return peliculaAlquilada;
	}
	
	private static void reduceCantDisponible(String peliculaAlquilada) {
		for(Pelicula p: libreriaPeliculas) {
			if(p.getTitulo().equals(peliculaAlquilada))
				p.setCopiasDisponibles(p.getCopiasDisponibles()-1);
		}
	}
	
	//recibe datos de un cliente por Scanner y devuelve un Cliente nuevo
	private static Cliente creaClienteNuevo() {
		System.out.println("NUEVO CLIENTE");
		System.out.printf("Nombre y apellid: ");
		String nombreClienteNuevo = scn.nextLine();
		System.out.printf("Telefono: ");
		String telClienteNuevoString = scn.nextLine();
		System.out.printf("Dirección: ");
		String direcClienteNuevo = scn.nextLine();
		Cliente clienteNuevo = new Cliente(nombreClienteNuevo,telClienteNuevoString,direcClienteNuevo);
		return clienteNuevo;
	}
	
	//recibe por parametros datos y devuelve una BoletaPrestamo nueva
	private static BoletaPrestamo creaBoletaNueva(String peliculaAlquilada,String nombreCliente) {
		BoletaPrestamo nuevaBoleta = new BoletaPrestamo(peliculaAlquilada,nombreCliente);
		return nuevaBoleta;
	}
	
	//muestra por cmd las devoluciones del dia actual
	private static void verDevolucionesDelDia() {
		System.out.println("\n[DEVOLUCIONES DEL DIA]");
		int devoluciones = 0;
		for(BoletaPrestamo bp : boletasPrestamo) {
			if(bp.getFechaDevolucion().equals(actualDate)) {
				System.out.println("--------------------------------------");
				System.out.println(bp.toString());
				System.out.println("--------------------------------------");
				devoluciones++;
			}
		}
		System.out.println("DEVOLUCIONES TOTALES DEL DIA: "+devoluciones);
	}
	
	//OTROS MEOTODOS AUXILIARES ---------------------------------------------------------------------------------------------------------------------------
	
	
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
	
//	//limpiar cmd
//	public final static void clearConsole(){
//	    try{
//	        final String os = System.getProperty("os.name");
//	        if (os.contains("Windows"))
//	            Runtime.getRuntime().exec("cls");
//	        else
//	            Runtime.getRuntime().exec("clear");
//	    }catch (final Exception e){
//	    	System.out.println("catch Exception e");
//	    }
//	}
}
