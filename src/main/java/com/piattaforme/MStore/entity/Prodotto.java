package com.piattaforme.MStore.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.NamedQuery;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@NamedQuery(name = "Prodotto.getProdottiInOfferta",
query = "select u from Prodotto u where u.discount = true")
public class Prodotto {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String name;
	
	private String description;
	
	private boolean discount;
	
	private Double discountPercentage;
	
	private String directoryImages;
	
	private Long numRisorse;
	
	private Double price;
	
	private String tipo;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isDiscount() {
		return discount;
	}

	public void setDiscount(boolean discount) {
		this.discount = discount;
	}

	public Double getDiscountPercentage() {
		return discountPercentage;
	}

	public void setDiscountPercentage(Double discountPercentage) {
		this.discountPercentage = discountPercentage;
	}

	public String getDirectoryImages() {
		return directoryImages;
	}

	public void setDirectoryImages(String directoryImages) {
		this.directoryImages = directoryImages;
	}

	public Long getNumRisorse() {
		return numRisorse;
	}

	public void setNumRisorse(Long numRisorse) {
		this.numRisorse = numRisorse;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
}
