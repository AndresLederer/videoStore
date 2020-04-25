package videoStore;

import java.text.SimpleDateFormat;
import java.util.*;
//import java.io.*;

public class VideoStore {
	//atributos
	private static ArrayList <Pelicula> libreriaPeliculas;
	private static ArrayList <Cliente> agendaClientes ;
	private static ArrayList <BoletaPrestamo> boletasPrestamo;
	private static Scanner scn;// = new Scanner(System.in);
	private static Date actualDate = new Date();
	private static SimpleDateFormat formatoPers = new SimpleDateFormat("dd/MM/yyyy");
	
	//metodos main
	public static void main(String [] args) {
		//instancio Peliculas
		Pelicula p1 = new Pelicula("Titanic",130,"Drama",1998,"Estados Unidos","Basada en hechos reales. El undimiento del Titanic.","G",5,5);
		Pelicula p2 = new Pelicula("Harry Potter 1",120,"Acción",2002,"Estados Unidos","La historia del niño que vivió","PG-13",8,8);
		Pelicula p3 = new Pelicula("Planet Earth",110,"Documental",2016,"United Kingdom","Un detallado documental de la BBC sobre la crisis climática","G",12,12);
		Pelicula p4 = new Pelicula("Narnia",90,"Aventura",2012,"Estados Unidos","La aventura de cuatro hermanos en un mundo fantástico","PG-17",1,1);
		Pelicula p5 = new Pelicula("Nemo",100,"Aventura",2008,"Estados Unidos","La aventura de Merlín buscando a su hijo a través de todo el oceáno","G",5,5);
		Pelicula p6 = new Pelicula("Silence",96,"Terror",2018,"United Kingdom","El silencio se apodera del mundo. Aquel que hable se autosentencia a un final oscuro","UNRATED",3,3);
		Pelicula p7 = new Pelicula("Yumanji",110,"Aventura",2005,"Estados Unidos","Yumanji no es solo un juego de mesa. Anímate a jugar, pero... bajo tu propio riesgo","PG-13",5,5);
		//instancio libreria de peliculas 
		libreriaPeliculas = new ArrayList <Pelicula>();
		//cargo peliculas a la libreria
		libreriaPeliculas.add(p1);
		libreriaPeliculas.add(p2);
		libreriaPeliculas.add(p3);
		libreriaPeliculas.add(p4);
		libreriaPeliculas.add(p5);
		libreriaPeliculas.add(p6);
		libreriaPeliculas.add(p7);
		
		//instancio Clientes
		Cliente c1 = new Cliente("Jael Stainer","+20 112 457852","Ganzngen Street 17",0);
		Cliente c2 = new Cliente("Anais Stainer","+20 112 159753","Larrea Street 230",0);
		Cliente c3 = new Cliente("Tim Young","+54 223 5395183","Whiteladies Road 24",0);
		Cliente c4 = new Cliente("Louisa Ximenez","+32 20 6147455","Pembrook Street 90",0);
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
		try {
			mainMenu();
		}catch (InputMismatchException ex) {
			System.out.println(">>>>> DEBE INGRESAR UN NUMERO ENTERO <<<<<");
			main(args);
		}
	}
	
	// METODOS ------------------------------------------------------------------------------------------------------------------------------------
	
	//mainMenu -- concidero throws InputMismatchException por si se ingresa un String en lungar de un int
	private static void mainMenu() throws InputMismatchException {
		scn = new Scanner(System.in);
		int opcion;
//		try { //intento ejecutar el programa
			do {
				do {
					System.out.println(formatoPers.format(actualDate));
					System.out.println("<<< VIDEO STORE >>>\n\n");
					System.out.println("[1] Alquilar pelicula"); //alquila una pelicula
					System.out.println("[2] Ver devoluciones del día"); //muestra las devoluciones de la fecha actual
					System.out.println("[3] Ver alquileres vigentes"); //muestra los alquileres aun vigentes
					System.out.println("[4] Ver últimos 3 alquileres por cliente"); //muestra los ultimos 3 alquileres de un cliente
					System.out.println("[5] Ver top 3 de películas más alquiladas"); //muestra el top 3 de las pelis mas alquiladas
					System.out.println("[6] Ver descripción de "); //muestra descripcion de una pelicula
					System.out.println("[7] Ver películas por género"); //muestra todas las peliculas de un genero, ordenadas  por popularidad
					System.out.println("[0] Salir"); //finaliza el programa
					System.out.printf("\n\nIndique una opción: "); 
					opcion = scn.nextInt();
				} while(opcion < 0 || opcion > 7);
				switch(opcion) {
				case 0:
					System.out.println("Ha finalizado la ejecución del programa");
					System.exit(0);
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
				case 4:
					scn = new Scanner(System.in);
					System.out.printf("Indique el nombre completo del cliente: ");
					String cliente = scn.nextLine();
					verUltimosAlquileresPorCliente(capitalizeEachWord(cliente));
					break;
				case 5:
					
					break;
				case 6:
					scn = new Scanner(System.in);
					System.out.println("Indique el titulo de la película: ");
					String pelicula = scn.nextLine();
					if(buscarPeliculaPorNombre(capitalizeEachWord(pelicula)))
						verDescripcionPelicula(capitalizeEachWord(pelicula));
					else
						System.out.println("La pelicula ingresada no existe en la libreria del VideoStore");
					break;
				case 7:
					scn = new Scanner(System.in);
					System.out.printf("Indique un género: ");
					String genero = scn.nextLine();
					if(checkPeliculasDeGenero(capitalizeEachWord(genero))) {
						System.out.println("[PELICULAS DE "+genero.toUpperCase()+"]");
						for(Pelicula p : libreriaPeliculas) {
							if(p.getGenero().equals(capitalizeEachWord(genero))) {
								System.out.println("--------------------------------------");
								System.out.println(p.toString());
								System.out.println("--------------------------------------");
							}
						}
					}else {
						System.out.println("No hay peliculas de \""+genero.toUpperCase()+"\" en la libreria del VideoStore");
					}
					break;
				}
			} while(opcion != 0);
//		}catch(InputMismatchException ex) { //si en scn recibo un String en lugar de un int => advierto con un mensaje por cmd y recursión al método
//			System.out.println(">>> DEBE INGRESAR UN NUMERO ENTERO <<<");
//			mainMenu();
//		}
	}
	
//	//corrobora que una pelicula este en la libreriaPeliculas
//	private static boolean buscarPelicula(Pelicula peliculaBuscada) {
//		boolean existe = false;
//		for(Pelicula p : libreriaPeliculas) {
//			if(p.equals(peliculaBuscada)) existe = true;
//		}
//		return existe;
//	}
	
	//corrobora que haya peliculas de un determinado genero en la libreria del VideoStore
	private static boolean checkPeliculasDeGenero(String genero) {
		boolean hayPelis = false;
		for(Pelicula p : libreriaPeliculas) {
			if(p.getGenero().equals(genero) && hayPelis == false)
				hayPelis = true;
		}
		return hayPelis;
	}
	
	//muestra la descripcion de una pelicula -- recibe el nombre por parametro
	private static void verDescripcionPelicula(String pelicula) {
		for(Pelicula p : libreriaPeliculas) {
			if(p.getTitulo().contentEquals(pelicula)) {
				System.out.println("[DESCRIPCION DE "+pelicula.toUpperCase()+"]");
				System.out.println("--------------------------------------");
				System.out.println(p.getDescripcion());
				System.out.println("--------------------------------------");
			}
		}
	}
	
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
					for(Cliente c : agendaClientes) {
						if(c.getNombre().equals(capitalizeEachWord(nombreCliente)))
								c.setCantAlquileres(c.getCantAlquileres()+1);
					}
					BoletaPrestamo boletaNueva = creaBoletaNueva(capitalizeEachWord(tituloBuscado),capitalizeEachWord(nombreCliente));
					boletasPrestamo.add(boletaNueva);
				}else { //si se trata de un clienten nuevo
					//creo cliente nuevo y lo agrego a la agenda de clientes
					Cliente clienteNuevo = creaClienteNuevo();
					clienteNuevo.setCantAlquileres(clienteNuevo.getCantAlquileres()+1);
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
		System.out.printf("Nombre y apellido: ");
		String nombreClienteNuevo = scn.nextLine();
		System.out.printf("Telefono: ");
		String telClienteNuevo = scn.nextLine();
		System.out.printf("Dirección: ");
		String direcClienteNuevo = scn.nextLine();
		Cliente clienteNuevo = new Cliente(capitalizeEachWord(nombreClienteNuevo),telClienteNuevo,capitalizeEachWord(direcClienteNuevo),0);
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
	
	//muestra por cmd los ultimos 3 alquileres de un cliente -- el argumento ya se recibe "capitalizado"
	private static void verUltimosAlquileresPorCliente(String cliente) {
		if(checkClienteExistente(cliente)) { //chequeo q el cliente este en mi agenda
			Cliente clienteAmostrar = buscarClientePorNombre(cliente); //obtengo el Cliente (obj)
			System.out.println("\n[ULTIMOS 3 ALQUILERES DE : "+cliente.toUpperCase()+"]");
			if(clienteAmostrar.getCantAlquileres() > 3) { //si ha hecho mas de 3 alquileres => muestro los ultimos 3
				int i;
				int mostrados=0;
				for(i=boletasPrestamo.size()-1 ; i>0 ; i--) {
					if(mostrados < 3 && boletasPrestamo.get(i).getNombreCliente().equals(cliente)) {
						System.out.println("--------------------------------------");
						System.out.println(boletasPrestamo.get(i).toString());
						System.out.println("--------------------------------------");
						mostrados++;
					}
				}
			}else if(clienteAmostrar.getCantAlquileres() > 0){ //si ha hecho 3 alquileres o menos => nuestro todos
				for(BoletaPrestamo b : boletasPrestamo) {
					if(b.getNombreCliente().equals(cliente))
						System.out.println("--------------------------------------");
						System.out.println(b.toString());
						System.out.println("--------------------------------------");
				}
			}else {
				System.out.println("El cliente ingresado no tienen ningún alquiler registrado");
			}
		}else { //si el cliente no esta en mi agenda => no tengo nada para mostrar
			System.out.println("El cliente ingresado no existe en la agenda del VideoStore");
		}
	}
	
	//busca un cliente por su nombre en la agenda de Clientes y devuelve todo el objeto Cliente
	private static Cliente buscarClientePorNombre(String nombreClienteBuscado) {
		Cliente clienteBuscado = null;
		for(Cliente c : agendaClientes) {
			if(c.getNombre().equals(nombreClienteBuscado)) clienteBuscado = c;
		}
		return clienteBuscado;
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
