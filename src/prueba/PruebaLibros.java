package prueba;

import implementaciones.ABBCatalogo;
import implementaciones.Cola;
import implementaciones.DiccionarioMultiple;
import tdas.ABBTDACatalogo;
import tdas.ColaTDA;
import tdas.ConjuntoTDA;
import tdas.DiccionarioMultipleTDA;
import clases.Genero;
import clases.Libro;

public class PruebaLibros {

	public static ColaTDA obtenerLibrosSegunGenero(ABBTDACatalogo a, String strGen) {

		Genero genero = buscarGenero(a, strGen);
		if (genero != null) {
			return genero.getLibros();
		}
		return null;
	}

	public static DiccionarioMultipleTDA obtenerLibrosPorPrecio(ABBTDACatalogo a, double price) {

		DiccionarioMultipleTDA dic = new DiccionarioMultiple();
		dic.inicializarDiccionario();

		/**
		 * Obtenemos los generos en una cola para facilitar el recorrido
		 */
		ColaTDA generos = new Cola();
		generos.inicializarCola();
		obtenerGeneros(a, generos);

		/**
		 * Iteramos sobre los generos
		 */
		while (!generos.colaVacia()) {
			Genero generoActual = (Genero) generos.primero();

			/**
			 * Iteramos sobre los libros del genero
			 */
			ColaTDA libros = generoActual.getLibros();
			while (!libros.colaVacia()) {
				Libro libroActual = (Libro) libros.primero();

				/**
				 * Evaluamos si debe entrar en el diccionario
				 */
				if (libroActual.getPrecio() < price) {
					dic.agregar(generoActual.getNombre(), libroActual);
				}
				libros.desacolar();
			}
			generos.desacolar();
		}

		return dic;
	}

	public static ColaTDA obtenerLibrosSegunAutor(ABBTDACatalogo a, String autor) {
		ColaTDA cola = new Cola();
		cola.inicializarCola();

		if (autor != null && !autor.isEmpty()) {

			/**
			 * Obtenemos los generos en una cola para facilitar el recorrido
			 */
			ColaTDA generos = new Cola();
			generos.inicializarCola();
			obtenerGeneros(a, generos);

			/**
			 * Iteramos sobre los generos
			 */
			while (!generos.colaVacia()) {
				Genero generoActual = (Genero) generos.primero();

				/**
				 * Iteramos sobre los libros del genero
				 */
				ColaTDA libros = generoActual.getLibros();
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
		ColaTDA generos = new Cola();
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
	private static void obtenerGeneros(ABBTDACatalogo a, ColaTDA generos) {
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
		ColaTDA cola = gen.getLibros();

		while (!cola.colaVacia()) {
			cantidad++;
			cola.desacolar();
		}

		return cantidad;

	}

	public static void main(String[] args) {

		ABBTDACatalogo abb = new ABBCatalogo();
		abb.inicializar();

		Libro libro1 = new Libro("Comedia", "lol1", "Yami", 4.50);
		Libro libro2 = new Libro("Drama", "drama1", "Juan", 5.00);
		Libro libro3 = new Libro("Drama", "drama2", "Juan", 10.80);
		Libro libro4 = new Libro("Epico", "epic1", "Pedro", 20.50);
		Libro libro5 = new Libro("Epico", "epic2", "Pedro", 30.00);
		Libro libro6 = new Libro("Epico", "epic3", "Pedro", 40.50);
		Libro libro7 = new Libro("Farsa", "farsa1", "Vieiro", 0.50);
		Libro libro8 = new Libro("Farsa", "farsa2", "Vieiro", 1.00);
		Libro libro9 = new Libro("Farsa", "farsa3", "Vieiro", 2.50);
		Libro libro10 = new Libro("Farsa", "farsa4", "Vieiro", 3.50);
		Libro libro11 = new Libro("Lirico", "lirico1", "Martin", 50.50);
		Libro libro12 = new Libro("Lirico", "lirico2", "Martin", 60.00);
		Libro libro13 = new Libro("Melodrama", "melo3", "Maximo", 70.50);
		Libro libro14 = new Libro("Lirico", "lirico4", "Martin", 80.00);
		Libro libro15 = new Libro("Lirico", "lirico5", "Martin", 90.50);
		Libro libro16 = new Libro("Melodrama", "melo1", "Miguel", 100.50);
		Libro libro17 = new Libro("Melodrama", "melo2", "Miguel", 23.50);
		Libro libro18 = new Libro("Lirico", "lirico3", "Joan Manuel", 41.00);
		Libro libro19 = new Libro("Arte", "arte1", "Pedro", 12.50);
		Libro libro20 = new Libro("Melodrama", "melo5", "Miguel", 58.00);
		Libro libro21 = new Libro("Melodrama", "melo6", "Miguel", 21.50);
		Libro libro22 = new Libro("Tragedia", "tragedia1", "jose", 11.50);
		Libro libro23 = new Libro("Tragedia", "tragedia2", "jose", 180.00);
		Libro libro24 = new Libro("Arte", "arte2", "Hernan", 200.50);
		Libro libro25 = new Libro("Tragedia", "tragedia3", "jose", 50.50);
		Libro libro26 = new Libro("Tragedia", "tragedia4", "jose", 150.50);
		Libro libro27 = new Libro("Tragedia", "tragedia5", "jose", 300.00);
		Libro libro28 = new Libro("Tragedia", "tragedia6", "jose", 110.50);

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

		Genero gene = new Genero("Comedia");

		ColaTDA colita = obtenerLibrosSegunGenero(abb, gene.getNombre());

		System.out.println("LIBROS SEGUN GENERO COMEDIA");
		System.out.println();

		while (!colita.colaVacia()) {
			Libro book = (Libro) colita.primero();
			System.out.println("Libro: " + book.getTitulo());
			colita.desacolar();
		}

		System.out.println();
		System.out.println("---------------------------------------");
		System.out.println();

		gene.setNombre("Tragedia");

		colita = obtenerLibrosSegunGenero(abb, gene.getNombre());

		System.out.println("LIBROS SEGUN GENERO TRAGEDIA");
		System.out.println();

		while (!colita.colaVacia()) {
			Libro book = (Libro) colita.primero();
			System.out.println("Libro: " + book.getTitulo());
			colita.desacolar();
		}

		System.out.println();
		System.out.println("---------------------------------------");
		System.out.println();

		System.out.println("LIBROS CON PRECIO MENOR A $20");
		System.out.println();

		DiccionarioMultipleTDA dicc = obtenerLibrosPorPrecio(abb, 20);
		ConjuntoTDA conjClave;

		if (!dicc.diccionarioVacio()) {
			conjClave = dicc.claves();
			while (!conjClave.conjuntoVacio()) {
				String nombreGenero = (String) conjClave.elegir();
				System.out.println();
				System.out.println("Genero: " + nombreGenero);
				System.out.println();
				ConjuntoTDA conjLibro = dicc.recuperar(nombreGenero);
				while (!conjLibro.conjuntoVacio()) {
					Libro book = (Libro) conjLibro.elegir();
					System.out.println(book.getTitulo());
					conjLibro.sacar(book);
				}
				conjClave.sacar(nombreGenero);
			}
		}

		System.out.println();
		System.out.println("---------------------------------------");
		System.out.println();

		System.out.println("GENERO PRINCIPAL");
		System.out.println();

		String genero = obtenerGeneroPrincipal(abb);

		if (genero != null) {
			System.out.println(genero);
		} else {
			System.out.println("No hay un g�nero principal.");
		}

		System.out.println();
		System.out.println("---------------------------------------");
		System.out.println();

		System.out.println("LIBROS DE JUAN");
		ColaTDA librosObtenidos = obtenerLibrosSegunAutor(abb, "juan");

		while (!librosObtenidos.colaVacia()) {
			Libro book = (Libro) librosObtenidos.primero();
			System.out.println("Libro: " + book.getTitulo());
			librosObtenidos.desacolar();
		}

	}
}
