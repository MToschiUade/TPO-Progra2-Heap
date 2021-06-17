/**
 * 
 */
package impl;

import api.ColaPrioridadTDA;
import api.HeapMaxColaPrioridadTDA;

/**
 * @author mtoschi
 *
 */
public class ColaPrioridadHEAP implements ColaPrioridadTDA {

	HeapMaxColaPrioridadTDA cola;
	private int LONGITUD = 15;
	
	@Override
	public void InicializarCola() {
		cola = new HeapMaxIMPLE();
		cola.inicializarHeap(LONGITUD);
	}

	@Override
	public void AcolarPrioridad(int x, int prioridad) {
		cola.insertar(x, prioridad);
	}

	@Override
	public void Desacolar() {
		cola.extraerMax();
	}

	@Override
	public boolean ColaVacia() {
		return cola.vacio() ;
	}

	@Override
	public int Primero() {
		// TODO Auto-generated method stub
		return cola.obtenerMax();
	}

	@Override
	public int Prioridad() {
		// TODO Auto-generated method stub
		return cola.prioridad();
	}

}
