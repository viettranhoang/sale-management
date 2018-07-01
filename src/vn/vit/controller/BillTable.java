package vn.vit.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.swing.table.AbstractTableModel;

import vn.vit.model.BuyProduct;

public class BillTable extends AbstractTableModel{
	private ArrayList<BuyProduct> lst = new ArrayList<>();
	String[] columns = {"Mặt hàng", "Số lượng", "Giá"}; 
	
	public BillTable() {
		
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
	public Object getValueAt(int rowIndex, int colIndex) {
		BuyProduct b = lst.get(rowIndex);
		switch (colIndex) {
		case 0:
			return b.getName();
		case 1:
			return b.getQuantity();
		case 2:
			return b.getPrice() * b.getQuantity(); 
		}
		return null;
	}
	
	public void setList(ArrayList<BuyProduct> lst) {
		this.lst = lst;
	}
	
	
	
	
}
