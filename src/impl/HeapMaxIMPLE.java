package impl;

import api.HeapMaxTDA;

public class HeapMaxIMPLE implements HeapMaxTDA {

	// con estas variables estaticas indico que obtengo de la matriz si la prioridad
	// o el valor del elemento
	private static int prioridad = 0;
	private static int elemento = 1;
	private int tamano;
	private int maximo;
	private int heap[][];

	@Override
	public void inicializarHeap(int cantidadDeElementos) {
		// Esto es para crear un heap con la cantidad de elementos y el subindice con 2
		// es para que
		// utilizando "prioridad" o "elemento" se obtenga dicho valor
		heap = new int[cantidadDeElementos][2];
		// tamaño o cantidad de elementos actuales
		tamano = 0;
		// Cantidad maxima de elementos que soporta el heap
		maximo = cantidadDeElementos;
	}

	@Override
	public int obtenerMax() {
		// retorna de la primer posicion el elemento
		return heap[0][elemento];
	}

	@Override
	public void insertar(int valor, int prioridad) {
		// En caso de que se exceda el maximo
		if (tamano >= maximo) {
			System.out.println("No se puede insetar elemento nuevo. Limite alcanzado!");
			return;
		}
		// sino inserta normalmente
		// inserta en la ultima posición disponible
		heap[tamano][HeapMaxIMPLE.prioridad] = prioridad;
		heap[tamano][HeapMaxIMPLE.elemento] = valor;
		// sumador que desplaza el tamaño actual, indicando que hay un elemento nuevo en
		// el heap
		tamano++;

		// obtenemos la posicion donde se guardo el nuevo elemento insertado
		int posicion = tamano - 1;

		// mientras que la posición sea mayor a 0
		// verificamos si el valor de la pririoridad es mayor al que seria padre
		// en cuyo caso cambiamos de posición los elementos
		while (posicion > 0 && heap[padre(posicion)][HeapMaxIMPLE.prioridad] < heap[posicion][HeapMaxIMPLE.prioridad]) {
			// heap[padre(posicion)][HeapMaxIMPLE.prioridad] nos da de la matriz heap la
			// prioridad del elemento padre
			// heap[posicion][HeapMaxIMPLE.prioridad] nos da de la matriz heap la prioridad
			// del elemento insertado

			// Guardo temporalmente la prioridad ingresada
			// Guardo temporalmente el valor ingresado
			int auxPrioridad = heap[posicion][HeapMaxIMPLE.prioridad];
			int auxValor = heap[posicion][HeapMaxIMPLE.elemento];
			// Se remplaza el elemento y su prioridad con la de su padre
			heap[posicion][HeapMaxIMPLE.prioridad] = heap[padre(posicion)][HeapMaxIMPLE.prioridad];
			heap[posicion][HeapMaxIMPLE.elemento] = heap[padre(posicion)][HeapMaxIMPLE.elemento];
			// Se remplaza el elemento y la prioridad del padre por
			heap[padre(posicion)][HeapMaxIMPLE.prioridad] = auxPrioridad;
			heap[padre(posicion)][HeapMaxIMPLE.elemento] = auxValor;
			// Se realiza el "desplazamiento", actualizando el valor de la posición con la
			// del padre
			posicion = padre(posicion);
		}
	}

	@Override
	public int extraerMax() {
		// trae del maximo el elemento
		int maximo = 0;
		if (tamano>=0) {
			maximo=heap[0][HeapMaxIMPLE.elemento];
			// remplaza el maximo por el enesimo elemento
			heap[0][HeapMaxIMPLE.prioridad] = heap[tamano-1][HeapMaxIMPLE.prioridad];
			heap[0][HeapMaxIMPLE.elemento] = heap[tamano-1][HeapMaxIMPLE.elemento];
			tamano--;
			// se hunde el elemento root para respetar la condición de heap
			hundirElemento(0);
		}else {
			System.out.println("Heap vacio");
		}		
		return maximo;
	}

	// retorna la posicion del padre
	private int padre(int index) {
		return (index - 1) / 2;
	}

	// retorna la posicion del hijo izquierdo
	private int hijoIzq(int index) {
		return 2 * index + 1;
	}

	// retorna la posición del hijo derecho
	private int hijoDer(int index) {
		return 2 * index + 2;
	}

	private void hundirElemento(int index) {
		
	}

}

// Se utilizo de referencia https://algorithmtutor.com/Data-Structures/Tree/Binary-Heaps/
