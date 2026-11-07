package com.guille.roguelike_naval.dominio.tablero;

import java.util.ArrayList;
import java.util.List;

//Un record es una forma corta de escribir una clase inmutable con sus datos, constructor, sus "getters", equals, hashCode y toString ya hechos.
public record  coordenada (int fila, int columna){

    public coordenada {
        if (fila < 0 || columna < 0) { //Constructor compacto: sin paréntesis. Solo sirve para validar.
            throw new IllegalArgumentException("Las coordenadas no pueden ser negativas");
        }

    }

    public coordenada coordenadaDesplazada (int filas, int columnas) {
        return new coordenada (fila + filas, columna + columnas);
    }

    //Metodo para obtener las coordenadas nuevas.
    public List <coordenada> vecinas () {
        List <coordenada> resultado = new ArrayList<>();

        // Fila de arriba a la coordenada inicial.
        // Si estamos en la fila 0, no hay nada arriba y no entramos.
        if (fila > 0) {
            resultado.add(coordenadaDesplazada(-1,0));
        }
        // Fila de abajo a la coordenada inicial.
        resultado.add(coordenadaDesplazada(+1,0));

        //Columna de la izquierda a la coordenada inicial.
        // Si estamos en la columna 0, no hay nada a la izquierda y no entramos.
        if (columna > 0) {
            resultado.add(coordenadaDesplazada(0,-1));
        }
        // Columna a la derecha a la coordenada inicial.
        resultado.add(coordenadaDesplazada(0,+1));
    return resultado;
    }

    //Metodo para convertir las coordenadas en texto.
    public String aTexto() {
        char letra = (char) ('A' + columna);
        int numero = fila + 1;
        return "" + letra + numero;
    }

    //Metodo para convertir el texto en coordenadas.
    public static coordenada desdeTexto(String texto) {
        int numero;


        //Comprobamos que hay algo escrito.
        if (texto == null || texto.isBlank()) {
            throw new IllegalArgumentException("Escribe una coordenada, por ejemplo B7");
        }

        //Limpiar con trim: quitar espacios y pasar a mayúsculas.
        String limpio = texto.trim().toUpperCase();

        //Comprobamos que al menos haya una letra y un número.
        if (limpio.length() <2) {
            throw new IllegalArgumentException("Coordenada no válida: " + texto);
        }

        //Comprobamos si el primer carácter es una letra entre A y Z.
        char letra = limpio.charAt(0);
        if (letra < 'A' || letra > 'Z') {
            throw new IllegalArgumentException("Coordenada no válida: " + texto);
        }

        //Esa línea convierte la letra en el número de columnas. Funciona porque, en Java, cada letra (char) es por dentro un número.
        //Cuando restas dos letras, Java resta sus códigos y el resultado es un int.
        int columna = letra - 'A';

        try {
            numero = Integer.parseInt(limpio.substring(1));
        }
        catch (NumberFormatException e) {
            throw new IllegalArgumentException("Coordenada no válida: " + texto);
        }
        if (numero < 1) {
            throw new IllegalArgumentException("Coordenada no válida: " + texto);
        }
        int fila = numero - 1;

        return new coordenada(fila, columna);
    }






}
