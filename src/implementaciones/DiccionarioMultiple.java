package implementaciones;

import tdas.DiccionarioMultipleTDA;
import Implementaciones.Conjunto;
import TDA.ConjuntoTDA;
import clases.Libro;

public class DiccionarioMultiple implements DiccionarioMultipleTDA {

	private class NodoDic {
		String clave;

		NodoV valores;

		NodoDic sig;
	}

	private class NodoV {
		Libro valor;

		NodoV sig;
	}

	NodoDic priClave;

	@Override
	public void inicializarDiccionario() {
		priClave = null;
	}

	@Override
	public void agregar(String clave, Libro valor) {

		/**
		 * Buscamos un Género en donde agregar el Libro
		 */
		NodoDic aux = buscarGenero(clave);

		if (aux != null) { // Si Encontramos un Género
			NodoV auxV = buscarLibro(aux, valor);

			/**
			 * Solo si no existe lo agregamos, si existe lo ignoramos
			 */
			if (auxV == null) {
				auxV = new NodoV();
				auxV.valor = valor;
				auxV.sig = aux.valores;
				aux.valores = auxV;
			}
		} else { // Si no existe el Género lo creamos con el nuevo Libro
			aux = new NodoDic();
			aux.clave = clave;
			aux.valores = new NodoV();
			aux.valores.valor = valor;
			aux.valores.sig = null;
			aux.sig = priClave;
			priClave = aux;
		}

	}

	@Override
	public void eliminar(String clave) {
		NodoDic aux = priClave;

		/**
		 * Verificamos si se quiere eliminar el primer Género
		 */
		if (aux.clave.equals(clave)) {
			aux = aux.sig;
			priClave = aux;
		} else {
			/**
			 * Iteramos para ubicar el Género buscado y eliminarlo
			 */
			while (!aux.sig.clave.equals(clave)) {
				aux = aux.sig;
			}
			aux.sig = aux.sig.sig;
		}
	}

	@Override
	public void eliminarValor(String clave, Libro valor) {
		NodoDic aux = priClave;

		/**
		 * Nos posicionamos en el Género del libro
		 */
		aux = buscarGenero(clave);

		NodoV auxV = aux.valores;
		/**
		 * Verificamos si se quiere eliminar el primer Libro
		 */
		if (auxV.valor.equals(valor)) {
			auxV = auxV.sig;
			aux.valores = auxV;
			if (aux.valores == null) { // Si el Género no tiene mas Libros
				eliminar(aux.clave);
			}
		} else {
			/**
			 * Iteramos para ubicar el Libro buscado y eliminarlo
			 */
			while (!auxV.sig.valor.equals(valor)) {
				auxV = auxV.sig;
			}
			auxV.sig = auxV.sig.sig;
		}

	}

	@Override
	public ConjuntoTDA<Libro> recuperar(String clave) {
		/**
		 * Crea un conjunto
		 */
		ConjuntoTDA<Libro> conjunto = new Conjunto<Libro>();
		conjunto.inicializarConjunto();

		NodoDic aux = priClave;

		/**
		 * Ubicamos el Género buscado
		 */
		aux = buscarGenero(clave);

		/**
		 * Recorremos los Libros para agregarlos al conjunto
		 */
		NodoV auxV = aux.valores;
		while (auxV != null) {
			conjunto.agregar(auxV.valor);
			auxV = auxV.sig;
		}

		return conjunto;
	}

	@Override
	public ConjuntoTDA<String> claves() {
		/**
		 * Creamos un Conjunto
		 */
		ConjuntoTDA<String> conjunto = new Conjunto<String>();
		conjunto.inicializarConjunto();

		/**
		 * Recorremos los Géneros para agregarlos al conjunto
		 */
		NodoDic aux = priClave;
		while (aux != null) {
			conjunto.agregar(aux.clave);
			aux = aux.sig;
		}

		return conjunto;
	}

	@Override
	public boolean diccionarioVacio() {
		return (priClave == null);
	}

	private NodoDic buscarGenero(String genero) {
		NodoDic aux = priClave;

		/**
		 * Buscamos un Género
		 */
		while (aux != null && !aux.clave.equals(genero)) {
			aux = aux.sig;
		}
		return aux;
	}

	private NodoV buscarLibro(NodoDic aux, Libro valor) {
		NodoV auxV = aux.valores;

		/**
		 * Buscamos si el Libro existe
		 */
		while (auxV != null && !auxV.valor.equals(valor)) {
			auxV = auxV.sig;
		}
		return auxV;
	}

}
