package tdas;

public interface ConjuntoTDA {
	
	void InicializarConjunto();
	
	boolean ConjuntoVacio();
	
	void Agregar(Object o);
	
	Object Elegir();
	
	void Sacar(Object o);
	
	boolean Pertenece(Object o);
}
