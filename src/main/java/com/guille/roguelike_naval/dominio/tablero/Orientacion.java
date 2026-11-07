package com.guille.roguelike_naval.dominio.tablero;

//Hacia donde está colocado el barco.
public enum Orientacion {
    HORIZONTAL(0, 1),
    VERTICAL (1,0);

    private final int pasoFila;
    private final int pasoColumna;

    Orientacion(int pasoFila, int pasoColumna) {

        this.pasoFila = pasoFila;
        this.pasoColumna = pasoColumna;
    }

    public int getPasoFila() {
        return pasoFila;
    }

    public int getPasoColumna() {
        return pasoColumna;
    }
}
