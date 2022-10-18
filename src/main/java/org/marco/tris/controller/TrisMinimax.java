package org.marco.tris.controller;

import java.util.Scanner;
import org.marco.tris.model.Schema;

public class TrisMinimax {

	static Scanner scan = new Scanner(System.in);
	static Schema s = new Schema();

	public static void main(String[] args) { // IMPORTANTE! G1 = 1, COM = 2 Meglio che usare le stringhe X e O
		
		while (true) {
			int[][] schema = new int[3][3];
			gioco(schema);
		}
	}

	public static void gioco(int[][] schema) {

		System.out.println("START");

		stampaSchema(schema);
		int turno = 1;
		while (true) {

			if (s.checkVittoria(schema) == 10) {
				System.out.println("Hai perso!");
				break;
			} else if (s.mosseDisponibili(schema) == false) {
				System.out.println("Pareggio!");
				break;
			}

			System.out.println("\nTurno del giocatore: inserisci un numero da 1 a 9!");

			boolean mossa = false;
			do {
				if (mossaG1(schema)) {
					stampaSchema(schema);
					mossa = true;
				} else
					System.out.println("Non puoi selezionare quella casella!");
			} while (mossa == false);

			if (s.checkVittoria(schema) == -10) {
				System.out.println("Hai vinto!");
				break;
			} else if (s.mosseDisponibili(schema) == false) {
				System.out.println("Pareggio!");
				break;
			}

			System.out.println("\nMossa dell'avversario:");
			scan.nextLine();
			if (turno == 1)
				s.mossaRandom(schema);
			else
				s.mossaMigliore(schema);
			stampaSchema(schema);

			turno++;

		}

	}

	public static boolean mossaG1(int[][] schema) {

		System.out.println("Inserisci la x:");
		int x = leggiScelta(0, 2);
		System.out.println("Inserisci la y:");
		int y = leggiScelta(0, 2);
		if (schema[x][y] != 0)
			return false;
		schema[x][y] = 1;
		return true;
	}

	public static int leggiScelta(int min, int max) {

		// Metodo aggiornato con TRY/CATCH in DO-WHILE :D

		boolean check = false;
		int scelta = 0;
		do {
			try {
				scelta = Integer.parseInt(scan.nextLine());
				while (scelta < min || scelta > max) {
					System.out.println();
					scelta = Integer.parseInt(scan.nextLine());
				}
				check = true;
			} catch (NumberFormatException nfe) {
				System.out.println("Errore, input non valido!");
			}
		} while (check == false);

		return scelta;
	}

	public static void stampaSchema(int[][] schema) {
		System.out.print("___________________\n");
		System.out.print("|     |     |     |\n");// Linea 1
		System.out.print("|  " + schema[0][0] + "  |  " + schema[0][1] + "  |  " + schema[0][2] + "  |\n");// Linea 2
		System.out.println("|_____|_____|_____|");// Linea 3
		System.out.print("|     |     |     |\n");// Linea 4
		System.out.print("|  " + schema[1][0] + "  |  " + schema[1][1] + "  |  " + schema[1][2] + "  |\n");// Linea 5
		System.out.println("|_____|_____|_____|");// Linea 6
		System.out.print("|     |     |     |\n");// Linea 7
		System.out.print("|  " + schema[2][0] + "  |  " + schema[2][1] + "  |  " + schema[2][2] + "  |\n");// Linea 8
		System.out.println("|_____|_____|_____|");// Linea 9
	}

}