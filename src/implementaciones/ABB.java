 package implementaciones;

import java.util.ArrayList;
import java.util.List;

import libros.Libro;
import enums.Genero;
import tdas.ABBTDA;

public class ABB implements ABBTDA{
	
	private class Nodo {
		Genero genero;
		NodoLibros libros;
		ABBTDA hijoIzq;
		ABBTDA hijoDer;
	}
	
	private class NodoLibros {
		Libro book;
		NodoLibros sig;
	}
	
	Nodo raiz;

	@Override
	public void Inicializar() {
		raiz=null;
	}

	@Override
	public Genero ObtenerGenero() {
		return raiz.genero;
	}

	@Override
	public ABBTDA HijoIzq() {
		return raiz.hijoIzq;
	}

	@Override
	public ABBTDA HijoDer() {
		return raiz.hijoDer;
	}

	@Override
	public boolean ArbolVacio() {
		return raiz==null;
	}
	
	@Override
	public List<Libro> Libros() {
		List<Libro> bookList = new ArrayList<Libro>();
		NodoLibros aux = raiz.libros;
		while(aux != null){
			bookList.add(aux.book);
			aux=aux.sig;
		}
		
		return bookList;
	}
	
	@Override
	public void AgregarGenero(Genero genero) {
		if(raiz==null){
			raiz = new Nodo();
			raiz.genero = genero;
			raiz.hijoIzq = new ABB();
			raiz.hijoIzq.Inicializar();
			raiz.hijoDer = new ABB();
			raiz.hijoDer.Inicializar();
		}else if(raiz.genero.compareTo(genero) < 0){
			raiz.hijoDer.AgregarGenero(genero);
		}else if(raiz.genero.compareTo(genero) > 0){
			raiz.hijoIzq.AgregarGenero(genero);
		}
	}

	@Override
	public void EliminarGenero(Genero genero) {
		if(raiz != null){
			if(raiz.genero.equals(genero) && raiz.hijoDer.ArbolVacio() && raiz.hijoIzq.ArbolVacio()){
				raiz = null;
			}else if (raiz.genero.equals(genero) && !raiz.hijoIzq.ArbolVacio()){
				raiz.genero = this.mayor(raiz.hijoIzq);
				raiz.hijoIzq.EliminarGenero(raiz.genero);
			}else if (raiz.genero.equals(genero) && !raiz.hijoDer.ArbolVacio()){
				raiz.genero = this.menor(raiz.hijoDer);
			}else if (raiz.genero.compareTo(genero) < 0){
				raiz.hijoDer.EliminarGenero(genero);
			}else{
				raiz.hijoIzq.EliminarGenero(genero);
			}
		}
	}

	public void AgregarLibro(Libro libro) {
		if(raiz.genero.equals(libro.getGenero())){
			NodoLibros aux = new NodoLibros();
			aux.book = libro;
			aux.sig = raiz.libros;
			raiz.libros = aux;
		}else if(!raiz.hijoDer.ArbolVacio() && raiz.genero.compareTo(libro.getGenero()) < 0){
			raiz.hijoDer.AgregarLibro(libro);
		}else if(!raiz.hijoIzq.ArbolVacio() && raiz.genero.compareTo(libro.getGenero()) > 0){
			raiz.hijoIzq.AgregarLibro(libro);
		}else{
			System.out.println("El género no existe");
			return;
		}
	}

	public void EliminarLibro(Libro libro) {
		if(raiz.genero.equals(libro.getGenero())){
			borrarLibro(libro);
		}else if(!raiz.hijoDer.ArbolVacio() && raiz.genero.compareTo(libro.getGenero()) < 0){
			raiz.hijoDer.EliminarLibro(libro);
		}else if(!raiz.hijoIzq.ArbolVacio() && raiz.genero.compareTo(libro.getGenero()) > 0){
			raiz.hijoIzq.EliminarLibro(libro);
		}else{
			System.out.println("El libro no existe para ser eliminado");
			return;
		}
	}
	
	private void borrarLibro(Libro libro) {
		NodoLibros aux = raiz.libros;
		if(aux.book.equals(libro)){
			raiz.libros = aux.sig;
		}else{
			while(!aux.sig.book.equals(libro) && aux.sig != null){
				aux=aux.sig;
			}
			if(aux.sig != null){
				aux.sig = aux.sig.sig;
			}else{
				System.out.println("El libro no existe para ser eliminado");
				return;
			}
		}
	}
	
	private Genero mayor(ABBTDA a) {
		if (a.HijoDer().ArbolVacio())
			return a.ObtenerGenero();
		else
			return mayor(a.HijoDer());
	}
	
	private Genero menor(ABBTDA a) {
		if (a.HijoIzq().ArbolVacio())
			return a.ObtenerGenero();
		else
			return menor(a.HijoIzq());
	}
	
}
