package tdas;

import TDA.ConjuntoTDA;
import clases.Libro;

/* Es una estructura que posee una colección de nombres de géneros (claves), 
 * y cada una de ellas tiene asociado un grupo de libros (valores). Los nombres de géneros son únicos. */
public interface DiccionarioMultipleTDA {

	/* Inicializa el diccionario. 
	 * Pre-condiciones: ninguna. 
	 * Pos-condiciones: la estructura queda inicializada. */
	void inicializarDiccionario();

	/* Dado un nombre de un género (clave) y un libro (valor), agrega al diccionario 
	 * el libro quedando asociado a un nombre de género. Si el nombre del género no 
	 * existe, se agrega una clave con el nombre del género, y luego se asocia el 
	 * libro como valor de dicha clave. Un mismo nombre del género puede 
	 * tener asociado una colección de libros, pero esos libros no se pueden repetir. 
	 * Pre-condiciones: la estructura tiene que estar inicializada. 
	 * Pos-condiciones: la estructura se ve modificada con un libro más si existe 
	 * el nombre del género. Si el libro existe, no se ve modificada la estructura. 
	 * En caso de no existir el nombre del género, la estructura se ve modificada 
	 * con un nombre de género más y un libro asociado a éste. */
	void agregar(String clave, Libro valor);

	/* Dado un nombre de un género elimina todos los libros asociados al nombre del 
	 * género, y por consiguiente elimina el nombre del género, ya que el diccionario 
	 * no puede contener algún nombre de género sin libros asociados. 
	 * Pre-condiciones: la estructura debe estar inicializada. 
	 * Pos-condiciones: la estructura se ve modificada con un nombre de género menos 
	 * si el nombre del género existe. Si el nombre del género no existe, no hace nada. */
	void eliminar(String clave);

	/* Dado un nombre de un género y un libro se elimina el libro asociado al nombre del 
	 * género. En caso de que el nombre del género o el libro no existan, no se hace nada. 
	 * Si al eliminar el libro el nombre del género no tiene otros libros asociados, se 
	 * debe eliminar el nombre del género.
	 * Pre-condiciones: la estructura debe estar inicializada. 
	 * Pos-condiciones: la estructura se ve modificada con un libro menos si el nombre 
	 * del género y el libro asociado a dicho nombre de género existen.  */
	void eliminarValor(String clave, Libro valor);

	/* Dado un nombre de un género devuelve el conjunto de libros asociados al mismo. 
	 * Si el nombre del género dado no pertenece al diccionario, se devuelve un conjunto vacío. 
	 * Pre-condiciones: la estructura debe estar inicializada.	
	 * Pos-condiciones: la estructura no se ve modificada. */
	ConjuntoTDA<Libro> recuperar(String clave);

	/* Devuelve un conjunto con todos los nombres de géneros definidos en el diccionario. 
	 * Pre-condiciones: la estructura debe estar inicializada. 
	 * Pos-condiciones: la estructura no se ve modificada. */
	ConjuntoTDA<String> claves();

	/* Verifica si el diccionario posee o no nombres de géneros (true: vacío; false: no vacío). 
	 * Pre-condiciones: la estructura debe estar inicializada. 
	 * Pos-condiciones: la estructura no se ve modificada. */
	boolean diccionarioVacio();
	
}
