package impl;

import api.HeapMaxTDA;

public class HeapMax implements HeapMaxTDA {
	
	private int tamanoMaximo;
    private int [] heap;
    private int tamano;

    public void inicializarHeap(int tamanoHeap) {
        heap = new int[tamanoHeap];
        tamanoMaximo = tamanoHeap;
        tamano = 0;
    }

    // retorna la posición del nodo padre
    public static int padre(int i) {
        return (i - 1) / 2;
    }

    // retorna la posición del nodo hijo izq
    public static int hijoIzquierdo(int i) {
        return 2*i + 1;
    }

    // retorna la posición del nodo hijo derecho
    public static int hijoDerecho(int i) {
        return 2*i + 2;
    }

    // inserta un elemento en la ultima posición y en caso de ser necesario ordena
    public void insertar(int valor) {
        if (tamano >= tamanoMaximo) {
            System.out.println("The heap is full. Cannot insert");
            return;
        }

        // inserta el elemento en la ultima posición y aumenta el tamaño actual del heap
        heap[tamano] = valor;
        tamano = tamano + 1;


        // mientras se cumpla la condición
        int i = tamano - 1;
        while (i != 0 && heap[padre(i)] < heap[i]) {
            int temp = heap[i];
            heap[i] = heap[padre(i)];
            heap[padre(i)] = temp;
            i = padre(i);
        }
    }

    // el metodo recibe una posción del arreglo y lo ubica en la posicion que correspoda
    // si sus hijos son mayores que el valor del padre
    private void hundirElemento(int i){
        // ubica el hijo izquierdo
        int izquierdo = hijoIzquierdo(i);

        // ubica el hijo derecho
        int derecho = hijoDerecho(i);

        // encuentra cual de los 3 nodos es mayor
        int maximo = i;

        // verifica si el hijo izquierdo es mayor al elemento padre
        if (izquierdo <= tamano && heap[izquierdo] > heap[maximo]) {
            maximo = izquierdo;
        }

        // verifica si el hijo derecho es mayor al elemento en maximo sea el izquierdo o el padre
        if (derecho <= tamano && heap[derecho] > heap[maximo]) {
            maximo = derecho;
        }

        // en caso de que el elemento actual en raiz no sea el maximo
        // intercambia los valores y repite el proceso de manera recursiva
        if (maximo != i) {
            int aux = heap[i];
            heap[i] = heap[maximo];
            heap[maximo] = aux;
            hundirElemento(maximo);
        }

    }

    // retorna el elemento maximo
    public int obtenerMax() {
        return heap[0];
    }

    // retorna y elimina del heap al elemento maximo
    public int extraerMax() {
        int elementoMaximo = heap[0];

        // Remplaza al maximo con el ultimo elemento insertado
        heap[0] = heap[tamano - 1];
        tamano = tamano - 1;

        // mantiene la condición de heapMax por medio de
        // hundir elemento
        hundirElemento(0);
        return elementoMaximo;
    }

}

//Se utilizo de referencia https://algorithmtutor.com/Data-Structures/Tree/Binary-Heaps/