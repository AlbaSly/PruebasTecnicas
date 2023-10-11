package com.raxel.Test001;

import java.util.Scanner;

class EstadosAsiento {
    public static final char DISPONIBLE = 'L';
    public static final char OCUPADO = 'X';
}

class Asiento {
    private char status = EstadosAsiento.DISPONIBLE;

    public Asiento() {}

    public boolean isDisponible() {
        return this.status == EstadosAsiento.DISPONIBLE;
    }

    public void ocupar() {
        this.status = EstadosAsiento.OCUPADO;
    }

    @Override
    public String toString() {
        return "[" + this.status + "]";
    }
}

class Teatro {
    private final int N_FILAS = 10;
    private final int N_COLUMNAS = 10;

    private Asiento[][] asientos = new Asiento[this.N_FILAS][this.N_COLUMNAS];

    private void configurarAsientos() {
        for (int x = 0; x < this.asientos.length; x++) {
            for (int y = 0; y < this.asientos[x].length; y++) {
                this.asientos[x][y] = new Asiento();
            }
        }
    }

    public Teatro() {
        this.configurarAsientos();
    }

    public void seleccionarAsiento(int fila, int columna) {
        if (fila >= this.asientos.length || columna >= this.asientos[0].length) {
            System.out.println("===EL ASIENTO NO EXISTE===");
            return;
        }
        if (!this.asientos[fila][columna].isDisponible()) {
            System.out.println("===ASIENTO OCUPADO!===");
            return;
        }

        this.asientos[fila][columna].ocupar();
        System.out.println("===SE HA OCUPADO EL ASIENTO===");
    }

    public void mostrarMapa() {
        System.out.print("  ");
        for (int x = 0; x < this.asientos.length; x++) {
            System.out.print(" " + x + " "); /*Mostrar numeración de las columnas*/
        }
        System.out.println("");

        /*Dibujar la matriz en consola*/
        for (int x = 0; x < this.asientos.length; x++) {
            System.out.print(x + " "); /*Mostrar numeración de las filas*/
            for (int y = 0; y < this.asientos[x].length; y++) {
                System.out.print(this.asientos[x][y]);
            }
            System.out.println("");
        }
    }

    public boolean verificarTeatroLleno() {
        int disponibles = 0;

        for (Asiento[] asientos : this.asientos) {
            for (Asiento asiento : asientos) {
                if (asiento.isDisponible()) disponibles++;
            }
        }

        return disponibles == 0;
    }
}

public class Test001 {
    private static final String FINAL_WORD = "end";
    private static final Scanner SCANNER = new Scanner(System.in);

    public static void main(String[] args) {
        Teatro teatro = new Teatro();

        while (true) {
            teatro.mostrarMapa();

            System.out.println("Seleccione el asiento por sus coordenadas (separe por una coma):");
            String entrada = SCANNER.nextLine();

            if (entrada.equals(FINAL_WORD)) break;

            String[] coordenadas = entrada.split(",");
            if (!(coordenadas.length == 2)) continue;

            int fila = Integer.parseInt(coordenadas[0].trim());
            int columna = Integer.parseInt(coordenadas[1].trim());

            teatro.seleccionarAsiento(fila, columna);

            if (teatro.verificarTeatroLleno()) break;
        }
    }
}
