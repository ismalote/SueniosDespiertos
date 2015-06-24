package tdas;

public interface ConjuntoTDA {
	
	void inicializarConjunto();
	
	boolean conjuntoVacio();
	
	void agregar(Object o);
	
	Object elegir();
	
	void sacar(Object o);
	
	boolean pertenece(Object o);
}
