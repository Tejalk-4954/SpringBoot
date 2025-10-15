package com.CarCompany.CarCompany.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity
public class Company {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	int id;
	
	String cname;
	
	String valuation;
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	public String getValuation() {
		return valuation;
	}

	public CEO getCeo() {
		return ceo;
	}

	public void setCeo(CEO ceo) {
		this.ceo = ceo;
	}

	public void setValuation(String valuation) {
		this.valuation = valuation;
	}
	
	@JoinColumn
	@OneToOne
	private CEO ceo;
	

	@JoinColumn
	@OneToOne
	private HQ hq;


	public HQ getHq() {
		return hq;
	}

	public void setHq(HQ hq) {
		this.hq = hq;
	}
	
	
}
