package juegocartas;

import java.util.ArrayList;
import java.util.Random;
import javax.swing.JPanel;

public class Jugador {

    private int TOTAL_CARTAS = 10;
    private int MARGEN = 20;
    private int DISTANCIA = 50;
    private int TOTAL_PINTAS = 4;

    private Carta[] cartas = new Carta[TOTAL_CARTAS];
    private Pinta[] pinta = new Pinta[TOTAL_PINTAS];

    private Random r = new Random();

    public void repartir() {
        for (int i = 0; i < TOTAL_CARTAS; i++) {
            cartas[i] = new Carta(r);

        }
    }

    public void mostrar(JPanel pnl) {
        pnl.removeAll();
        int x = MARGEN;
        //recorrido objetual por una lista de objetos
        for (Carta c : cartas) {
            c.mostrar(pnl, x, MARGEN);
            x += DISTANCIA;
        }

        pnl.repaint();
    }

    public String getGrupos() {
        String mensaje = "No se encontraron grupos";
        int[] contadores = new int[NombreCarta.values().length];
        for (Carta c : cartas) {
            contadores[c.getNombre().ordinal()]++;
        }

        int totalGrupos = 0;
        for (int c : contadores) {
            if (c >= 2) {
                totalGrupos++;
            }
        }
        if (totalGrupos > 0) {
            mensaje = "Los grupos encontrados fueron:\n";
            for (int i = 0; i < contadores.length; i++) {
                if (contadores[i] >= 2) {
                    mensaje += Grupo.values()[contadores[i]] + " de " + NombreCarta.values()[i] + "\n";
                }
            }
        }
        return mensaje;
    }


    public String getEscaleras() {

        String mensaje = "No se encontraron escaleras";
        int[] contador = new int[4];
        int totalEscaleras = 0;

        for (Carta c : cartas) {
            contador[c.getPinta().ordinal()]++;
        }

        for (int c : contador) {
            if (c >= 2) {
                totalEscaleras++;
            }
        }

        if (totalEscaleras > 0) {
            mensaje = "Las escaleras encontradas fueron:\n";

            for (int i = 0; i < contador.length; i++) {
                if (contador[i] >= 2) {
                    ArrayList<NombreCarta> nombresEscalera = new ArrayList<>();
                    for (Carta c : cartas) {
                        if (c.getPinta().ordinal() == i) {
                            nombresEscalera.add(c.getNombre());
                        }
                    }
                    mensaje += "Escalera de " + Pinta.values()[i] + ":\n";
                    for (NombreCarta nombre : nombresEscalera) {
                        mensaje += nombre + "\n";
                    }
                    mensaje += "\n";
                }
            }
        }

        return mensaje;
    }

    public int calcularValorTotalCartas() {
        int valorCarta = 0;
        for (Carta carta : cartas) {
            valorCarta += carta.getNombre().getValor();
        }
        return valorCarta;
    }

    public int calcularValorEscalera() {
        int[] contador = new int[4];
        int totalEscaleras = 0;
        int valorTotalEscalera = 0;

        for (Carta c : cartas) {
            contador[c.getPinta().ordinal()]++;
        }

        for (int c : contador) {
            if (c >= 2) {
                totalEscaleras++;
            }
        }

        if (totalEscaleras > 0) {
            for (int i = 0; i < contador.length; i++) {
                if (contador[i] >= 2) {
                    for (Carta c : cartas) {
                        if (c.getPinta().ordinal() == i) {
                            valorTotalEscalera += c.getNombre().getValor();
                        }
                    }
                }
            }
        }

        return valorTotalEscalera;
    }

    public String calcularPuntajeSinEscalera() {
        int puntajeSinEscalera = 0;
        String mensaje = "Todas las cartas forman figuras por lo que su puntaje es 0";

        int puntajeTotal = calcularValorTotalCartas();
        int puntajeEscalera = calcularValorEscalera();
        puntajeSinEscalera = puntajeTotal - puntajeEscalera;

        if (puntajeSinEscalera != 0) {
            mensaje = "El puntaje obtenido es: " + puntajeSinEscalera + "\n";
        }

        return mensaje;
    }

}
