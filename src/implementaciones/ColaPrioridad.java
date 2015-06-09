package implementaciones;

import tdas.ColaPrioridadTDA;

public class ColaPrioridad implements ColaPrioridadTDA{
	
	private class NodoColaPrio{
		Object valor;
		int prio;
		NodoColaPrio sig;
	}
	
	NodoColaPrio mayPri;

	@Override
	public void InicializarCola() {
		mayPri = null;
	}

	@Override
	public void AcolarPrioridad(Object o, int p) {
		NodoColaPrio nuevo = new NodoColaPrio();
		nuevo.valor = o;
		nuevo.prio = p;
		
		if(mayPri == null || p > mayPri.prio){
			nuevo.sig = mayPri;
			mayPri = nuevo;
		}
		else{
			NodoColaPrio aux = mayPri;
			
			while(aux.sig != null && aux.sig.prio >= p)
				aux = aux.sig;
			nuevo.sig = aux.sig;
			aux.sig = nuevo;
		}
	}

	@Override
	public void Desacolar() {
		mayPri = mayPri.sig;
	}

	@Override
	public Object Primero() {
		return mayPri.valor;
	}

	@Override
	public boolean ColaVacia() {
		return (mayPri == null);
	}

	@Override
	public int Prioridad() {
		return mayPri.prio;
	}

}
