package vn.vit.model;

import java.util.ArrayList;

import vn.vit.common.CustomerType;

public class Bill extends Customer{
	private ArrayList<BuyProduct> lstProduct = new ArrayList<>();

	public Bill() {
		super();
	}

	public Bill(int iD, String name, String address, CustomerType type) {
		super(iD, name, address, type);
	}
	
	public ArrayList<BuyProduct> getList() {
		return lstProduct;
	}
	
	public void add(BuyProduct b) {
		lstProduct.add(b);
	}
	
	
	
}
