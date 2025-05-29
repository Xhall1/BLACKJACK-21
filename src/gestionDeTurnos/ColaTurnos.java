package gestionDeTurnos;

// Maneja la entrada y salida de un "Jugador" o "Dealer" para hacer los turnos
public class ColaTurnos {
    private NodoTurno inicio, fin;

    public ColaTurnos() {
        this.inicio = null;
        this.fin = null;
    }

    public boolean esVacia() {
        return inicio == null;
    }

    public void enColar(String nombre) {
        NodoTurno nuevo = new NodoTurno(nombre);
        if (esVacia()) {
            inicio = nuevo;
            fin = nuevo;
        } else {
            fin.setSiguiente(nuevo);
            fin = nuevo;
        }
    }

    public String desEncolar() {
        if (!esVacia()) {
            String nombre = inicio.getNombre();
            if (inicio == fin) {
                inicio = null;
                fin = null;
            } else {
                inicio = inicio.getSiguiente();
            }
            return nombre;
        }
        return null;
    }

    public void mostrarCola() {
        NodoTurno aux = inicio;
        while (aux != null) {
            System.out.println("Turno: " + aux.getNombre());
            aux = aux.getSiguiente();
        }
    }
}