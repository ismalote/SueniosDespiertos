package tdas;

import clases.Genero;
import clases.Libro;

/* Es una estructura que representa a un catálogo de libros, 
 * los cuales están agrupados por género. 
 * No pueden existir géneros duplicados. Cada género tiene 
 * asociado uno o más libros; y a su vez, éstos pueden estar duplicados. 
 * Dichos géneros quedan ordenados alfabéticamente a medida 
 * que se insertan, ubicando los alfabéticamente anteriores al género 
 * raíz a la izquierda, y los alfabéticamente posteriores
 * al género raíz a la derecha. */
public interface ABBTDACatalogo {

	/* Inicializa la estructura.
	 * Pre-condiciones: No posee.
	 * Pos-condiciones: La estructura queda inicializada. */
	void inicializar();
	
	/* Indica si la estructura tiene elementos o no (true: no tiene | false: tiene).
	 * Pre-condiciones: La estructura tiene que estar inicializada. 
	 * Pos-condiciones: La estructura no se ve modificada. */
	boolean arbolVacio();
	
	/* Agrega un libro a la estructura. Si el género del libro existe, se incorpora a ese género. 
	 * Si el género no existe, se crea primero el género y luego se incorpora el libro. 
	 * Pre-condiciones: La estructura tiene que estar inicializada. 
	 * Pos-condiciones: La estructura se ve modificada con un libro más en caso de existir el 
	 * género; en caso contrario, se ve modificada con un género más y un libro asociado a éste. */
	void agregarLibro(Libro libro);
	
	/* Elimina un género de la estructura y todos sus libros. Reacomoda los restantes géneros del catálogo. 
	 * Pre-condiciones: La estructura tiene que estar inicializada. 
	 * Pos-condiciones: La estructura se ve modificada con un elemento en menos, si existe el género.*/
	void eliminarGenero(Genero genero);
	
	/* Elimina uno o más libros de la estructura. Si el género del libro no existe o 
	 * el libro no existe en su género, no hace nada. Si es el único libro, elimina también el género. 
	 * Pre-condiciones: La estructura tiene que estar inicializada. 
	 * Pos-condiciones: La estructura se ve modificada, con tantas 
	 * ocurrencias del libro existan, en menos, si tanto el género del libro como el libro existen. */
	void eliminarLibro(Libro libro);
	
	/* Devuelve el Género que está en la raíz del catálogo o nulo si el árbol es nulo. 
	 * Pre-condiciones: La estructura tiene que estar inicializada. 
	 * Pos-condiciones: No posee. */
	Genero obtenerGenero();
	
	/* Devuelve el catálogo de libros asociado al género cuyo nombre es alfabéticamente anterior al género raíz, o nulo si no existe. 
	 * Pre-condiciones: La estructura tiene que estar inicializada. 
	 * Pos-condiciones: No posee. */
	ABBTDACatalogo hijoIzq();
	
	/* Devuelve el catálogo de libros asociado al género cuyo nombre es alfabéticamente posterior al género raíz, o nulo si no existe. 
	 * Pre-condiciones: La estructura tiene que estar inicializada. 
	 * Pos-condiciones: No posee. */
	ABBTDACatalogo hijoDer();
		
}