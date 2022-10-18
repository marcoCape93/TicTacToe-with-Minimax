package org.marco.tris.model;

import java.awt.Color;

public class Cella {
		
	private int x;
	private int y;
	private String contenuto;
	private Color colore;
	
	public Cella() {
		
	}
	
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public String getContenuto() {
		return contenuto;
	}
	public void setContenuto(String contenuto) {
		this.contenuto = contenuto;
	}
	public Color getColore() {
		return colore;
	}

	public void setColore(Color colore) {
		this.colore = colore;
	}

	@Override
	public String toString() {
		return "Cella [x=" + x + ", y=" + y + ", contenuto=" + contenuto + ", colore=" + colore + "]";
	}
	
	
	
}
