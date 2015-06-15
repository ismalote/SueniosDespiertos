package tdas;

import clases.Genero;
import clases.Libro;

/* Es una estructura que posee un conjunto de Géneros pertenecientes cada uno a un catálogo de libros.
 * Cada catálogo tiene asociado uno o más libros.
 * No pueden existir géneros duplicados.
 * A su vez, cada género puede contener libros duplicados. 
 * Ordena los géneros alfabéticamente a medida que se insertan ubicando 
 * los menores al género raíz, alfabéticamente, a la izquierda y 
 * los mayores al género ráiz, alfabéticamente, a la derecha, y 
 * así sucesivamente con cada género insertado. */
public interface ABBTDACatalogo {

	/* Inicializa la estructura.
	 * Pre-condiciones: No posee.
	 * Pos-condiciones: La estructura queda inicializada. */
	public void Inicializar();
	
	/* Indica si la estructura tiene elementos o no (true: no tiene | false: tiene).
	 * Pre-condiciones: La estructura tiene que estar inicializada. 
	 * Pos-condiciones: La estructura no se ve modificada. */
	public boolean ArbolVacio();
	
	/* Agrega un libro a la estructura. Si el género del libro existe, se incorpora a ese género. 
	 * Si el género no existe, se crea primero el género y luego se incorpora el libro.
	 * Pre-condiciones: La estructura tiene que estar inicializada.
	 * Pos-condiciones: La estructura se ve modificada con un elemento en más. */
	public void AgregarLibro(Libro libro);
	
	/* Elimina un género de la estructura y todos sus libros. 
	 * Reacomoda los restantes géneros del catálogo.
	 * Pre-condiciones: La estructura tiene que estar inicializada.
	 * Pos-condiciones: La estructura se ve modificada con un elemento en menos, si existe el género. */
	public void EliminarGenero(Genero genero);
	
	/* Elimina uno o más libros de la estructura.
	 * Si es el único libro, elimina también el género.
	 * Pre-condiciones: La estructura tiene que estar inicializada y el género del libro debe existir.
	 * Pos-condiciones: La estructura se ve modificada, con tantas ocurrencias del libro existan, en menos, 
	 * si tanto el género del libro como el libro existen. 
	 * Si el género del libro no existe o el libro no existe en su género, no hace nada. */
	public void EliminarLibro(Libro libro);
	
	/* Devuelve el Género que está en la raíz del catálogo o nulo si el árbol es nulo.
	 * Pre-condiciones: La estructura tiene que estar inicializada.
	 * Pos-condiciones: No posee. */
	public Genero ObtenerGenero();
	
	/* Devuelve el catálogo de libros asociado al género de la izquierda o nulo si no existe.
	 * Pre-condiciones: La estructura tiene que estar inicializada.
	 * Pos-condiciones: No posee. */
	public ABBTDACatalogo HijoIzq();
	
	/* Devuelve el catálogo de libros asociado al género de la derecha o nulo si no existe.
	 * Pre-condiciones: La estructura tiene que estar inicializada.
	 * Pos-condiciones: No posee. */
	public ABBTDACatalogo HijoDer();
		
}