package implementaciones;

import tdas.PilaTDA;

public class Pila implements PilaTDA{
	
	private class NodoPila{
		Object valor;
		NodoPila sig;
	}
	
	NodoPila primero;
	
	@Override
	public void InicializarPila() {
		primero = null;
	}

	@Override
	public void Apilar(Object o) {
		NodoPila aux = new NodoPila();
		aux.valor=o;
		aux.sig=primero;
		primero=aux;
	}

	@Override
	public void Desapilar() {
		if(!PilaVacia())
			primero = primero.sig;
	}

	@Override
	public boolean PilaVacia() {
		return (primero == null);
	}

	@Override
	public Object Tope() {
		return primero.valor;
	}
	
}
