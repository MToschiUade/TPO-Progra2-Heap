package api;

public interface HeapMaxColaPrioridadTDA {

	public void inicializarHeap(int tamanoHeap);
	
	public int extraerMax();
	
	public int obtenerMax();
	
	public void insertar(int valor,int prioridad);
	
	//public int[] extraerMaxPar();
	
	//public int[] obtenerMaxPar();
	
	// public int primero();
	
	public int prioridad();
	
	public boolean vacio();
	
}
