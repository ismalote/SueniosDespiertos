package tdas;

import java.util.List;

import libros.Libro;
import enums.Genero;

public interface ABBTDA {
	
	void Inicializar();
	
	Genero ObtenerGenero();
	
	ABBTDA HijoIzq();
	
	ABBTDA HijoDer();
	
	boolean ArbolVacio();
	
	List<Libro> Libros();
	
	void AgregarGenero(Genero genero);
	
	void EliminarGenero(Genero genero);

	void AgregarLibro(Libro libro);

	void EliminarLibro(Libro libro);
	
}
