package com.piattaforme.MStore.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Ordine {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private Double prezzo;
	
	private String nomeProdotto;
	
	private Double scontoPercentage;
	
	@ManyToOne
	@JoinColumn(referencedColumnName = "id")
	private Fattura fattura;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getScontoPercentage() {
		return scontoPercentage;
	}

	public void setScontoPercentage(Double scontoPercentage) {
		this.scontoPercentage = scontoPercentage;
	}
	
	public Fattura getFattura() {
		return fattura;
	}

	public void setFattura(Fattura fattura) {
		this.fattura = fattura;
	}

	public String getNomeProdotto() {
		return nomeProdotto;
	}

	public void setNomeProdotto(String nomeProdotto) {
		this.nomeProdotto = nomeProdotto;
	}

	public Double getPrezzo() {
		return prezzo;
	}

	public void setPrezzo(Double prezzo) {
		this.prezzo = prezzo;
	}
	
	
}
