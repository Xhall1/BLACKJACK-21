# 🃏 Blackjack 21 - Juego de Consola en Java con Estructuras de Datos

Este proyecto es una implementación completa del clásico juego de Blackjack (21) desarrollada en Java para consola. El proyecto demuestra el uso práctico de múltiples estructuras de datos fundamentales, cada una cumpliendo un rol específico en la lógica del juego.

---

## 🎯 Objetivo del Juego

El jugador humano y el dealer automático reciben dos cartas inicialmente. El objetivo es acercarse lo más posible a 21 sin pasarse. El jugador puede pedir más cartas o plantarse, mientras que el dealer toma decisiones automatizadas basadas en un árbol de decisión binario.

---

## 🧱 Estructuras de Datos Implementadas

### 🟦 Lista Enlazada - Representación de la Baraja

**Archivo:** `src/card/Baraja.java`

- **Propósito:** Modela la baraja de 52 cartas como una secuencia dinámica
- **Funcionalidades:**
  - Inicialización de baraja estándar con 4 palos y 13 valores
  - Mezcla aleatoria de cartas usando `Collections.shuffle()`
  - Operación de "robar carta" elimina eficientemente el primer elemento
  - Recorrido para mostrar cartas restantes
- **Nodo utilizado:** `src/utils/Nodo<Carta>`

### 🟨 Pila (Stack) - Historial de Cartas Jugadas

**Archivo:** `src/historialJugadas/Pila.java`

- **Propósito:** Mantiene el historial de todas las cartas jugadas durante la partida
- **Funcionalidades:**
  - Implementación LIFO (Last In, First Out)
  - Método `apilar()` para agregar cartas al historial
  - Método `desapilar()` para recuperar la última carta jugada
  - Visualización del historial completo con formato ASCII
- **Nodo utilizado:** `src/utils/Nodo<Carta>`

### 🟩 Cola (Queue) - Gestión de Turnos

**Archivo:** `src/turnos/Cola.java`

- **Propósito:** Controla el orden secuencial de turnos entre jugadores
- **Funcionalidades:**
  - Implementación FIFO (First In, First Out)
  - Alternancia automática entre jugador humano y dealer
  - Métodos `encolar()` y `desencolar()` para gestión de turnos
  - Visualización del orden de turnos pendientes
- **Nodo utilizado:** `src/utils/Nodo<T>` (genérico)

### 🟫 Árbol Binario de Decisión - Lógica del Dealer

**Archivos:** `src/dealer/ArbolDecision.java`, `src/dealer/NodoDecision.java`

- **Propósito:** Implementa la lógica automatizada del dealer según reglas estándar
- **Funcionalidades:**
  - Estructura de árbol binario para toma de decisiones
  - Evaluación de puntaje y presencia de "As suave"
  - Reglas implementadas:
    - Puntaje < 17: Pedir carta
    - Puntaje ≥ 17: Plantarse
    - As suave con 17: Pedir carta (regla estándar)
  - Visualización de la estructura del árbol para debugging

### 🟥 Tabla Hash (HashMap) - Control de Estado de Jugadores

**Archivos:** `src/jugadores/ControladorJugadores.java`, `src/jugadores/EstadoJugador.java`

- **Propósito:** Almacena y gestiona eficientemente el estado de todos los jugadores
- **Funcionalidades:**
  - Acceso O(1) a información de jugadores por nombre
  - Estado completo por jugador:
    - Cartas en mano con cálculo automático de puntaje
    - Manejo inteligente de Ases (11 o 1 según conveniencia)
    - Estado del juego (plantado, pasado, blackjack)
    - Estadísticas acumuladas (partidas ganadas/jugadas)
  - Determinación automática de ganadores
  - Gestión de múltiples jugadores simultáneos

---

## 🏗️ Arquitectura del Proyecto

```
src/
├── card/           # Representación de cartas y baraja
│   ├── Carta.java
│   └── Baraja.java
├── dealer/         # Lógica de decisión del dealer
│   ├── ArbolDecision.java
│   └── NodoDecision.java
├── game/           # Motor principal del juego
│   ├── Main.java
│   └── BlackjackEngine.java
├── historialJugadas/  # Gestión del historial
│   └── Pila.java
├── jugadores/      # Control de estado de jugadores
│   ├── EstadoJugador.java
│   └── ControladorJugadores.java
├── test/           # Pruebas de estructuras
│   └── TestEstructuras.java
├── turnos/         # Gestión de turnos
│   └── Cola.java
└── utils/          # Utilidades compartidas
    └── Nodo.java   # Nodo genérico reutilizable
```

---

## 🛠️ Características Técnicas

### Nodo Genérico Unificado

- **Archivo:** `src/utils/Nodo.java`
- Implementación genérica `Nodo<T>` reutilizable por todas las estructuras
- Métodos de retrocompatibilidad para facilitar migración
- Eliminación de duplicación de código

### Motor del Juego

- **Archivo:** `src/game/BlackjackEngine.java`
- Coordinación de todas las estructuras de datos
- Flujo completo del juego desde inicio hasta determinación de ganador
- Manejo de reglas complejas (blackjack natural, As suave, etc.)

### Funcionalidades Avanzadas

- Cálculo automático e inteligente de puntajes
- Manejo correcto de Ases (11 o 1 según conveniencia)
- Detección automática de blackjack natural (21 con 2 cartas)
- Estadísticas acumuladas por jugador
- Visualización con ASCII Art para cartas

---

## 🚀 Instalación y Ejecución

### Compilación

```bash
cd BLACKJACK-21
javac -cp . src/game/Main.java
```

### Ejecución del Juego

```bash
java -cp . src.game.Main
```

### Ejecución de Pruebas

```bash
javac -cp . src/test/TestEstructuras.java
java -cp . src.test.TestEstructuras
```

---

## 🎮 Ejemplo de Flujo de Juego

```
=======================================
          🎴 BLACKJACK 21 🎴
=======================================

🃏 ¡Bienvenido al Blackjack versión consola!
Ingrese su nombre: Juan

¡Perfecto! Juan vs Dealer

🎮 ¡Nueva partida!
Juan tiene:
  8 de ♦
  K de ♠
Puntaje: 18

Dealer tiene:
  6 de ♣
  [Carta oculta]

🎯 Turno de: Juan
¿Desea otra carta? (s/n): n
✋ Juan se planta con 18 puntos.

🤖 Turno del Dealer
El dealer revela su carta oculta...
Dealer tiene:
  6 de ♣
  7 de ♥
Puntaje: 13

El dealer toma una carta...
🃏 Nueva carta del dealer: 4 de ♦
Puntaje del dealer: 17
✋ El dealer se planta con 17 puntos.

🏆 === RESULTADOS ===
🎉 Juan gana! (18 vs 17)
```

---

## 🧪 Extensibilidad

El diseño modular permite fácil extensión para:

- **Múltiples jugadores simultáneos:** La tabla hash ya soporta N jugadores
- **Variantes del Blackjack:** Modificar reglas en el árbol de decisión
- **Diferentes tipos de mazos:** Extender la clase Baraja
- **IA más sofisticada:** Expandir el árbol de decisión
- **Interfaz gráfica:** Los métodos de lógica son independientes de la presentación
- **Persistencia de datos:** Agregar serialización a las clases de estado
- **Análisis estadístico:** Expandir las métricas en EstadoJugador

---

## 📚 Créditos

Desarrollado como proyecto académico enfocado en la implementación práctica de estructuras de datos fundamentales y su aplicación en sistemas de juegos interactivos.

**Contribuidores:** Santiago Torres Morocho, Juan David Chaves, Gabriela Orozco, Luis Alejandro Tosne, Jesús David León
