package com.guille.roguelike_naval.dominio.tablero;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CoordenadaTest {

    // ---------- Validación ----------

    @Test
    void filaNegativaLanzaExcepcion() {
        assertThrows(IllegalArgumentException.class, () -> new coordenada(-1, 0));
    }

    @Test
    void columnaNegativaLanzaExcepcion() {
        assertThrows(IllegalArgumentException.class, () -> new coordenada(0, -1));
    }

    // ---------- Igualdad ----------

    @Test
    void dosCoordenadasConLosMismosValoresSonIguales() {
        assertEquals(new coordenada(3, 4), new coordenada(3, 4));
    }

    // ---------- desplazada ----------

    @Test
    void desplazarUnaFilaHaciaAbajo() {
        coordenada original = new coordenada(2, 3);
        coordenada resultado = original.coordenadaDesplazada(1, 0);
        assertEquals(new coordenada(3, 3), resultado);
    }

    @Test
    void desplazarNoCambiaLaOriginal() {
        coordenada original = new coordenada(2, 3);
        original.coordenadaDesplazada(1, 0);
        assertEquals(new coordenada(2, 3), original);
    }

    // ---------- vecinas ----------

    @Test
    void enElCentroHayCuatroVecinas() {
        List<coordenada> vecinas = new coordenada(5, 5).vecinas();
        assertEquals(4, vecinas.size());
    }

    @Test
    void enElBordeDeArribaHayTresVecinas() {
        List<coordenada> vecinas = new coordenada(0, 5).vecinas();
        assertEquals(3, vecinas.size());
    }

    @Test
    void enLaEsquinaHayDosVecinas() {
        List<coordenada> vecinas = new coordenada(0, 0).vecinas();
        assertEquals(2, vecinas.size());
        assertTrue(vecinas.contains(new coordenada(1, 0)));
        assertTrue(vecinas.contains(new coordenada(0, 1)));
    }

    // ---------- aTexto ----------

    @Test
    void aTextoDevuelveLetraYNumero() {
        assertEquals("B7", new coordenada(6, 1).aTexto());
    }

    @Test
    void aTextoDeLaUltimaCasilla() {
        assertEquals("J10", new coordenada(9, 9).aTexto());
    }

    // ---------- desdeTexto ----------

    @Test
    void desdeTextoConvierteB7() {
        assertEquals(new coordenada(6, 1), coordenada.desdeTexto("B7"));
    }

    @Test
    void desdeTextoAceptaMinusculasYEspacios() {
        assertEquals(new coordenada(6, 1), coordenada.desdeTexto(" b7 "));
    }

    @Test
    void desdeTextoConvierteJ10() {
        assertEquals(new coordenada(9, 9), coordenada.desdeTexto("J10"));
    }

    @Test
    void desdeTextoRechazaTextosIncorrectos() {
        assertThrows(IllegalArgumentException.class, () -> coordenada.desdeTexto(""));
        assertThrows(IllegalArgumentException.class, () -> coordenada.desdeTexto("B"));
        assertThrows(IllegalArgumentException.class, () -> coordenada.desdeTexto("7B"));
        assertThrows(IllegalArgumentException.class, () -> coordenada.desdeTexto("BX"));
        assertThrows(IllegalArgumentException.class, () -> coordenada.desdeTexto("B0"));
    }

    // ---------- ida y vuelta ----------

    @Test
    void convertirATextoYVolverDaLaMismaCoordenada() {
        coordenada original = new coordenada(4, 7);
        assertEquals(original, coordenada.desdeTexto(original.aTexto()));
    }
}