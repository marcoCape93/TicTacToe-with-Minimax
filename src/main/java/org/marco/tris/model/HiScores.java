package org.marco.tris.model;

public class HiScores {
	
	private int id;
	private double punteggio;
	private String nickname;
	private int giocatoreId;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public double getPunteggio() {
		return punteggio;
	}
	public void setPunteggio(double punteggio) {
		this.punteggio = punteggio;
	}
	public int getGiocatoreId() {
		return giocatoreId;
	}
	public void setGiocatoreId(int giocatoreId) {
		this.giocatoreId = giocatoreId;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	@Override
	public String toString() {
		return "ID: " + id + " Punteggio: " + punteggio + " Nickname: " + nickname + " ID Giocatore: "
				+ giocatoreId;
	}
	
}
