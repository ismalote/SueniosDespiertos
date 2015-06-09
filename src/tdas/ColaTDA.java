package tdas;

public interface ColaTDA {
	
	void InicializarCola();
	
	void Acolar(Object o);
	
	void Desacolar();
	
	boolean ColaVacia();
	
	Object Primero();
}
