package prueba;

import java.util.List;

import clases.Genero;
import clases.Libro;
import implementaciones.*;
import tdas.*;

public class PruebaLibros {
	
	public static ColaTDA ObtenerLibrosSegunGenero(ABBTDACatalogo a, Genero gen){
		ColaTDA cola = new Cola();
		cola.InicializarCola();
		
		if(!a.ArbolVacio()){
			if(a.ObtenerGenero().getNombre().equals(gen.getNombre())){
				ColaTDA aux = a.ObtenerGenero().getLibros();
				while(!aux.ColaVacia()){
					cola.Acolar(aux.Primero());
					aux.Desacolar();
				}
			}else if(a.ObtenerGenero().getNombre().compareTo(gen.getNombre()) < 0){
				ColaTDA colaDer = ObtenerLibrosSegunGenero(a.HijoDer(), gen);
				
				while(!colaDer.ColaVacia()){
					cola.Acolar(colaDer.Primero());
					colaDer.Desacolar();
				}
				
			}else if(a.ObtenerGenero().getNombre().compareTo(gen.getNombre()) > 0){
				ColaTDA colaIzq = ObtenerLibrosSegunGenero(a.HijoIzq(), gen);
				
				while(!colaIzq.ColaVacia()){
					cola.Acolar(colaIzq.Primero());
					colaIzq.Desacolar();
				}
			}
		}
		
		return cola;
	}
	
	
	public static DiccionarioMultipleTDA ObtenerLibrosPorPrecio(ABBTDACatalogo a, double price){
		// TODO hay que revisar este metodo
		
		DiccionarioMultipleTDA dic = new DiccionarioMultiple();
		dic.InicializarDiccionario();
		
		if(!a.ArbolVacio()){
			ColaTDA books = a.ObtenerGenero().getLibros();
			while(!books.ColaVacia()){
				if(((Libro) books.Primero()).getPrecio() < price){
					dic.Agregar(((Libro) books.Primero()).getGenero(), books.Primero());
				}
				books.Desacolar();
			}
			DiccionarioMultipleTDA dicIzq = ObtenerLibrosPorPrecio(a.HijoIzq(), price);
			DiccionarioMultipleTDA dicDer = ObtenerLibrosPorPrecio(a.HijoDer(), price);
			
			while(!dicIzq.DiccionarioVacio()){
				ConjuntoTDA conjGen = dicIzq.Claves();
				Genero gen = (Genero) conjGen.Elegir();
				ConjuntoTDA conjLib = dicIzq.Recuperar(gen);
				while(!conjLib.ConjuntoVacio()){
					Libro book = (Libro) conjLib.Elegir();
					dic.Agregar(gen, book);
					dicIzq.EliminarValor(gen, book);
					conjLib.Sacar(book);
				}
				conjGen.Sacar(gen);
			}
			
			while(!dicDer.DiccionarioVacio()){
				ConjuntoTDA conjGen = dicDer.Claves();
				Genero gen = (Genero) conjGen.Elegir();
				ConjuntoTDA conjLib = dicDer.Recuperar(gen);
				while(!conjLib.ConjuntoVacio()){
					Libro book = (Libro) conjLib.Elegir();
					dic.Agregar(gen, book);
					dicDer.EliminarValor(gen, book);
					conjLib.Sacar(book);
				}
				conjGen.Sacar(gen);
			}
		}
		
		return dic;
	}
	
	public ColaTDA ObtenerLibrosSegunAutor(ABBTDACatalogo a, String autor){
		ColaTDA cola = new Cola();
		cola.InicializarCola();
		
		if(!a.ArbolVacio()){
			ColaTDA books = a.ObtenerGenero().getLibros();
			while(!books.ColaVacia()){
				if(((Libro) books.Primero()).getAutor().equals(autor)){
					cola.Acolar(books.Primero());
				}
				books.Desacolar();
			}
			
			ColaTDA colaIzq = ObtenerLibrosSegunAutor(a.HijoIzq(), autor);
			ColaTDA colaDer = ObtenerLibrosSegunAutor(a.HijoDer(), autor);
			
			while(!colaIzq.ColaVacia()){
				cola.Acolar(colaIzq.Primero());
				colaIzq.Desacolar();
			}
			
			while(!colaDer.ColaVacia()){
				cola.Acolar(colaDer.Primero());
				colaDer.Desacolar();
			}
		}
		
		return cola;
	}
	
	
	
	
	public static Genero ObtenerGeneroPrincipal(ABBTDACatalogo a){
		
		// TODO hay que arreglar este metodo
		
		Genero generoPrincipal = null;
		int cantidadMayor = 0;
		ConjuntoTDA claves = null;
		
		DiccionarioSimpleTDA dicc = new DiccionarioSimple();
		dicc.InicializarDiccionario();
				
//		dicc.Agregar(Genero.TRAGEDIA, ObtenerCantidadLibros(a, Genero.TRAGEDIA));
//		dicc.Agregar(Genero.COMEDIA, ObtenerCantidadLibros(a, Genero.COMEDIA));
//		dicc.Agregar(Genero.MELODRAMA, ObtenerCantidadLibros(a, Genero.MELODRAMA));
//		dicc.Agregar(Genero.TRAGICOMEDIA, ObtenerCantidadLibros(a, Genero.TRAGICOMEDIA));
//		dicc.Agregar(Genero.FARSA, ObtenerCantidadLibros(a, Genero.FARSA));
//		dicc.Agregar(Genero.EPICO, ObtenerCantidadLibros(a, Genero.EPICO));
//		dicc.Agregar(Genero.LIRICO, ObtenerCantidadLibros(a, Genero.LIRICO));
//		dicc.Agregar(Genero.DRAMATICO, ObtenerCantidadLibros(a, Genero.DRAMATICO));
		
		claves = dicc.Claves();
		
		// Calculo la mayor cantidad, luego retorno el g�nero con mayor cantidad de libros.
		while (!claves.ConjuntoVacio()) {
			Genero clave = (Genero)claves.Elegir();
			int cantidad = (int)dicc.Recuperar(clave);
			
			if (cantidad > cantidadMayor) {
				cantidadMayor = cantidad;
				generoPrincipal = clave;
			}
			
			claves.Sacar(clave);
		}
		
		return generoPrincipal;
	}
	
	private static int ObtenerCantidadLibros(ABBTDACatalogo a, Genero gen) {
		int cantidad = 0;
		
		ColaTDA cola = ObtenerLibrosSegunGenero(a, gen);
		
		while (!cola.ColaVacia()) {
			cantidad++;
			cola.Desacolar();
		}
		
		return cantidad;
	}
	
	
	public static void main(String[] args) {
		ABBTDACatalogo abb = new ABBCatalogo();
		abb.Inicializar();
		
		Libro libro1 = new Libro("Comedia", "lol1", "Yami", 4.50);
		Libro libro2 = new Libro("Drama", "drama1", "Juan", 5.00);
		Libro libro3 = new Libro("Drama", "drama2", "Juan", 10.80);
		Libro libro4 = new Libro("Epico", "epic1", "Pedro", 20.50);
		Libro libro5 = new Libro("Epico", "epic2", "Pedro", 30.00);
		Libro libro6= new Libro("Epico", "epic3", "Pedro", 40.50);
		Libro libro7 = new Libro("Farsa", "farsa1", "Vieiro", 0.50);
		Libro libro8 = new Libro("Farsa", "farsa2", "Vieiro", 1.00);
		Libro libro9 = new Libro("Farsa", "farsa3", "Vieiro", 2.50);
		Libro libro10 = new Libro("Farsa", "farsa4", "Vieiro", 3.50);
		Libro libro11 = new Libro("Lirico", "lirico1", "Martin", 50.50);
		Libro libro12 = new Libro("Lirico", "lirico2", "Martin", 60.00);
		Libro libro13 = new Libro("Lirico", "lirico3", "Martin", 70.50);
		Libro libro14 = new Libro("Lirico", "lirico4", "Martin", 80.00);
		Libro libro15 = new Libro("Lirico", "lirico5", "Martin", 90.50);
		Libro libro16 = new Libro("Melodrama", "melo1", "Miguel", 100.50);
		Libro libro17 = new Libro("Melodrama", "melo2", "Miguel", 23.50);
		Libro libro18 = new Libro("Melodrama", "melo3", "Miguel", 41.00);
		Libro libro19 = new Libro("Melodrama", "melo4", "Miguel", 12.50);
		Libro libro20 = new Libro("Melodrama", "melo5", "Miguel", 58.00);
		Libro libro21 = new Libro("Melodrama", "melo6", "Miguel", 21.50);
		Libro libro22 = new Libro("Tragedia", "tragedia1", "jose", 11.50);
		Libro libro23 = new Libro("Tragedia", "tragedia2", "jose", 180.00);
		Libro libro24 = new Libro("Tragedia", "tragedia3", "jose", 200.50);
		Libro libro25 = new Libro("Tragedia", "tragedia4", "jose", 50.50);
		Libro libro26 = new Libro("Tragedia", "tragedia5", "jose", 150.50);
		Libro libro27 = new Libro("Tragedia", "tragedia6", "jose", 300.00);
		Libro libro28 = new Libro("Tragedia", "tragedia7", "jose", 110.50);

		
		abb.AgregarLibro(libro1);
		abb.AgregarLibro(libro2);
		abb.AgregarLibro(libro3);
		abb.AgregarLibro(libro4);
		abb.AgregarLibro(libro5);
		abb.AgregarLibro(libro6);
		abb.AgregarLibro(libro7);
		abb.AgregarLibro(libro8);
		abb.AgregarLibro(libro9);
		abb.AgregarLibro(libro10);
		abb.AgregarLibro(libro11);
		abb.AgregarLibro(libro12);
		abb.AgregarLibro(libro13);
		abb.AgregarLibro(libro14);
		abb.AgregarLibro(libro15);
		abb.AgregarLibro(libro16);
		abb.AgregarLibro(libro17);
		abb.AgregarLibro(libro18);
		abb.AgregarLibro(libro19);
		abb.AgregarLibro(libro20);
		abb.AgregarLibro(libro21);
		abb.AgregarLibro(libro22);
		abb.AgregarLibro(libro23);
		abb.AgregarLibro(libro24);
		abb.AgregarLibro(libro25);
		abb.AgregarLibro(libro26);
		abb.AgregarLibro(libro27);
		abb.AgregarLibro(libro28);
		
		Genero gene = new Genero("Comedia");
		
		ColaTDA colita = ObtenerLibrosSegunGenero(abb, gene);
		
		System.out.println("LIBROS SEGUN GENERO COMEDIA");
		System.out.println();
		
		while(!colita.ColaVacia()){
			Libro book = (Libro) colita.Primero();
			System.out.println("Libro: " + book.getTitulo());
			colita.Desacolar();
		}
		
		System.out.println();
		System.out.println("---------------------------------------");
		System.out.println();
		
		gene.setNombre("Tragedia");
		
		colita = ObtenerLibrosSegunGenero(abb, gene);
		
		System.out.println("LIBROS SEGUN GENERO TRAGEDIA");
		System.out.println();
		
		while(!colita.ColaVacia()){
			Libro book = (Libro) colita.Primero();
			System.out.println("Libro: " + book.getTitulo());
			colita.Desacolar();
		}
		
		System.out.println();
		System.out.println("---------------------------------------");
		System.out.println();
		
		System.out.println("LIBROS CON PRECIO MENOR A $20");
		System.out.println();
		
		DiccionarioMultipleTDA dicc = ObtenerLibrosPorPrecio(abb, 20);
		
		ConjuntoTDA conjClave;
		
		if(dicc.DiccionarioVacio()){
			conjClave = dicc.Claves();
			while(!conjClave.ConjuntoVacio()){
				Genero gen = (Genero) conjClave.Elegir();
				System.out.println("Genero: " + gen);
				System.out.println();
				ConjuntoTDA conjLibro = dicc.Recuperar(gen);
				while(!conjLibro.ConjuntoVacio()){
					Libro book = (Libro) conjLibro.Elegir();
					System.out.println(book.getTitulo());
					conjLibro.Sacar(book);
				}
				conjLibro.Sacar(gen);
			}
		}
		
		System.out.println();
		System.out.println("---------------------------------------");
		System.out.println();
		
		System.out.println("G�NERO PRINCIPAL");
		System.out.println();
		
		Genero genero = ObtenerGeneroPrincipal(abb);
		
		System.out.println(genero.toString());
	}

}
