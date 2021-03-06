package com.isa.airflights.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import io.jsonwebtoken.lang.Objects;


/**
 * Sluzi za definisanje tipova prtljaga, njihovih dimenzija i tezine,klase i aerodroma kojem pripada taj 
 * @author Dusan
 *
 */
@Entity
@SequenceGenerator(name="luggage_seq", initialValue=10)
@Table(name = "luggagePrice")
public class LuggagePrice {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator="luggage_seq")
	private Long id;
	
	/**
	 * Svaka cena odredjenog prtljaga pripada jednom cenovniku
	 */
	@ManyToOne
	@JoinColumn
	private LuggagePriceList luggagePriceList;
	
	
	@Column(name = "length")
	private Integer length;
	
	@Column(name = "width")
	private Integer width;
	
	@Column(name = "height")
	private Integer height;
	
	@Column(name = "weight")
	private Integer weight;
	
	
	@Column(name = "price")
	private Double price;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LuggagePriceList getLuggagePriceList() {
		return luggagePriceList;
	}

	public void setLuggagePriceList(LuggagePriceList LuggagePriceList) {
		this.luggagePriceList = LuggagePriceList;
	}

	public Integer getLength() {
		return length;
	}

	public void setLength(Integer length) {
		this.length = length;
	}

	public Integer getWidth() {
		return width;
	}

	public void setWidth(Integer width) {
		this.width = width;
	}

	public Integer getHeight() {
		return height;
	}

	public void setHeight(Integer height) {
		this.height = height;
	}

	public Integer getWeight() {
		return weight;
	}

	public void setWeight(Integer weight) {
		this.weight = weight;
	}


	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(getId());
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        LuggagePrice LuggagePrice= (LuggagePrice) obj;
        if (LuggagePrice.getId() == null || getId() == null) {
            return false;
        }
        return LuggagePrice.getId().equals(getId());
	}
	
	
}
