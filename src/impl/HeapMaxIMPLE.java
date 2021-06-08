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
		if (tamano >= 0) {
			maximo = heap[0][HeapMaxIMPLE.elemento];
			// remplaza el maximo por el enesimo elemento
			heap[0][HeapMaxIMPLE.prioridad] = heap[tamano - 1][HeapMaxIMPLE.prioridad];
			heap[0][HeapMaxIMPLE.elemento] = heap[tamano - 1][HeapMaxIMPLE.elemento];
			tamano--;
			// se hunde el elemento root para respetar la condición de heap
			hundirElemento(0);
		} else {
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

	// hunde el elemento a partir de una posición del arreglo dada
	private void hundirElemento(int index) {

		// Obtengo la posicion de los hijos
		int izquierdo = hijoIzq(index);
		int derecho = hijoDer(index);

		// se guarda la posición del supuesto maximo.
		int posicionMaximo = index;

		// se comparan las prioridades del elemento con su hijo izquerdo
		// se verifica ademas que los hijos existan dentro del los elementos ingresados
		// al heap
		// MAXIMO > TAMANO por lo tanto al calcular izq y derecho hay que verificar que
		// esten dentro de Tamano
		if (izquierdo <= tamano
				&& heap[izquierdo][HeapMaxIMPLE.prioridad] > heap[posicionMaximo][HeapMaxIMPLE.prioridad]) {
			// en caso de que se cumpla que la prioridad izquierda es mayor, la posicion de
			// maximo la ocupa el izquierdo
			posicionMaximo = izquierdo;
		}

		// se comparan las prioridades del elemento con su hijo derecho
		// En caso de que se cumpla que el izquierdo es mayor se comparara al izquerdo con el derecho
		if (derecho <= tamano && heap[derecho][HeapMaxIMPLE.prioridad] > heap[posicionMaximo][HeapMaxIMPLE.prioridad]) {
			// en caso de que se cumpla que la prioridad izquierda es mayor, la posicion de
			// maximo la ocupa el derecho
			posicionMaximo = derecho;
		}
		
		// si la posición de maximo cambio
		// Rotan los valores del root con el hijo que sea mayor
		if(posicionMaximo!=index) {
			int auxPrioridad = heap[index][HeapMaxIMPLE.prioridad];
			int auxValor = heap[index][HeapMaxIMPLE.elemento];
			// el nuevo maximo se posiciona en la raiz
			heap[index][HeapMaxIMPLE.prioridad] = heap[posicionMaximo][HeapMaxIMPLE.prioridad];
			heap[index][HeapMaxIMPLE.elemento] = heap[posicionMaximo][HeapMaxIMPLE.elemento];
			// el maximo anterior se posiciona en la posición donde se obtuvo el nuevo maximo
			heap[posicionMaximo][HeapMaxIMPLE.prioridad] = auxPrioridad;
			heap[posicionMaximo][HeapMaxIMPLE.elemento] = auxValor;
			// se repite recursivamente con el elemento hundido en su nueva posicion
			hundirElemento(posicionMaximo);
		}

	}

	@Override
	public int prioridad() {
		// TODO Auto-generated method stub
		return heap[0][HeapMaxIMPLE.prioridad];
	}

	@Override
	public boolean vacio() {
		// TODO Auto-generated method stub
		return (tamano<=0);
	}

}

// Se utilizo de referencia https://algorithmtutor.com/Data-Structures/Tree/Binary-Heaps/
