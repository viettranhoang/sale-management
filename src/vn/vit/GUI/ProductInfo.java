package vn.vit.GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import vn.vit.controller.ProductTable;
import vn.vit.model.Product;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ProductInfo extends JFrame {

	private JPanel contentPane;
	private JLabel lblnGi;
	private JLabel label_1;
	private JLabel label_2;
	private JTextField tfID;
	private JTextField tfName;
	private JButton button;
	private JSpinner spPrice;
	
	private ProductTable proTableModdel = new ProductTable();

	/**
	 * Create the frame.
	 */
	public ProductInfo( ProductTable proTableModdel) {
		this.proTableModdel = proTableModdel;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 367, 241);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblnGi = new JLabel("Đơn giá");
		lblnGi.setBounds(21, 121, 52, 14);
		contentPane.add(lblnGi);
		
		label_1 = new JLabel("Tên");
		label_1.setBounds(21, 66, 52, 14);
		contentPane.add(label_1);
		
		label_2 = new JLabel("ID");
		label_2.setBounds(21, 11, 52, 14);
		contentPane.add(label_2);
		
		tfID = new JTextField();
		tfID.setEditable(false);
		tfID.setColumns(10);
		tfID.setBounds(118, 11, 199, 20);
		contentPane.add(tfID);
		
		tfName = new JTextField();
		tfName.setColumns(10);
		tfName.setBounds(118, 66, 199, 20);
		contentPane.add(tfName);
		
		button = new JButton("Thêm");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				add();
			}
		});
		button.setBounds(227, 167, 89, 23);
		contentPane.add(button);
		
		spPrice = new JSpinner();
		spPrice.setModel(new SpinnerNumberModel(1000, 1000, null, 1000));
		spPrice.setBounds(118, 118, 199, 20);
		contentPane.add(spPrice);
		
		initInfo();
	}

	private void initInfo() {
		tfID.setText("" + (proTableModdel.getRowCount() + 1));
	}

	protected void add() {
		String name = tfName.getText();
		if(name.equals("") || name.equals(null)) {
			JOptionPane.showMessageDialog(this, "Bạn chưa nhập tên");
			return;
		}
		int price =  (Integer) spPrice.getValue();
		Product p = new Product(Integer.parseInt(tfID.getText()), name, price);
		proTableModdel.add(p);
		dispose();
		
	}
}
