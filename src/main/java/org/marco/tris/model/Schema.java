package org.marco.tris.model;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Schema {

	private int[][] schema = new int[3][3];
	public Cella cella;

	public Schema() {
		List<Cella> listaCelle = new ArrayList<Cella>();
		
		int i = 1;
		do {
			listaCelle.add(new Cella());
			i++;
		} while(i < 10);
	}


	public List<Cella> celleDisponibili() {

		List<Cella> celleLibere = new ArrayList<Cella>();
		Cella cella;

		for (int i = 0; i < schema.length; i++) {
			for (int j = 0; j < schema.length; j++) {
				if (schema[i][j] == 0) {
					cella = new Cella();
					cella.setY(i);
					cella.setX(j);
					celleLibere.add(cella);
				}
			}
		}
		return celleLibere; // se l'arraylist è vuota vuol dire che la matrice non ha elementi nulli!

	}
	
	public void mossaMiglioreGUI(int[][] schema) {

		// List<Cella> celleLibere = celleDisponibili(schema);

		int bestCella = -1000; //Integer.MIN_VALUE;
		Cella bestMossa = new Cella();
		bestMossa.setX(-1);
		bestMossa.setY(-1);
		//int[] mossa = new int[2];
		for (int i = 0; i < 3; i++) { // Iteriamo tutte le mosse disponibili
			for (int j = 0; j < 3; j++) {

				if (schema[i][j] == 0) {
					schema[i][j] = 2; // 2 = COM
					int provaCella = minimax(schema, 0, false);
					schema[i][j] = 0;
					if (provaCella > bestCella) { //Il problema è che minimax mi resituisce sempre lo stesso int
						bestCella = provaCella;
						//mossa[0] = i;
						//mossa[1] = j;
						bestMossa.setX(i);
						bestMossa.setY(j);

					}
				}
			}
		} // schema[mossa[0]][mossa[1]] = 2;
		schema[bestMossa.getX()][bestMossa.getY()] = 2;
		System.out.printf("The value of the best Move is : %d\n\n", bestCella);
		
	}

	public void mossaMigliore(int[][] schema) {

		// List<Cella> celleLibere = celleDisponibili(schema);

		int bestCella = -1000; //Integer.MIN_VALUE;
		Cella bestMossa = new Cella();
		bestMossa.setX(-1);
		bestMossa.setY(-1);
		//int[] mossa = new int[2];
		for (int i = 0; i < 3; i++) { // Iteriamo tutte le mosse disponibili
			for (int j = 0; j < 3; j++) {

				if (schema[i][j] == 0) {
					schema[i][j] = 2; // 2 = COM
					int provaCella = minimax(schema, 0, false);
					schema[i][j] = 0;
					if (provaCella > bestCella) { //Il problema è che minimax mi resituisce sempre lo stesso int
						bestCella = provaCella;
						//mossa[0] = i;
						//mossa[1] = j;
						bestMossa.setX(i);
						bestMossa.setY(j);

					}
				}
			}
		} // schema[mossa[0]][mossa[1]] = 2;
		schema[bestMossa.getX()][bestMossa.getY()] = 2;
		System.out.printf("The value of the best Move is : %d\n\n", bestCella);
		
	}

	public int minimax(int[][] schema, int depth, boolean maximizing) { //Il problema è qui, da qualche parte
		
		int punteggio = checkVittoria(schema);
		
		if (punteggio == 10) return punteggio;
		if (punteggio == -10) return punteggio;
		if(mosseDisponibili(schema) == false) return 0; // Pareggio!
		
		if (maximizing) { // simulazione turno COM, maximizing
			int bestPunteggio = -1000; //Integer.MIN_VALUE;
			for (int i = 0; i < 3; i++) { // Iteriamo tutte le mosse disponibili
				for (int j = 0; j < 3; j++) {
					if (schema[i][j] == 0) {
						schema[i][j] = 2;
						bestPunteggio = Math.max(bestPunteggio, minimax(schema, depth + 1, false));
						schema[i][j] = 0;
					}
				}
			}
			return bestPunteggio;
		}

		else {
			int bestPunteggio = 1000; //Integer.MAX_VALUE; // simulazione turno G1, minimizing
			for (int i = 0; i < 3; i++) { // Iteriamo tutte le mosse disponibili
				for (int j = 0; j < 3; j++) {
					if (schema[i][j] == 0) {
						schema[i][j] = 1;
						bestPunteggio = Math.min(bestPunteggio, minimax(schema, depth + 1, true));
						schema[i][j] = 0;
					}
				}
			}
			return bestPunteggio;
		}
	}

	public void mossaRandom(int[][] schema) {
		boolean check = false;
		int x = 0;
		int y = 0;
		do {
			x = ThreadLocalRandom.current().nextInt(0, 2 + 1);
			y = ThreadLocalRandom.current().nextInt(0, 2 + 1);
			if (schema[x][y] == 0)
				check = true;
		} while (check == false);
		schema[x][y] = 2;
	}

	public int checkVittoria(int[][] schema) {

		
		for (int i = 0; i < schema.length; i++) {
			if (schema[i][0] == schema[i][1] && schema[i][1] == schema[i][2]) { // check righe
				if (schema[i][0] == 1) return -10;
				else if (schema[i][0] == 2) return 10;																								// righe/colonne
			}
		}
		
		for (int i = 0; i < schema.length; i++) {
			if (schema[0][i] == schema[1][i] && schema[1][i] == schema[2][i]) { // check colonne
				if (schema[0][i] == 1) return -10;
				else if (schema[0][i] == 2) return 10;																								// righe/colonne
			}
		}
		
		if ((schema[0][0] == schema[1][1] && schema[1][1] == schema[2][2])
				|| (schema[0][2] == schema[1][1] && schema[1][1] == schema[2][0])) { // check diagonali
			if (schema[1][1] == 1) return -10;
			else if (schema[1][1] == 2) return 10;
		}
		return 0;
	}
	
	public boolean mosseDisponibili(int[][] schema)
	{
	    for (int i = 0; i < 3; i++)
	        for (int j = 0; j < 3; j++)
	            if (schema[i][j] == 0)
	                return true;
	    return false;
	}
	
	public int[][] getSchema() {
		return schema;
	}

	public void setSchema(int[][] schema) {
		this.schema = schema;
	}

}
