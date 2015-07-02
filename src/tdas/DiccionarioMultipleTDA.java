package tdas;

import TDA.ConjuntoTDA;
import clases.Libro;

/* Es una estructura que posee un conjunto de géneros, y cada una de ellos posee asociado un conjunto de libros. Los géneros son únicos. */
public interface DiccionarioMultipleTDA {

	/* Inicializa el diccionario.
	Pre-condiciones: ninguna.
	Pos-condiciones: la estructura queda inicializada.*/
	void inicializarDiccionario();

	/* Dado un género y un libro, agrega al diccionario el libro quedando asociado a un género. Si el género no existe, se agrega el género y luego el libro a dicho género. Un mismo género puede tener asociado un conjunto de libros, pero esos libros no se pueden repetir.
	Pre-condiciones: la estructura tiene que estar inicializada.
	Pos-condiciones: la estructura se ve modificada con un libro más. Si el libro existe, no se ve modificada la estructura. */	
	void agregar(String clave, Libro valor);

	/* Dado un género elimina todos los libros asociados al género, y por consiguiente elimina al género, ya que el diccionario no puede contener géneros sin libros asociados. 
	Pre-condiciones: la estructura debe estar inicializada.
	Pos-condiciones: la estructura se ve modificada con un género menos si el género existe. Si el género no existe, no hace nada. */
	void eliminar(String clave);

	/* Dado un género y un libro se elimina el libro asociado al género. En caso de que el género o el libro no existan no se hace nada. Si al
	eliminar el libro, el género no tiene otros libros asociados se debe eliminar el género. 
	Pre-condiciones: la estructura debe estar inicializada.
	Pos-condiciones: la estructura se ve modificada con un libro menos si el género y el libro asociado a dicho género existen. */
	void eliminarValor(String clave, Libro valor);

	/* Dado un género devuelve el conjunto de libros asociados al mismo. Si el género dado no pertenece al diccionario, se devuelve un conjunto vacío. 
	Pre-condiciones: la estructura debe estar inicializada.
	Pos-condiciones: la estructura no se ve modificada. */
	ConjuntoTDA<Libro> recuperar(String clave);

	/* Devuelve un conjunto con todas los géneros definidas en el diccionario.
	Pre-condiciones: la estructura debe estar inicializada.
	Pos-condiciones: la estructura no se ve modificada. */
	ConjuntoTDA<String> claves();

	/* Verifica si el diccionario posee o no géneros (true: vacío; false: no vacío).
	Pre-condiciones: la estructura debe estar inicializada.
	Pos-condiciones: la estructura no se ve modificada. */
	boolean diccionarioVacio();
	
}
