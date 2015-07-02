package implementaciones;

import clases.Genero;
import clases.Libro;
import tdas.ABBTDACatalogo;

public class ABBCatalogo implements ABBTDACatalogo{
	
	private class Nodo {
		Genero genero;
		ABBTDACatalogo hijoIzq;
		ABBTDACatalogo hijoDer;
	}
	
	Nodo raiz;
	
	@Override
	public void inicializar() {
		raiz=null;
		
	}

	@Override
	public boolean arbolVacio() {
		return raiz==null;
	}

	@Override
	public void agregarLibro(Libro libro) {
		if(raiz==null || raiz.genero.getNombre().equals(libro.getGenero())){
			if(raiz == null){
				raiz = new Nodo();
				raiz.genero = new Genero(libro.getGenero());
				raiz.genero.addLibro(libro);
				raiz.hijoIzq = new ABBCatalogo();
				raiz.hijoIzq.inicializar();
				raiz.hijoDer = new ABBCatalogo();
				raiz.hijoDer.inicializar();
			}else{
				raiz.genero.addLibro(libro);
			}
		}else if(raiz.genero.getNombre().compareTo(libro.getGenero()) < 0){
			raiz.hijoDer.agregarLibro(libro);
		}else if(raiz.genero.getNombre().compareTo(libro.getGenero()) > 0){
			raiz.hijoIzq.agregarLibro(libro);
		}
		
	}

	@Override
	public void eliminarGenero(Genero genero) {
		if(raiz != null){
			if(raiz.genero.getNombre().equals(genero.getNombre()) && raiz.hijoDer.arbolVacio() && raiz.hijoIzq.arbolVacio()){
				raiz = null;
			}else if (raiz.genero.getNombre().equals(genero.getNombre()) && !raiz.hijoIzq.arbolVacio()){
				raiz.genero = this.mayor(raiz.hijoIzq);
				raiz.hijoIzq.eliminarGenero(raiz.genero);
			}else if (raiz.genero.getNombre().equals(genero.getNombre()) && !raiz.hijoDer.arbolVacio()){
				raiz.genero = this.menor(raiz.hijoDer);
				raiz.hijoDer.eliminarGenero(raiz.genero);
			}else if (raiz.genero.getNombre().compareTo(genero.getNombre()) < 0){
				raiz.hijoDer.eliminarGenero(genero);
			}else{
				raiz.hijoIzq.eliminarGenero(genero);
			}
		}
	}

	@Override
	public void eliminarLibro(Libro libro) {
		if(raiz.genero.getNombre().equals(libro.getGenero())){
			raiz.genero.borrarLibro(libro);
			if(raiz.genero.getLibros().colaVacia())
				eliminarGenero(raiz.genero);
		}else if(!raiz.hijoDer.arbolVacio() && raiz.genero.getNombre().compareTo(libro.getGenero()) < 0){
			raiz.hijoDer.eliminarLibro(libro);
		}else if(!raiz.hijoIzq.arbolVacio() && raiz.genero.getNombre().compareTo(libro.getGenero()) > 0){
			raiz.hijoIzq.eliminarLibro(libro);
		}else{
			System.out.println("El libro no existe para ser eliminado.");
			return;
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
	
	private Genero menor(ABBTDACatalogo a) {
		if (a.hijoIzq().arbolVacio())
			return a.obtenerGenero();
		else
			return menor(a.hijoIzq());
	}

	private Genero mayor(ABBTDACatalogo a) {
		if (a.hijoDer().arbolVacio())
			return a.obtenerGenero();
		else
			return mayor(a.hijoDer());
	}

}
