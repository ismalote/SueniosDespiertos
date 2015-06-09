package implementaciones;

import tdas.ColaTDA;

public class Cola implements ColaTDA{
	
	private class NodoCola{
		Object valor;
		NodoCola sig;
	}
	
	NodoCola primero;
	NodoCola ultimo;

	@Override
	public void InicializarCola() {
		primero = null;
	}

	@Override
	public void Acolar(Object o) {
		NodoCola aux = new NodoCola();
		aux.valor = o;
		aux.sig = null;
		
		if(ultimo != null)
			ultimo.sig = aux;
		ultimo = aux;
		
		if(primero == null)
			primero = ultimo;
	}

	@Override
	public void Desacolar() {
		primero = primero.sig;
		
		if(primero == null)
			ultimo = null;
	}

	@Override
	public boolean ColaVacia() {
		return (ultimo == null);
	}

	@Override
	public Object Primero() {
		return primero.valor;
	}
	
}
