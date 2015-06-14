package implementaciones;

import clases.Genero;
import clases.Libro;
import tdas.ABBTDACatalogo;

public class ABBCatalogo implements ABBTDACatalogo{
	
	private class Nodo {
		Genero genero;
		ABBCatalogo hijoIzq;
		ABBCatalogo hijoDer;
	}
	
	Nodo raiz;
	
	@Override
	public void Inicializar() {
		raiz=null;
		
	}

	@Override
	public boolean ArbolVacio() {
		return raiz==null;
	}

	@Override
	public void AgregarLibro(Libro libro) {
		if(raiz==null || raiz.genero.getNombre().equals(libro.getGenero())){
			if(raiz == null){
				raiz = new Nodo();
				raiz.genero = new Genero(libro.getGenero());
				raiz.genero.getLibros().Acolar(libro);
				raiz.hijoIzq = new ABBCatalogo();
				raiz.hijoIzq.Inicializar();
				raiz.hijoDer = new ABBCatalogo();
				raiz.hijoDer.Inicializar();
			}else{
				raiz.genero.getLibros().Acolar(libro);
			}
		}else if(raiz.genero.getNombre().compareTo(libro.getGenero()) < 0){
			raiz.hijoDer.AgregarLibro(libro);
		}else if(raiz.genero.getNombre().compareTo(libro.getGenero()) > 0){
			raiz.hijoIzq.AgregarLibro(libro);
		}
		
	}

	@Override
	public void EliminarGenero(Genero genero) {
		if(raiz != null){
			if(raiz.genero.getNombre().equals(genero.getNombre()) && raiz.hijoDer.ArbolVacio() && raiz.hijoIzq.ArbolVacio()){
				raiz = null;
			}else if (raiz.genero.getNombre().equals(genero.getNombre()) && !raiz.hijoIzq.ArbolVacio()){
				raiz.genero = this.mayor(raiz.hijoIzq);
				raiz.hijoIzq.EliminarGenero(raiz.genero);
			}else if (raiz.genero.getNombre().equals(genero.getNombre()) && !raiz.hijoDer.ArbolVacio()){
				raiz.genero = this.menor(raiz.hijoDer);
				raiz.hijoDer.EliminarGenero(raiz.genero);
			}else if (raiz.genero.getNombre().compareTo(genero.getNombre()) < 0){
				raiz.hijoDer.EliminarGenero(genero);
			}else{
				raiz.hijoIzq.EliminarGenero(genero);
			}
		}
	}

	@Override
	public void EliminarLibro(Libro libro) {
		if(raiz.genero.getNombre().equals(libro.getGenero())){
			raiz.genero.borrarLibro(libro);
			if(raiz.genero.getLibros().ColaVacia())
				EliminarGenero(raiz.genero);
		}else if(!raiz.hijoDer.ArbolVacio() && raiz.genero.getNombre().compareTo(libro.getGenero()) < 0){
			raiz.hijoDer.EliminarLibro(libro);
		}else if(!raiz.hijoIzq.ArbolVacio() && raiz.genero.getNombre().compareTo(libro.getGenero()) > 0){
			raiz.hijoIzq.EliminarLibro(libro);
		}else{
			System.out.println("El libro no existe para ser eliminado");
			return;
		}
		
	}

	@Override
	public Genero ObtenerGenero() {
		return raiz.genero;
	}

	@Override
	public ABBTDACatalogo HijoIzq() {
		return raiz.hijoIzq;
	}

	@Override
	public ABBTDACatalogo HijoDer() {
		return raiz.hijoDer;
	}
	
	private Genero menor(ABBTDACatalogo a) {
		if (a.HijoIzq().ArbolVacio())
			return a.ObtenerGenero();
		else
			return menor(a.HijoIzq());
	}

	private Genero mayor(ABBTDACatalogo a) {
		if (a.HijoDer().ArbolVacio())
			return a.ObtenerGenero();
		else
			return mayor(a.HijoDer());
	}

}
