package com.piattaforme.MStore.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.NamedQuery;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@NamedQuery(name = "Fattura.getFattureByToken",
query = "select f from Fattura f, Costumer c where c.token = ?1 and c.id = f.costumer")
public class Fattura {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private Double prezzo;
	
	@OneToMany(mappedBy="fattura", fetch = FetchType.LAZY )
	private List<Ordine> ordine;
	
	@ManyToOne
	@JoinColumn(referencedColumnName ="id")
	private Costumer costumer;

	private Date dataOrdine;

	private Date dataConsegna;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getPrezzo() {
		return prezzo;
	}

	public void setPrezzo(Double prezzo) {
		this.prezzo = prezzo;
	}

	public Date getDataOrdine() {
		return dataOrdine;
	}

	public void setDataOrdine(Date dataOrdine) {
		this.dataOrdine = dataOrdine;
	}

	public Date getDataConsegna() {
		return dataConsegna;
	}

	public void setDataConsegna(Date dataConsegna) {
		this.dataConsegna = dataConsegna;
	}

	public Costumer getCostumer() {
		return costumer;
	}

	public void setCostumer(Costumer costumer) {
		this.costumer = costumer;
	}

	public List<Ordine> getOrdine() {
		return ordine;
	}

	public void setOrdine(List<Ordine> ordine) {
		this.ordine = ordine;
	}
	
}
