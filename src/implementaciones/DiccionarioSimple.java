package implementaciones;

import tdas.ConjuntoTDA;
import tdas.DiccionarioSimpleTDA;

public class DiccionarioSimple implements DiccionarioSimpleTDA{
	
	private class NodoDic{
		Object clave;
		Object valor;
		NodoDic sig;
	}
	
	NodoDic primero;

	@Override
	public void InicializarDiccionario() {
		primero = null;
	}

	@Override
	public void Agregar(Object clave, Object valor) {
		NodoDic aux = primero;
		
		while(aux != null && !aux.clave.equals(valor)){
			aux = aux.sig;
		}
		
		if(aux == null){
			aux = new NodoDic();
			aux.clave = clave;
			aux.valor = valor;
			aux.sig = primero;
			primero = aux;
		}else{
			aux.valor = valor;
		}
	}

	@Override
	public void Eliminar(Object clave) {
		NodoDic aux = primero;
		
		while(!aux.sig.clave.equals(clave)){
			aux = aux.sig;
		}
		
		aux.sig = aux.sig.sig;
	}

	@Override
	public Object Recuperar(Object clave) {
		NodoDic aux = primero;
		
		while(!aux.clave.equals(clave)){
			aux = aux.sig;
		}
		
		return aux.valor;
	}

	@Override
	public ConjuntoTDA Claves() {
		ConjuntoTDA conjunto = new Conjunto();
		conjunto.InicializarConjunto();
		NodoDic aux = primero;
		
		while(aux != null){
			conjunto.Agregar(aux.clave);
			aux = aux.sig;
		}
		
		return conjunto;
	}

	@Override
	public boolean DiccionarioVacio() {
		return (primero == null);
	}
	
	
}
