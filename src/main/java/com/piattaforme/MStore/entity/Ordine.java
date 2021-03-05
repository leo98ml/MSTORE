package com.piattaforme.MStore.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import lombok.Builder;

@Builder
@Entity
public class Ordine {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private Long quantita;
	
	@OneToOne(mappedBy="ordine")
	private Prodotto prodotto;
	
	private Double scontoPercentage;
	
	private boolean sconto;
	
	@ManyToOne
	@JoinColumn(referencedColumnName = "id")
	private Fattura fattura;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getQuantita() {
		return quantita;
	}

	public void setQuantita(Long quantita) {
		this.quantita = quantita;
	}

	public Double getScontoPercentage() {
		return scontoPercentage;
	}

	public void setScontoPercentage(Double scontoPercentage) {
		this.scontoPercentage = scontoPercentage;
	}

	public boolean isSconto() {
		return sconto;
	}

	public void setSconto(boolean sconto) {
		this.sconto = sconto;
	}

	public Prodotto getProdotto() {
		return prodotto;
	}

	public void setProdotto(Prodotto prodotto) {
		this.prodotto = prodotto;
	}

	public Fattura getFattura() {
		return fattura;
	}

	public void setFattura(Fattura fattura) {
		this.fattura = fattura;
	}
	
	
}
