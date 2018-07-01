package vn.vit.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

import vn.vit.common.CustomerIO;
import vn.vit.common.ProductIO;
import vn.vit.model.Customer;
import vn.vit.model.Product;

public class ProductTable extends AbstractTableModel{
	private ArrayList<Product> lst = new ArrayList();
	String[] columns = {"Mã hàng", "Tên hàng", "Đơn giá"};
	
	private ProductList proListModel = new ProductList();
	
	
	public ProductList getProListModel() {
		return proListModel;
	}

	public ProductTable() {
		lst = ProductIO.readFile();
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
	public String getColumnName(int rowIndex) {
		return columns[rowIndex];
	}
	
	@Override
	public boolean isCellEditable(int rowIndex, int colIndex) {
		if(colIndex != 0) return true;
		return false;
	}
	
	@Override
	public void setValueAt(Object o, int rowIndex, int colIndex) {
		switch (colIndex) {
		case 1:
			lst.get(rowIndex).setName((String) o);
			break;
		case 2:
			long price = 0;
			try {
				price = Long.parseLong((String) o);
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Bạn nhập sai đơn giá");
				return;
			}
			lst.get(rowIndex).setPrice(price);
			break;
		}
		fireTableCellUpdated(rowIndex, colIndex);
		ProductIO.writeFile(lst);
		proListModel.fireChange();
	
	}
	
	@Override
	public Object getValueAt(int rowIndex, int colIndex) {
		Product pro = lst.get(rowIndex);
		switch (colIndex) {
		case 0:
			return pro.getID();
		case 1:
			return pro.getName();
		case 2:
			return pro.getPrice();
		default:
			break;
		}
		return null;
	}

	public void add(Product p) {
		lst.add(p);
		fireTableDataChanged();
		ProductIO.writeFile(lst);
		proListModel.fireChange();
	}

	public void delete(int id) {
		lst.remove(id);
		ProductIO.writeFile(lst);
		fireTableDataChanged();
	}

	public void sortById() {
		Collections.sort(lst, new Comparator<Product>() {
			@Override
			public int compare(Product b1, Product b2) {
				return b1.getID() > b2.getID() ? -1 : b1.getID() < b2.getID() ? 1 : 0;
			}
		});
		
	}

	public void sortByName() {
		Collections.sort(lst, new Comparator<Product>() {
			@Override
			public int compare(Product b1, Product b2) {
				return b1.getName().toLowerCase().compareTo(b2.getName().toLowerCase());
			}
		});
		
	}

}
