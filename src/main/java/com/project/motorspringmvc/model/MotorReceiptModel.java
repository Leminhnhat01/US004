package com.project.motorspringmvc.model;

public class MotorReceiptModel {
	private String receipt_no;
	private String policy_no;
	private String currency;
	private Double amount;
	
	public MotorReceiptModel() {
		super();
	}
	public MotorReceiptModel(String receipt_no, String policy_no, String currency, Double amount) {
		super();
		this.receipt_no = receipt_no;
		this.policy_no = policy_no;
		this.currency = currency;
		this.amount = amount;
	}
	public String getReceipt_no() {
		return receipt_no;
	}
	public void setReceipt_no(String receipt_no) {
		this.receipt_no = receipt_no;
	}
	public String getPolicy_no() {
		return policy_no;
	}
	public void setPolicy_no(String policy_no) {
		this.policy_no = policy_no;
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
