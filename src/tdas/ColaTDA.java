package tdas;

public interface ColaTDA {
	
	void inicializarCola();
	
	void acolar(Object o);
	
	void desacolar();
	
	boolean colaVacia();
	
	Object primero();
}
