package implementaciones;

import tdas.ABBTDACatalogo;
import clases.Genero;
import clases.Libro;

public class ABBCatalogo implements ABBTDACatalogo {

	private class Nodo {
		Genero genero;

		ABBTDACatalogo hijoIzq;

		ABBTDACatalogo hijoDer;
	}

	Nodo raiz;

	@Override
	public void inicializar() {
		raiz = null;
	}

	@Override
	public boolean arbolVacio() {
		return raiz == null;
	}

	@Override
	public void agregarLibro(Libro libro) {

		int codigoComparacion = 0;
		if (!this.arbolVacio()) {
			codigoComparacion = raiz.genero.getNombre().compareToIgnoreCase(libro.getGenero());
		}
		/**
		 * Si es el primer Libro o si el Género del Libro es el mismo que el de este Arbol
		 */
		if (this.arbolVacio() || codigoComparacion == 0) {
			if (this.arbolVacio()) {
				raiz = new Nodo();
				raiz.genero = new Genero(libro.getGenero());
				raiz.genero.addLibro(libro);
				raiz.hijoIzq = new ABBCatalogo();
				raiz.hijoIzq.inicializar();
				raiz.hijoDer = new ABBCatalogo();
				raiz.hijoDer.inicializar();
			} else {
				raiz.genero.addLibro(libro);
			}
		} else if (codigoComparacion < 0) { // Si el Género del Libro es alfabéticamente mayor al Genero de este Arbol
			raiz.hijoDer.agregarLibro(libro);
		} else if (codigoComparacion > 0) { // Si el Género del Libro es alfabéticamente menor al Genero de este Arbol
			raiz.hijoIzq.agregarLibro(libro);
		}
	}

	@Override
	public void eliminarGenero(Genero genero) {
		if (!this.arbolVacio()) {
			int codigoComparacion = raiz.genero.getNombre().compareToIgnoreCase(genero.getNombre());
			/**
			 * Si el Género fue hallado y estamos en un Nodo hoja
			 */
			if (codigoComparacion == 0 && raiz.hijoDer.arbolVacio() && raiz.hijoIzq.arbolVacio()) {
				raiz = null;
			}
			/**
			 * Si el Género fue hallado y tiene al menos un hijo a su izquierda para que lo reemplace
			 */
			else if (codigoComparacion == 0 && !raiz.hijoIzq.arbolVacio()) {
				raiz.genero = this.mayor(raiz.hijoIzq);
				raiz.hijoIzq.eliminarGenero(raiz.genero);
			}
			/**
			 * Si el Género fue hallado y tiene al menos un hijo a su derecha para que lo reemplace
			 */
			else if (codigoComparacion == 0 && !raiz.hijoDer.arbolVacio()) {
				raiz.genero = this.menor(raiz.hijoDer);
				raiz.hijoDer.eliminarGenero(raiz.genero);
			}
			/**
			 * Si el Género buscado es alfabéticamente mayor al Género del Arbol lo buscamos en su hijo derecho
			 */
			else if (codigoComparacion < 0) {
				raiz.hijoDer.eliminarGenero(genero);
			}
			/**
			 * Si el Género buscado es alfabéticamente menor al Género del Arbol lo buscamos en su hijo izquierdo
			 */
			else {
				raiz.hijoIzq.eliminarGenero(genero);
			}
		}
	}

	@Override
	public void eliminarLibro(Libro libro) {
		if (!this.arbolVacio()) {
			int codigoComparacion = raiz.genero.getNombre().compareToIgnoreCase(libro.getGenero());
			/**
			 * Si fue hallado el Género del Libro
			 * 
			 */
			if (codigoComparacion == 0) {
				raiz.genero.borrarLibro(libro);
				if (raiz.genero.getLibros().colaVacia()) { // Si el Género no tiene mas libros
					eliminarGenero(raiz.genero);
				}
			} else if (codigoComparacion < 0) { // Si el Genero del Libro es menor alfabéticamente
				raiz.hijoDer.eliminarLibro(libro);

			} else { // Si el Genero del Libro es mayor alfabéticamente
				raiz.hijoIzq.eliminarLibro(libro);
			}
		}
	}

	@Override
	public Genero obtenerGenero() {
		return raiz.genero;
	}

	@Override
	public ABBTDACatalogo hijoIzq() {
		return raiz.hijoIzq;
	}

	@Override
	public ABBTDACatalogo hijoDer() {
		return raiz.hijoDer;
	}

	/**
	 * @param arbol
	 * @return el menor {@link Genero} alfabéticamente
	 */
	private Genero menor(ABBTDACatalogo arbol) {
		if (arbol.hijoIzq().arbolVacio())
			return arbol.obtenerGenero();
		else
			return menor(arbol.hijoIzq());
	}

	/**
	 * @param arbol
	 * @return el mayor {@link Genero} alfabéticamente
	 */
	private Genero mayor(ABBTDACatalogo arbol) {
		if (arbol.hijoDer().arbolVacio())
			return arbol.obtenerGenero();
		else
			return mayor(arbol.hijoDer());
	}

}
