package com.project.motorspringmvc.model;

public class MotorCurrencyModel {
	private int currency_id;
	private String name;
	
	
	public MotorCurrencyModel(int currency_id, String name) {
		super();
		this.currency_id = currency_id;
		this.name = name;
	}
	public int getCurrency_id() {
		return currency_id;
	}
	public void setCurrency_id(int currency_id) {
		this.currency_id = currency_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
}
