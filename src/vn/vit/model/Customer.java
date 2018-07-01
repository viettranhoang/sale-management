package vn.vit.model;

import java.io.Serializable;

import vn.vit.common.CustomerType;

public class Customer implements Serializable {

	private int ID;
	private String name;
	private String address;
	private CustomerType type;

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public CustomerType getType() {
		return type;
	}

	public void setType(CustomerType type) {
		this.type = type;
	}

	public Customer(int iD, String name, String address, CustomerType type) {
		super();
		ID = iD;
		this.name = name;
		this.address = address;
		this.type = type;
	}

	public Customer() {
		super();
		// TODO Auto-generated constructor stub
	}

}
