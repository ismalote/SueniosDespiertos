package prueba;

import implementaciones.ABBCatalogo;
import implementaciones.DiccionarioMultiple;
import tdas.ABBTDACatalogo;
import tdas.DiccionarioMultipleTDA;
import Implementaciones.Cola;
import TDA.ColaTDA;
import TDA.ConjuntoTDA;
import clases.Genero;
import clases.Libro;

public class PruebaLibros {

	/**
	 * 
	 * @param arbol
	 * @param genero
	 * @return Una {@link ColaTDA} con los Libros que pertenecen al <code>genero</code> en el {@link ABBTDACatalogo} <code>arbol</code>
	 */
	public static ColaTDA<Libro> obtenerLibrosSegunGenero(ABBTDACatalogo arbol, String genero) {

		Genero encontrado = buscarGenero(arbol, genero);
		if (encontrado != null) {
			return encontrado.getLibros();
		}
		return null;
	}

	/**
	 * 
	 * @param arbol
	 * @param precio
	 * @return Un {@link DiccionarioMultipleTDA} con los Libros con precio menor al <code>precio</code> en el {@link ABBTDACatalogo} <code>arbol</code>
	 */
	public static DiccionarioMultipleTDA obtenerLibrosPorPrecio(ABBTDACatalogo arbol, double precio) {

		DiccionarioMultipleTDA dic = new DiccionarioMultiple();
		dic.inicializarDiccionario();

		/**
		 * Obtenemos los generos en una cola para facilitar el recorrido
		 */
		ColaTDA<Genero> generos = new Cola<Genero>();
		generos.inicializarCola();
		obtenerGeneros(arbol, generos);

		/**
		 * Iteramos sobre los generos
		 */
		while (!generos.colaVacia()) {
			Genero generoActual = (Genero) generos.primero();

			/**
			 * Iteramos sobre los libros del genero
			 */
			ColaTDA<Libro> libros = generoActual.getLibros();
			while (!libros.colaVacia()) {
				Libro libroActual = (Libro) libros.primero();

				/**
				 * Evaluamos si debe entrar en el diccionario
				 */
				if (libroActual.getPrecio() < precio) {
					dic.agregar(generoActual.getNombre(), libroActual);
				}
				libros.desacolar();
			}
			generos.desacolar();
		}

		return dic;
	}

	/**
	 * 
	 * @param arbol
	 * @param autor
	 * @return Una {@link ColaTDA} con los Libros del <code>autor</code> en el {@link ABBTDACatalogo} <code>arbol</code>
	 */
	public static ColaTDA<Libro> obtenerLibrosSegunAutor(ABBTDACatalogo arbol, String autor) {
		ColaTDA<Libro> cola = new Cola<Libro>();
		cola.inicializarCola();

		if (autor != null && !autor.isEmpty()) {

			/**
			 * Obtenemos los generos en una cola para facilitar el recorrido
			 */
			ColaTDA<Genero> generos = new Cola<Genero>();
			generos.inicializarCola();
			obtenerGeneros(arbol, generos);

			/**
			 * Iteramos sobre los generos
			 */
			while (!generos.colaVacia()) {
				Genero generoActual = (Genero) generos.primero();

				/**
				 * Iteramos sobre los libros del genero
				 */
				ColaTDA<Libro> libros = generoActual.getLibros();
				while (!libros.colaVacia()) {
					Libro libroActual = (Libro) libros.primero();

					/**
					 * Evaluamos si debe entrar en la cola
					 */
					if (autor.equalsIgnoreCase(libroActual.getAutor())) {
						cola.acolar(libroActual);
					}
					libros.desacolar();
				}
				generos.desacolar();
			}
		}
		return cola;
	}

	/**
	 * 
	 * @param arbol
	 * @return El genero con mas libros del arbol.
	 * 
	 *         Si hay más de un género con la misma cantidad de libros, devuelve el primero de ellos.
	 */
	public static String obtenerGeneroPrincipal(ABBTDACatalogo arbol) {

		String generoPrincipal = null;
		int cantidadMayor = 0;

		/**
		 * Obtenemos los generos en una cola para facilitar el recorrido
		 */
		ColaTDA<Genero> generos = new Cola<Genero>();
		generos.inicializarCola();
		obtenerGeneros(arbol, generos);

		/**
		 * Iteramos sobre los generos
		 */
		while (!generos.colaVacia()) {
			Genero gen = (Genero) generos.primero();
			/**
			 * Evaluamos si la cantidad de libros del genero actual es la mayor encontrada
			 */
			int cantidadActual = obtenerCantidadLibros(gen);
			if (cantidadActual > cantidadMayor) {
				cantidadMayor = cantidadActual;
				generoPrincipal = gen.getNombre();
			}
			generos.desacolar();
		}

		return generoPrincipal;
	}

	private static Genero buscarGenero(ABBTDACatalogo arbol, String gen) {
		Genero genero = null;
		if (gen != null && !arbol.arbolVacio()) {
			/**
			 * Evaluamos una sola vez el genero solicitado con el genero actual
			 */
			int compareResult = gen.compareToIgnoreCase(arbol.obtenerGenero().getNombre());
			/**
			 * Si es igual
			 */
			if (compareResult == 0) {
				genero = arbol.obtenerGenero();
			} else
			/**
			 * Si es mayor que el actual nos desplazamos a la derecha
			 */
			if (compareResult > 0) {
				genero = buscarGenero(arbol.hijoDer(), gen);
			} else
			/**
			 * Si es menor que el actual nos desplazamos a la izquierda
			 */
			if (compareResult < 0) {
				genero = buscarGenero(arbol.hijoIzq(), gen);
			}
		}
		return genero;

	}

	/**
	 * Agrega los géneros a la {@link ColaTDA} <code>generos</code> recursivamente.
	 * 
	 * @param a
	 * @param generos
	 */
	private static void obtenerGeneros(ABBTDACatalogo a, ColaTDA<Genero> generos) {
		if (!a.arbolVacio()) {
			obtenerGeneros(a.hijoIzq(), generos);
			generos.acolar(a.obtenerGenero());
			obtenerGeneros(a.hijoDer(), generos);
		}
	}

	/**
	 * Cuenta cuantos objetos de {@link Libro} contiene un {@link Genero}
	 * 
	 * @param gen
	 * @return
	 */
	private static int obtenerCantidadLibros(Genero gen) {

		int cantidad = 0;
		ColaTDA<Libro> cola = gen.getLibros();

		while (!cola.colaVacia()) {
			cantidad++;
			cola.desacolar();
		}

		return cantidad;

	}

	public static void main(String[] args) {

		ABBTDACatalogo abb = new ABBCatalogo();
		abb.inicializar();

		// Creamos cada libro que serán cargados en el catálogo

		Libro libro1 = new Libro("Comedia", "Chistes y Anecdotas", "Maria", 4.50);
		Libro libro2 = new Libro("Drama", "Drama Volumen 1", "Juan", 5.00);
		Libro libro3 = new Libro("Drama", "Drama Volumen 2", "Juan", 10.80);
		Libro libro4 = new Libro("Epico", "Esfuerzo o heroismo", "Pedro", 20.50);
		Libro libro5 = new Libro("Epico", "Corazon", "Pedro", 30.00);
		Libro libro6 = new Libro("Epico", "Para la historia", "Pedro", 40.50);
		Libro libro7 = new Libro("Farsa", "Farsa 10", "Gomez", 10.50);
		Libro libro8 = new Libro("Farsa", "Farsantes", "Gomez", 11.00);
		Libro libro9 = new Libro("Farsa", "Farsa 8", "Gomez", 2.50);
		Libro libro10 = new Libro("Farsa", "Otras farsas", "Gomez", 3.50);
		Libro libro11 = new Libro("Lirico", "Entusiamo", "Martin", 50.50);
		Libro libro12 = new Libro("Lirico", "Inspiracion", "Martin", 60.00);
		Libro libro13 = new Libro("Melodrama", "Melodrama Volumen 1", "Maximo", 70.50);
		Libro libro14 = new Libro("Lirico", "Historias liricas", "Martin", 80.00);
		Libro libro15 = new Libro("Lirico", "Grandes jugadas", "Martin", 90.50);
		Libro libro16 = new Libro("Melodrama", "Melodrama Volumen 2", "Miguel", 100.50);
		Libro libro17 = new Libro("Melodrama", "Melodrama 3", "Miguel", 23.50);
		Libro libro18 = new Libro("Lirico", "Ranking lirico", "Joan Manuel", 41.00);
		Libro libro19 = new Libro("Arte", "Historia del arte", "Pedro", 12.50);
		Libro libro20 = new Libro("Melodrama", "Melodrama, la continuacion", "Miguel", 58.00);
		Libro libro21 = new Libro("Melodrama", "Melodrama, ultima etapa", "Maximo", 21.50);
		Libro libro22 = new Libro("Tragedia", "Tragedy part 1", "Jose", 11.50);
		Libro libro23 = new Libro("Tragedia", "Tragedy part 2", "Jose", 180.00);
		Libro libro24 = new Libro("Arte", "Arte rupestre", "Hernan", 200.50);
		Libro libro25 = new Libro("Tragedia", "Tragedia Importante", "Jose", 50.50);
		Libro libro26 = new Libro("Tragedia", "Tragedia Historica", "Jose", 150.50);
		Libro libro27 = new Libro("Tragedia", "Tragedia Volumen 1", "Jose", 300.00);
		Libro libro28 = new Libro("Tragedia", "Tragedia Volumen 2", "Jose", 110.50);
		Libro libro29 = new Libro("Tragedia", "Tragedia, ultima etapa", "Maximo", 21.50);

		// Cargamos los libros al Catálogo, que se ordenarán de acuerdo a su género.

		abb.agregarLibro(libro11);
		abb.agregarLibro(libro22);
		abb.agregarLibro(libro3);
		abb.agregarLibro(libro4);
		abb.agregarLibro(libro25);
		abb.agregarLibro(libro6);
		abb.agregarLibro(libro7);
		abb.agregarLibro(libro8);
		abb.agregarLibro(libro1);
		abb.agregarLibro(libro10);
		abb.agregarLibro(libro9);
		abb.agregarLibro(libro12);
		abb.agregarLibro(libro13);
		abb.agregarLibro(libro14);
		abb.agregarLibro(libro15);
		abb.agregarLibro(libro16);
		abb.agregarLibro(libro17);
		abb.agregarLibro(libro18);
		abb.agregarLibro(libro19);
		abb.agregarLibro(libro20);
		abb.agregarLibro(libro21);
		abb.agregarLibro(libro2);
		abb.agregarLibro(libro23);
		abb.agregarLibro(libro24);
		abb.agregarLibro(libro5);
		abb.agregarLibro(libro26);
		abb.agregarLibro(libro27);
		abb.agregarLibro(libro28);
		abb.agregarLibro(libro29);

		// Obtenemos los libros que corresponden a un género determinado utilizando obtenerLibrosSegunGenero(ABBTDACatalogo a, String strGen)

		String generoBuscado = "Comedia";

		Genero gene = new Genero(generoBuscado);

		ColaTDA<Libro> librosSegunGenero = obtenerLibrosSegunGenero(abb, gene.getNombre());

		System.out.println("LIBROS SEGUN GÉNERO '" + generoBuscado.toUpperCase() + "'");
		System.out.println("Esperado: \"Chistes y Anecdotas\"");
		System.out.println();

		if (librosSegunGenero == null) {
			System.out.println("No existen libros del género ingresado.");
		} else {
			while (!librosSegunGenero.colaVacia()) {
				Libro book = (Libro) librosSegunGenero.primero();
				System.out.println("- " + book.getTitulo());
				librosSegunGenero.desacolar();
			}
		}

		System.out.println();
		System.out.println("---------------------------------------");
		System.out.println();

		generoBuscado = "Tragedia";

		gene.setNombre(generoBuscado);

		librosSegunGenero = obtenerLibrosSegunGenero(abb, gene.getNombre());

		System.out.println("LIBROS SEGUN GÉNERO '" + generoBuscado.toUpperCase() + "'");
		System.out.println("Esperado:\t\"Tragedy part 1\"");
		System.out.println("\t\t\"Tragedy part 2\"");
		System.out.println("\t\t\"Tragedia Importante\"");
		System.out.println("\t\t\"Tragedia Historica\"");
		System.out.println("\t\t\"Tragedia Volumen 1\"");
		System.out.println("\t\t\"Tragedia Volumen 2\"");
		System.out.println("\t\t\"Tragedia, ultima etapa\"");
		System.out.println();

		if (librosSegunGenero == null) {
			System.out.println("No existen libros del género ingresado.");
		} else {
			while (!librosSegunGenero.colaVacia()) {
				Libro book = (Libro) librosSegunGenero.primero();
				System.out.println("- " + book.getTitulo());
				librosSegunGenero.desacolar();
			}
		}

		// Obtenemos los libros cuyo precio es inferior a un precio determinado utilizando obtenerLibrosPorPrecio(ABBTDACatalogo a, double price)

		System.out.println();
		System.out.println("---------------------------------------");
		System.out.println();

		Double precioBuscado = 20.05;

		System.out.println("LIBROS CON PRECIO MENOR A $" + precioBuscado);
		System.out.println("Esperado:");
		System.out.println("\t\t\"Chistes y Anecdotas\"");
		System.out.println("\t\t\"Tragedy part 1\"");
		System.out.println("\t\t\"Historia del arte\"");
		System.out.println("\t\t\"Otras farsas\"");
		System.out.println("\t\t\"Farsa 10\"");
		System.out.println("\t\t\"Farsa 8\"");
		System.out.println("\t\t\"Farsantes\"");
		System.out.println("\t\t\"Drama Volumen 1\"");
		System.out.println("\t\t\"Drama Volumen 2\"");

		DiccionarioMultipleTDA dicc = obtenerLibrosPorPrecio(abb, precioBuscado);
		ConjuntoTDA<String> conjClave;

		if (!dicc.diccionarioVacio()) {
			conjClave = dicc.claves();
			while (!conjClave.conjuntoVacio()) {
				String nombreGenero = (String) conjClave.elegir();
				System.out.println();
				System.out.println("Género: " + nombreGenero);
				System.out.println();
				ConjuntoTDA<Libro> conjLibro = dicc.recuperar(nombreGenero);
				while (!conjLibro.conjuntoVacio()) {
					Libro book = (Libro) conjLibro.elegir();
					System.out.println("- " + book.getTitulo());
					conjLibro.sacar(book);
				}
				conjClave.sacar(nombreGenero);
			}
		} else {
			System.out.println();
			System.out.println("No existen libros con precio menor al ingresado.");
		}

		// Obtenemos el género principal, que es aquél que posee más libros, utilizando obtenerGeneroPrincipal(ABBTDACatalogo arbol)

		System.out.println();
		System.out.println("---------------------------------------");
		System.out.println();

		System.out.println("GÉNERO PRINCIPAL");
		System.out.println("Esperado:\t Tragedia");
		System.out.println();

		String genero = obtenerGeneroPrincipal(abb);

		if (genero != null) {
			System.out.println(genero);
		} else {
			System.out.println("No hay un género principal.");
		}

		// Obtenemos los libros que corresponden a un autor determinado utilizando obtenerLibrosSegunAutor(ABBTDACatalogo a, String autor) {

		System.out.println();
		System.out.println("---------------------------------------");
		System.out.println();

		String autorBuscado = "maximo";

		System.out.println("LIBROS SEGUN AUTOR: '" + autorBuscado.toUpperCase() + "'");
		System.out.println("Esperado:\t \"Melodrama Volumen 1\"");
		System.out.println("\t\t\"Melodrama, ultima etapa\"");
		System.out.println("\t\t\"Tragedia, ultima etapa\"");
		System.out.println();

		ColaTDA<Libro> librosObtenidos = obtenerLibrosSegunAutor(abb, autorBuscado);

		if (librosObtenidos.colaVacia()) {
			System.out.println("No existen libros del autor ingresado.");
		}

		while (!librosObtenidos.colaVacia()) {
			Libro book = (Libro) librosObtenidos.primero();
			System.out.println("- " + book.getTitulo());
			librosObtenidos.desacolar();
		}

	}
}
