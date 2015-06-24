package implementaciones;

import tdas.ConjuntoTDA;

public class Conjunto implements ConjuntoTDA{
	
	private class NodoConj{
		Object valor;
		NodoConj sig;
	}
	
	NodoConj c;
	
	@Override
	public void inicializarConjunto() {
		c = null;
	}

	@Override
	public boolean conjuntoVacio() {
		return (c == null);
	}

	@Override
	public void agregar(Object o) {
		if(!this.pertenece(o)){
			NodoConj aux = new NodoConj();
			aux.valor = o;
			aux.sig = c;
			c = aux;
		}
	}

	@Override
	public Object elegir() {
		return c.valor;
	}

	@Override
	public void sacar(Object o) {
		if(c != null){
			if(c.valor.equals(o)){
				c = c.sig;
			}else{
				NodoConj aux = c;
				while(aux.sig != null && !aux.sig.valor.equals(o)){
					aux = aux.sig;
				}
				if(aux.sig != null)
					aux.sig = aux.sig.sig;
			}		
		}
	}

	@Override
	public boolean pertenece(Object o) {
		NodoConj aux = c;
		while((aux != null) && (!aux.valor.equals(o))){
			aux = aux.sig;
		}
		
		return (aux != null);
	}
}
