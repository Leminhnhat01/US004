package com.project.motorspringmvc.model;

public class MotorModel {
	private String policy_no;
	private String payment_status;
	private String currency;
	private Double amount;
	public MotorModel() {
		super();
	}

	public MotorModel(String policy_no, String payment_status, String currency, Double amount) {
		super();
		this.policy_no = policy_no;
		this.payment_status = payment_status;
		this.currency = currency;
		this.amount = amount;
	}

	public String getPolicy_no() {
		return policy_no;
	}
	public void setPolicy_no(String policy_no) {
		this.policy_no = policy_no;
	}
	public String getPayment_status() {
		return payment_status;
	}
	public void setPayment_status(String payment_status) {
		this.payment_status = payment_status;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}
	
	
}
