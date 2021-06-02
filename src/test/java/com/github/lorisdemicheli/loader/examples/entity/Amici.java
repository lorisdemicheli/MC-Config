package com.github.lorisdemicheli.loader.examples.entity;

import com.github.lorisdemicheli.loader.yaml.annotations.Entity;
import com.github.lorisdemicheli.loader.yaml.annotations.Column;

@Entity
public class Amici {
	@Column
	private int numero;
	@Column
	private boolean online;
	
	public Amici() {}
	
	
	public Amici(int numero, boolean online) {
		super();
		this.numero = numero;
		this.online = online;
	}


	@Override
	public String toString() {
		return "Amici [numero=" + numero + ", online=" + online + "]";
	}


	public boolean isOnline() {
		return online;
	}
	public void setOnline(boolean online) {
		this.online = online;
	}
	public int getNumero() {
		return numero;
	}
	public void setNumero(int numero) {
		this.numero = numero;
	}
	
}
