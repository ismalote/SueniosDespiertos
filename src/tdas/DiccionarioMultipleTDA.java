package tdas;

import TDA.ConjuntoTDA;
import clases.Libro;

public interface DiccionarioMultipleTDA {

	void inicializarDiccionario();

	void agregar(String clave, Libro valor);

	void eliminar(String clave);

	void eliminarValor(String clave, Libro valor);

	ConjuntoTDA<Libro> recuperar(String clave);

	ConjuntoTDA<String> claves();

	boolean diccionarioVacio();
}
