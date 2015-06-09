package tdas;

public interface ColaPrioridadTDA {
	
	void InicializarCola();
	
	void AcolarPrioridad(Object o, int p);
	
	void Desacolar();
	
	Object Primero();
	
	boolean ColaVacia();
	
	int Prioridad();
}
