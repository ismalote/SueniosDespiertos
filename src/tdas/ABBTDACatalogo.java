package tdas;

import clases.Genero;
import clases.Libro;

/* Es una estructura que posee un conjunto de G�neros pertenecientes cada uno a un cat�logo de libros.
 * Cada cat�logo tiene asociado uno o m�s libros.
 * No pueden existir g�neros duplicados.
 * A su vez, cada g�nero puede contener libros duplicados. 
 * Ordena los g�neros alfab�ticamente a medida que se insertan ubicando 
 * los menores al g�nero ra�z, alfab�ticamente, a la izquierda y 
 * los mayores al g�nero r�iz, alfab�ticamente, a la derecha, y 
 * as� sucesivamente con cada g�nero insertado. */
public interface ABBTDACatalogo {

	/* Inicializa la estructura.
	 * Pre-condiciones: No posee.
	 * Pos-condiciones: La estructura queda inicializada. */
	public void Inicializar();
	
	/* Indica si la estructura tiene elementos o no (true: no tiene | false: tiene).
	 * Pre-condiciones: La estructura tiene que estar inicializada. 
	 * Pos-condiciones: La estructura no se ve modificada. */
	public boolean ArbolVacio();
	
	/* Agrega un libro a la estructura. Si el g�nero del libro existe, se incorpora a ese g�nero. 
	 * Si el g�nero no existe, se crea primero el g�nero y luego se incorpora el libro.
	 * Pre-condiciones: La estructura tiene que estar inicializada.
	 * Pos-condiciones: La estructura se ve modificada con un elemento en m�s. */
	public void AgregarLibro(Libro libro);
	
	/* Elimina un g�nero de la estructura y todos sus libros. 
	 * Reacomoda los restantes g�neros del cat�logo.
	 * Pre-condiciones: La estructura tiene que estar inicializada.
	 * Pos-condiciones: La estructura se ve modificada con un elemento en menos, si existe el g�nero. */
	public void EliminarGenero(Genero genero);
	
	/* Elimina uno o m�s libros de la estructura. Si el g�nero del libro no existe o el libro no existe en su g�nero, no hace nada.
	 * Si es el �nico libro, elimina tambi�n el g�nero.
	 * Pre-condiciones: La estructura tiene que estar inicializada.
	 * Pos-condiciones: La estructura se ve modificada, con tantas ocurrencias del libro existan, en menos, 
	 * si tanto el g�nero del libro como el libro existen. */
	public void EliminarLibro(Libro libro);
	
	/* Devuelve el G�nero que est� en la ra�z del cat�logo o nulo si el �rbol es nulo.
	 * Pre-condiciones: La estructura tiene que estar inicializada.
	 * Pos-condiciones: No posee. */
	public Genero ObtenerGenero();
	
	/* Devuelve el cat�logo de libros asociado al g�nero cuyo nombre es alfabéticamente anterior al género raíz, o nulo si no existe.
	 * Pre-condiciones: La estructura tiene que estar inicializada.
	 * Pos-condiciones: No posee. */
	public ABBTDACatalogo HijoIzq();
	
	/* Devuelve el cat�logo de libros asociado al g�nero cuyo nombre es alfabéticamente anterior al género raíz, o nulo si no existe.
	 * Pre-condiciones: La estructura tiene que estar inicializada.
	 * Pos-condiciones: No posee. */
	public ABBTDACatalogo HijoDer();
		
}