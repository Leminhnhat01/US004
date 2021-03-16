package com.project.motorspringmvc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.project.motorspringmvc.connection.JavaConnectToSQL;
import com.project.motorspringmvc.model.MotorCurrencyModel;
import com.project.motorspringmvc.model.MotorModel;
import com.project.motorspringmvc.model.MotorReceiptModel;

public class MotorReceiptDao {
	private static final String url="jdbc:sqlserver://localhost;databaseName=motor;user=sa;password=sa";
	private static final String FIND_ALL_MOTOR_RECEIPT="Select * From dbo.motor_receipt";
	private static final String FIND_ALL_MOTOR="Select * From dbo.motor";
	private static final String CREATE_MOTOR_RECEIPT="INSERT INTO dbo.motor_receipt "
			+ "(receipt_no,policy_no,currency,amount) VALUES (?,?,?,?)";
	private static final String FIND_RECEIPT_MOTOR_BY_POLICY_NO="Select * From dbo.motor_receipt where policy_no=?";
	private static final String FIND_MOTOR_BY_POLICY_NO="Select * From dbo.motor where policy_no=?";
	private static final String FIND_ALL_MOTOR_CURRENCY="Select * From dbo.motor_currency ORDER BY name";
	private static final String UPDATED_PAYMENT_STATUS_MOTOR="Update dbo.motor set "
			+ "payment_status=? Where policy_no=?";
	private PreparedStatement statement=null;
	private ResultSet results=null;

	public List<MotorReceiptModel> findAllMotorReceipt(){
		List<MotorReceiptModel> motorReceipt=new ArrayList<MotorReceiptModel>();
		try {
			Connection conn=JavaConnectToSQL.getConnection(url);
			statement = conn.prepareStatement(FIND_ALL_MOTOR_RECEIPT);
			results = statement.executeQuery();
			while(results.next()) {
				String receipt_no=results.getString("receipt_no");
				String policy_no=results.getString("policy_no");
				String currency=results.getString("currency");
				Double amount=results.getDouble("amount");
				MotorReceiptModel motor=new MotorReceiptModel(receipt_no,policy_no,currency,amount);
				motorReceipt.add(motor);
			}
			JavaConnectToSQL.closeConnection(conn);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		return motorReceipt;
	}
	
	public List<MotorModel> findAllMotor(){
		List<MotorModel> motor=new ArrayList<MotorModel>();
		try {
			Connection conn=JavaConnectToSQL.getConnection(url);
			statement = conn.prepareStatement(FIND_ALL_MOTOR);
			results = statement.executeQuery();
			while(results.next()) {
				String policy_no=results.getString("policy_no");
				String payment_status=results.getString("payment_status");
				String currency=results.getString("currency");
				Double amount=results.getDouble("amount");
				MotorModel m=new MotorModel(policy_no,payment_status,currency,amount);
				motor.add(m);
			}
			JavaConnectToSQL.closeConnection(conn);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		return motor;
	}
	
	//create a new Motor Receipt
	public String createMotor(MotorReceiptModel motorReceipt){
		try{
		Connection conn=JavaConnectToSQL.getConnection(url);
		statement=conn.prepareStatement(CREATE_MOTOR_RECEIPT);
		statement.setString(1, motorReceipt.getReceipt_no());
		statement.setString(2, motorReceipt.getPolicy_no());
		statement.setString(3, motorReceipt.getCurrency());
		statement.setDouble(4, motorReceipt.getAmount());
		Integer rowInserts=statement.executeUpdate();
		conn.commit();
		JavaConnectToSQL.closeConnection(conn);
		
		if (rowInserts > 0) {
			System.out.println("Creating user success.");
		}
		}catch(SQLException e) {
			System.out.print("Can not insert to db");
			e.printStackTrace();
		}
		return "success";
	}
	
	//Find a Receipt Motor
	public MotorReceiptModel findReceiptByPolicyNo(String policy_no) {
		MotorReceiptModel receiptMotor =null;
		try {
			Connection conn=JavaConnectToSQL.getConnection(url);
			statement =conn.prepareStatement(FIND_RECEIPT_MOTOR_BY_POLICY_NO);
			statement.setString(1, policy_no);
			results=statement.executeQuery();
			if(results.next()) {
				receiptMotor=new MotorReceiptModel(
						results.getString("receipt_no"),
						results.getString("policy_no"),
						results.getString("currency"),
						results.getDouble("amount")
						);
			}
				JavaConnectToSQL.closeConnection(conn);
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.print("Something have a problem");
			e.printStackTrace();
		}
		return receiptMotor;
	}

	//Find a exist policy_no in a db motor
	public MotorModel findByPolicyNo(String policy_no) {
		MotorModel motor=null;
		try {
			Connection conn=JavaConnectToSQL.getConnection(url);
			statement =conn.prepareStatement(FIND_MOTOR_BY_POLICY_NO);
			statement.setString(1, policy_no);
			results=statement.executeQuery();
			if(results.next()) {
				motor=new MotorModel(
						results.getString("policy_no"),
						results.getString("payment_status"),
						results.getString("currency"),
						results.getDouble("amount")
						);
			}
			JavaConnectToSQL.closeConnection(conn);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.print("Something have a problem");
			e.printStackTrace();
		}
		return motor;
	}
	
	//update status payment in dbo.motor
	public Integer updatePaymentStatus(String policy_no,String payment_status) {
		Integer rowsUpdate=0;
		try {
			Connection conn=JavaConnectToSQL.getConnection(url);
			statement =conn.prepareStatement(UPDATED_PAYMENT_STATUS_MOTOR);
			statement.setString(1,payment_status);
			statement.setString(2, policy_no);
			rowsUpdate=statement.executeUpdate();
			if(rowsUpdate>0) {
				System.out.println("Update Status Success");
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return rowsUpdate;
	}
	
	//get list in dbo.currency
	public List<MotorCurrencyModel> list(){
		List<MotorCurrencyModel> listCurrency=new ArrayList<MotorCurrencyModel>();
		try {
			Connection conn=JavaConnectToSQL.getConnection(url);
			statement = conn.prepareStatement(FIND_ALL_MOTOR_CURRENCY);
			results = statement.executeQuery();
			while(results.next()) {
				int currency_id=results.getInt("currency_id");
				String name=results.getString("name");
				MotorCurrencyModel currency=new MotorCurrencyModel(currency_id,name);
				listCurrency.add(currency);
			}
			JavaConnectToSQL.closeConnection(conn);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		return listCurrency;
	}
	
}
