package prueba;

import java.util.List;

import implementaciones.ABB;
import implementaciones.DiccionarioMultiple;
import libros.Libro;
import implementaciones.Cola;
import tdas.ColaTDA;
import tdas.ConjuntoTDA;
import tdas.DiccionarioMultipleTDA;
import enums.Genero;
import tdas.ABBTDA;

public class PruebaLibros {
	
	public static ColaTDA ObtenerLibrosSegunGenero(ABBTDA a, Genero gen){
		ColaTDA cola = new Cola();
		cola.InicializarCola();
		
		if(!a.ArbolVacio()){
			if(a.ObtenerGenero().equals(gen)){
				for(int i=0 ; i < a.Libros().size() ; i++)
					cola.Acolar(a.Libros().get(i));
			}else if(a.ObtenerGenero().compareTo(gen) < 0){
				ColaTDA colaDer = ObtenerLibrosSegunGenero(a.HijoDer(), gen);
				
				while(!colaDer.ColaVacia()){
					cola.Acolar(colaDer.Primero());
					colaDer.Desacolar();
				}
				
			}else if(a.ObtenerGenero().compareTo(gen) > 0){
				ColaTDA colaIzq = ObtenerLibrosSegunGenero(a.HijoIzq(), gen);
				
				while(!colaIzq.ColaVacia()){
					cola.Acolar(colaIzq.Primero());
					colaIzq.Desacolar();
				}
			}
		}
		
		return cola;
	}
	
	public static DiccionarioMultipleTDA ObtenerLibrosPorPrecio(ABBTDA a, double price){
		DiccionarioMultipleTDA dic = new DiccionarioMultiple();
		dic.InicializarDiccionario();
		
		if(!a.ArbolVacio()){
			List<Libro> books = a.Libros();
			for(int i=0 ; i < books.size() ; i++){
				if(books.get(i).getPrecio() < price){
					dic.Agregar(books.get(i).getGenero(), books.get(i));
				}
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
	
	public ColaTDA ObtenerLibrosSegunAutor(ABBTDA a, String autor){
		ColaTDA cola = new Cola();
		cola.InicializarCola();
		
		if(!a.ArbolVacio()){
			List<Libro> books = a.Libros();
			for(int i=0 ; i < books.size() ; i++){
				if(books.get(i).getAutor().equals(autor)){
					cola.Acolar(books.get(i));
				}
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
	
	public Genero ObtenerGeneroPrincipal(ABBTDA a){
		// TODO falta hacer este método
		return null;
	}
	
	public static void main(String[] args) {
		ABBTDA abb = new ABB();
		
		abb.AgregarGenero(Genero.COMEDIA);
		abb.AgregarGenero(Genero.DRAMATICO);
		abb.AgregarGenero(Genero.EPICO);
		abb.AgregarGenero(Genero.FARSA);
		abb.AgregarGenero(Genero.LIRICO);
		abb.AgregarGenero(Genero.MELODRAMA);
		abb.AgregarGenero(Genero.TRAGEDIA);
		abb.AgregarGenero(Genero.TRAGICOMEDIA);
		
		
		Libro libro1 = new Libro(Genero.COMEDIA, "lol1", "Yami", 4.50);
		Libro libro2 = new Libro(Genero.DRAMATICO, "drama1", "Juan", 5.00);
		Libro libro3 = new Libro(Genero.DRAMATICO, "drama2", "Juan", 10.80);
		Libro libro4 = new Libro(Genero.EPICO, "epic1", "Pedro", 20.50);
		Libro libro5 = new Libro(Genero.EPICO, "epic2", "Pedro", 30.00);
		Libro libro6= new Libro(Genero.EPICO, "epic3", "Pedro", 40.50);
		Libro libro7 = new Libro(Genero.FARSA, "farsa1", "Vieiro", 0.50);
		Libro libro8 = new Libro(Genero.FARSA, "farsa2", "Vieiro", 1.00);
		Libro libro9 = new Libro(Genero.FARSA, "farsa3", "Vieiro", 2.50);
		Libro libro10 = new Libro(Genero.FARSA, "farsa4", "Vieiro", 3.50);
		Libro libro11 = new Libro(Genero.LIRICO, "lirico1", "Martin", 50.50);
		Libro libro12 = new Libro(Genero.LIRICO, "lirico2", "Martin", 60.00);
		Libro libro13 = new Libro(Genero.LIRICO, "lirico3", "Martin", 70.50);
		Libro libro14 = new Libro(Genero.LIRICO, "lirico4", "Martin", 80.00);
		Libro libro15 = new Libro(Genero.LIRICO, "lirico5", "Martin", 90.50);
		Libro libro16 = new Libro(Genero.MELODRAMA, "melo1", "Miguel", 100.50);
		Libro libro17 = new Libro(Genero.MELODRAMA, "melo2", "Miguel", 23.50);
		Libro libro18 = new Libro(Genero.MELODRAMA, "melo3", "Miguel", 41.00);
		Libro libro19 = new Libro(Genero.MELODRAMA, "melo4", "Miguel", 12.50);
		Libro libro20 = new Libro(Genero.MELODRAMA, "melo5", "Miguel", 58.00);
		Libro libro21 = new Libro(Genero.MELODRAMA, "melo6", "Miguel", 21.50);
		Libro libro22 = new Libro(Genero.TRAGEDIA, "tragedia1", "jose", 11.50);
		Libro libro23 = new Libro(Genero.TRAGEDIA, "tragedia2", "jose", 180.00);
		Libro libro24 = new Libro(Genero.TRAGEDIA, "tragedia3", "jose", 200.50);
		Libro libro25 = new Libro(Genero.TRAGEDIA, "tragedia4", "jose", 50.50);
		Libro libro26 = new Libro(Genero.TRAGEDIA, "tragedia5", "jose", 150.50);
		Libro libro27 = new Libro(Genero.TRAGEDIA, "tragedia6", "jose", 300.00);
		Libro libro28 = new Libro(Genero.TRAGEDIA, "tragedia7", "jose", 110.50);
		Libro libro29 = new Libro(Genero.TRAGICOMEDIA, "tragicom1", "Ernesto", 20.50);
		Libro libro30 = new Libro(Genero.TRAGICOMEDIA, "tragicom2", "Ernesto", 900.00);
		Libro libro31 = new Libro(Genero.TRAGICOMEDIA, "tragicom3", "Ernesto", 800.50);
		Libro libro32 = new Libro(Genero.TRAGICOMEDIA, "tragicom4", "Ernesto", 600.50);
		Libro libro33 = new Libro(Genero.TRAGICOMEDIA, "tragicom5", "Ernesto", 450.50);
		Libro libro34 = new Libro(Genero.TRAGICOMEDIA, "tragicom6", "Ernesto", 320.00);
		Libro libro35 = new Libro(Genero.TRAGICOMEDIA, "tragicom7", "Ernesto", 101.50);
		Libro libro36 = new Libro(Genero.TRAGICOMEDIA, "tragicom8", "Ernesto", 320.00);
		
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
		abb.AgregarLibro(libro29);
		abb.AgregarLibro(libro30);
		abb.AgregarLibro(libro31);
		abb.AgregarLibro(libro32);
		abb.AgregarLibro(libro33);
		abb.AgregarLibro(libro34);
		abb.AgregarLibro(libro35);
		abb.AgregarLibro(libro36);
		
		
		ColaTDA colita = ObtenerLibrosSegunGenero(abb, Genero.COMEDIA);
		
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
		
		colita = ObtenerLibrosSegunGenero(abb, Genero.TRAGICOMEDIA);
		
		System.out.println("LIBROS SEGUN GENERO TRAGICOMEDIA");
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
		
	}

}
