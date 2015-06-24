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
	public void inicializarCola() {
		primero = null;
	}

	@Override
	public void acolar(Object o) {
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
	public void desacolar() {
		primero = primero.sig;
		
		if(primero == null)
			ultimo = null;
	}

	@Override
	public boolean colaVacia() {
		return (ultimo == null);
	}

	@Override
	public Object primero() {
		return primero.valor;
	}
	
}
