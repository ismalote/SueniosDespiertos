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
		NodoDic aux = priClave;

		while (aux != null && !aux.clave.equals(clave)) {
			aux = aux.sig;
		}

		if (aux != null) {
			NodoV auxV = aux.valores;

			while (auxV != null && !auxV.valor.equals(valor)) {
				auxV = auxV.sig;
			}

			if (auxV == null) {
				auxV = new NodoV();
				auxV.valor = valor;
				auxV.sig = aux.valores;
				aux.valores = auxV;
			}
		} else {
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

		if (aux.clave.equals(clave)) {
			aux = aux.sig;
			priClave = aux;
		} else {
			while (!aux.sig.clave.equals(clave)) {
				aux = aux.sig;
			}
			aux.sig = aux.sig.sig;
		}
	}

	@Override
	public void eliminarValor(String clave, Libro valor) {
		NodoDic aux = priClave;

		while (!aux.clave.equals(clave)) {
			aux = aux.sig;
		}

		NodoV auxV = aux.valores;

		if (auxV.valor.equals(valor)) {
			auxV = auxV.sig;
			aux.valores = auxV;
			if (aux.valores == null) {
				eliminar(aux.clave);
			}
		} else {
			while (!auxV.sig.valor.equals(valor)) {
				auxV = auxV.sig;
			}
			auxV.sig = auxV.sig.sig;
		}

	}

	@Override
	public ConjuntoTDA<Libro> recuperar(String clave) {
		ConjuntoTDA<Libro> conjunto = new Conjunto<Libro>();
		conjunto.inicializarConjunto();
		NodoDic aux = priClave;

		while (!aux.clave.equals(clave)) {
			aux = aux.sig;
		}

		NodoV auxV = aux.valores;
		while (auxV != null) {
			conjunto.agregar(auxV.valor);
			auxV = auxV.sig;
		}

		return conjunto;
	}

	@Override
	public ConjuntoTDA<String> claves() {
		ConjuntoTDA<String> conjunto = new Conjunto<String>();
		conjunto.inicializarConjunto();
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

}
