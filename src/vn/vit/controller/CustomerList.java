package vn.vit.controller;

import java.util.ArrayList;

import javax.swing.AbstractListModel;

import vn.vit.common.CustomerIO;
import vn.vit.model.Customer;

public class CustomerList extends AbstractListModel<String>{
	private ArrayList<Customer> lst = new ArrayList();
	
	public CustomerList() {
		lst = CustomerIO.readFile();
		
	}
	
	public void fireChange() {
		lst = CustomerIO.readFile();
		fireContentsChanged(this, 0, getSize());
	}
	
	@Override
	public String getElementAt(int index) {
		return lst.get(index).getName();
	}

	@Override
	public int getSize() {
		return lst.size();
	}

	public Customer getInfo(int cusSelected) {
		return lst.get(cusSelected);
	}

}
