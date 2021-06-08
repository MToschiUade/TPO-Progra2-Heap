package api;

public interface ColaPrioridadTDA {

	void InicializarCola(); // sin precondiciones

	void AcolarPrioridad(int x, int prioridad); // cola inicializada

	void Desacolar(); // cola inicializada y no vacía

	boolean ColaVacia(); // cola inicializada

	int Primero(); // cola inicializada y no vacía

	int Prioridad(); // cola inicializada y no vacía

}
