# 🃏 Blackjack 21 - Juego de Consola en Java con Estructuras de Datos

Este proyecto es una simulación del clásico juego de Blackjack (21) desarrollada en Java para consola. Más que un simple juego, su propósito principal es la implementación y práctica de estructuras de datos esenciales para manejar la lógica, el flujo y el estado del juego.

---

## 🎯 Objetivo del Juego

El jugador humano y el dealer reciben dos cartas inicialmente. El objetivo es acercarse lo más posible a 21 sin pasarse. El jugador puede pedir más cartas o plantarse, y el dealer toma decisiones automatizadas basadas en un árbol de decisión.

---

## 🧱 Estructuras de Datos Implementadas

- **Lista Enlazada**: Representa la baraja de 52 cartas. Permite modelar la baraja como una secuencia dinámica, facilita robar cartas y recorrer la baraja restante.
- **Pila (Stack)**: Guarda el historial de cartas jugadas por ambos participantes para visualizar las últimas jugadas fácilmente.
- **Cola (Queue)**: Controla el orden de los turnos, alternando automáticamente entre el jugador y el dealer.
- **Árbol Binario de Decisión**: Implementa la lógica del dealer para decidir si pedir carta o plantarse según el puntaje actual.
- **Tabla Hash (HashMap)**: Almacena y administra el estado de cada jugador (nombre, cartas en mano, puntaje, estado del juego y métricas adicionales).

---

## 🛠️ Tecnologías

- Lenguaje: **Java**
- Plataforma: Consola

---

## 📦 Instalación y Ejecución

1. Clona el repositorio:
    ```bash
    git clone https://github.com/Xhall1/BLACKJACK-21.git
    cd blackjack-21
    ```

2. Compila y ejecuta (ejemplo con `javac` y `java`):
    ```bash
    javac game.Main.java
    java game.Main
    ```

---

## 📚 Créditos

Desarrollado por Santiago Torres Morocho, Juan David Chaves, Gabriela Orozco, Luis Alejandro Tosne, Jesús David León. Proyecto académico enfocado en estructuras de datos y lógica de juegos.





