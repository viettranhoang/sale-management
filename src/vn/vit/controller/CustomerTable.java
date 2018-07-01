package vn.vit.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.swing.table.AbstractTableModel;

import vn.vit.common.CustomerIO;
import vn.vit.common.CustomerType;
import vn.vit.model.Customer;

public class CustomerTable extends AbstractTableModel {
	private ArrayList<Customer> lst = new ArrayList();
	private String[] columns = { "Mã", "Tên", "Địa chỉ", "Loại" };
	public int MIN_ID = 9999;
	
	private CustomerList cusListModel = new CustomerList();
	
	
	
	public CustomerList getCusListModel() {
		return cusListModel;
	}

	public CustomerTable() {
		lst = CustomerIO.readFile();
	}
	
	@Override
	public int getColumnCount() {
		return columns.length;
	}

	@Override
	public int getRowCount() {
		return lst.size();
	}
	
	@Override
	public String getColumnName(int columnIndex) {
		return columns[columnIndex];
	}
	
	@Override
	public boolean isCellEditable(int row, int col) {
		if(col == 0) return false;
		return true;
	}
	
	@Override
	public void setValueAt(Object value, int row, int col) {
		switch (col) {
		case 1:
			lst.get(row).setName((String)value);
			break;
		case 2:
			lst.get(row).setAddress((String)value);
			break;
		case 3:
			lst.get(row).setType((CustomerType) value);
			break;
		}
//	    fireTableCellUpdated(row, col);
	    CustomerIO.writeFile(lst);
//	    cusListModel.fireChange();
	  }
	
	

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Customer cus = lst.get(rowIndex);
		switch (columnIndex) {
		case 0:
			return cus.getID();
		case 1:
			return cus.getName();
		case 2:
			return cus.getAddress();
		case 3:
			return cus.getType();
			
		default:
			break;
		}
		return null;
	}

	public void add(Customer cus) {
		lst.add(cus);
		fireTableDataChanged();	
		CustomerIO.writeFile(lst);
		cusListModel.fireChange();

	}
	
	public void sortByName() {
		Collections.sort(lst, new Comparator<Customer>() {
			@Override
			public int compare(Customer b1, Customer b2) {
				return b1.getName().toLowerCase().compareTo(b2.getName().toLowerCase());
			}
		});
	}

	public void sortById() {
		Collections.sort(lst, new Comparator<Customer>() {
			@Override
			public int compare(Customer b1, Customer b2) {
				return b1.getID() > b2.getID() ? -1 : b1.getID() < b2.getID() ? 1 : 0;
			}
		});
	}
	
	public void delete(int id) {
		lst.remove(id);
		CustomerIO.writeFile(lst);
		fireTableDataChanged();
	}
	
	public void search(String key, int index){
		if(key == null || key.equals("")){
			lst.clear();
			lst = CustomerIO.readFile();
			fireTableDataChanged();
			return;
		}
		ArrayList<Customer> lstTemp = CustomerIO.readFile();
		
		key = key.toLowerCase();
		lst.clear();
		for (Customer c : lstTemp) {
			if(index == 0 && (c.getID() + "").toLowerCase().contains(key) ||
			   index == 1 && c.getName().toLowerCase().contains(key) ||
			   index == 2 && c.getAddress().toLowerCase().contains(key) ||
			   index == 3 && (c.getName().toLowerCase().contains(key) || 
							   c.getAddress().toLowerCase().contains(key) ||
							   (c.getID() + "").toLowerCase().contains(key))
			   ) {
			   
				lst.add(c);
			}
		}
		fireTableDataChanged();
	}
	
}
