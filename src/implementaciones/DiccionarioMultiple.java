package implementaciones;

import implementaciones.Conjunto;
import tdas.ConjuntoTDA;
import tdas.DiccionarioMultipleTDA;

public class DiccionarioMultiple implements DiccionarioMultipleTDA{
	
	private class NodoDic{
		Object clave;
		NodoV valores;
		NodoDic sig;
	}
	
	private class NodoV{
		Object valor;
		NodoV sig;
	}
	
	NodoDic priClave;

	@Override
	public void InicializarDiccionario() {
		priClave = null;
	}

	@Override
	public void Agregar(Object clave, Object valor) {
		NodoDic aux = priClave;
		
		while(aux != null && !aux.clave.equals(clave)){
			aux = aux.sig;
		}
		
		if(aux != null){
			NodoV auxV = aux.valores;
			
			while(auxV != null && !auxV.valor.equals(valor)){
				auxV = auxV.sig;
			}
			
			if(auxV == null){
				auxV = new NodoV();
				auxV.valor = valor;
				auxV.sig = aux.valores;
				aux.valores = auxV;
			}
		}else{
			aux = new NodoDic();
			aux.clave = clave;
			aux.valores = new NodoV();
			aux.valores.valor = valor;
			aux.valores.sig = null;
			aux.sig = priClave;
			priClave = aux;
		}

		
	}

	@Override
	public void Eliminar(Object clave) {
		NodoDic aux = priClave;
		
		if(aux.clave.equals(clave)){
			aux = aux.sig;
			priClave = aux;
		}else{
			while(!aux.sig.clave.equals(clave)){
				aux = aux.sig;
			}
			aux.sig = aux.sig.sig;
		}
	}

	@Override
	public void EliminarValor(Object clave, Object valor) {
		NodoDic aux = priClave;
		
		while(!aux.clave.equals(clave)){
			aux = aux.sig;
		}
		
		NodoV auxV = aux.valores;
		
		if(auxV.valor.equals(valor)){
			auxV = auxV.sig;
			aux.valores = auxV;
			if(aux.valores == null){
				Eliminar(aux.clave);
			}
		}else{
			while(!auxV.sig.valor.equals(valor)){
				auxV = auxV.sig;
			}
			auxV.sig = auxV.sig.sig;
		}
		
	}

	@Override
	public ConjuntoTDA Recuperar(Object clave) {
		ConjuntoTDA conjunto = new Conjunto();
		conjunto.InicializarConjunto();
		NodoDic aux = priClave;
		
		while(!aux.clave.equals(clave)){
			aux = aux.sig;
		}
		
		NodoV auxV = aux.valores;
		while(auxV != null){
			conjunto.Agregar(auxV.valor);
			auxV = auxV.sig;
		}
		
		return conjunto;
	}

	@Override
	public ConjuntoTDA Claves() {
		ConjuntoTDA conjunto = new Conjunto();
		conjunto.InicializarConjunto();
		NodoDic aux = priClave;
		
		while(aux != null){
			conjunto.Agregar(aux.clave);
			aux = aux.sig;
		}
		
		return conjunto;
	}

	@Override
	public boolean DiccionarioVacio() {
		return (priClave == null);
	}
	
}
