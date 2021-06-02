package com.github.lorisdemicheli.loader.examples.entity;

import java.util.ArrayList;
import java.util.List;

import com.github.lorisdemicheli.loader.yaml.annotations.Entity;
import com.github.lorisdemicheli.loader.yaml.annotations.Column;

@Entity
public class Entita {

	@Column
	private String animale;
	@Column
	private String sesso;
	@Column
	private List<String> sopprannomi = new ArrayList<>();
	
	public Entita(String animale, String sesso) {
		this.animale = animale;
		this.sesso = sesso;
	}
	
	public Entita() {}

	public String getAnimale() {
		return animale;
	}

	public void setAnimale(String animale) {
		this.animale = animale;
	}

	public String getSesso() {
		return sesso;
	}

	public void setSesso(String sesso) {
		this.sesso = sesso;
	}

	@Override
	public String toString() {
		return "Entity [animale=" + animale + ", sesso=" + sesso + ", sopprannomi=" + sopprannomi + "]";
	}
	
	public void setSopprannomi(List<String> sopprannomi) {
		this.sopprannomi = sopprannomi;
	}
	
	public List<String> getSopprannomi() {
		return sopprannomi;
	}
}
