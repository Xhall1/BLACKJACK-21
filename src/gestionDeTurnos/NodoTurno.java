package src.gestionDeTurnos;

public class NodoTurno {
    private String nombre; // Nombre del jugador (ej: "Jugador" o "Dealer")
    private NodoTurno siguiente;

    public NodoTurno(String nombre) {
        this.nombre = nombre;
        this.siguiente = null;
    }

    public String getNombre() {
        return nombre;
    }

    public NodoTurno getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(NodoTurno siguiente) {
        this.siguiente = siguiente;
    }
}