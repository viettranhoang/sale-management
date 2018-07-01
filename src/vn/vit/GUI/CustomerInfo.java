package vn.vit.GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import vn.vit.controller.CustomerTable;
import vn.vit.model.Customer;
import vn.vit.common.CustomerType;

public class CustomerInfo extends JFrame {

	private JPanel contentPane;
	private JLabel lblId;
	private JTextField tfID;
	private JTextField tfName;
	private JLabel lblTn;
	private JTextField tfAddress;
	private JLabel lblaCh;
	private JLabel lblLoi;
	private JComboBox cbType;
	private JButton btnThm;

	private CustomerTable cusTableModel = new CustomerTable();

	/**
	 * Create the frame.
	 */
	public CustomerInfo(CustomerTable cusTableModel) {
		this.cusTableModel = cusTableModel;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 371, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblId = new JLabel("ID");
		lblId.setBounds(26, 24, 52, 14);
		contentPane.add(lblId);
		
		tfID = new JTextField();
		tfID.setEditable(false);
		tfID.setBounds(123, 24, 199, 20);
		contentPane.add(tfID);
		tfID.setColumns(10);
		
		tfName = new JTextField();
		tfName.setColumns(10);
		tfName.setBounds(123, 79, 199, 20);
		contentPane.add(tfName);
		
		lblTn = new JLabel("Tên");
		lblTn.setBounds(26, 79, 52, 14);
		contentPane.add(lblTn);
		
		tfAddress = new JTextField();
		tfAddress.setColumns(10);
		tfAddress.setBounds(123, 134, 199, 20);
		contentPane.add(tfAddress);
		
		lblaCh = new JLabel("Địa chỉ");
		lblaCh.setBounds(26, 134, 52, 14);
		contentPane.add(lblaCh);
		
		lblLoi = new JLabel("Loại");
		lblLoi.setBounds(26, 189, 52, 14);
		contentPane.add(lblLoi);
		
		cbType = new JComboBox();
		cbType.setModel(new DefaultComboBoxModel(CustomerType.values()));
		cbType.setBounds(123, 189, 199, 22);
		contentPane.add(cbType);
		
		btnThm = new JButton("Thêm");
		btnThm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				addCustomer();
			}
		});
		btnThm.setBounds(233, 227, 89, 23);
		contentPane.add(btnThm);
		initInfo();
	}



	protected void addCustomer() {
		String name, address;
		name = tfName.getText();
		address = tfAddress.getText();
		
		if(name.equals("") || name.equals(null)) {
			JOptionPane.showMessageDialog(this, "Bạn nhập tên sai");
			return;
		}
		if(address.equals("") || address.equals(null)) { 
			JOptionPane.showMessageDialog(this, "Bạn chưa nhập địa chỉ");
			return;
		}
		Customer cus = new Customer(Integer.parseInt(tfID.getText()), name, address, (CustomerType) cbType.getSelectedItem());
		
		cusTableModel.add(cus);
		dispose(); //ẩn
	}



	private void initInfo() {
		tfID.setText("" + (cusTableModel.MIN_ID + cusTableModel.getRowCount() + 1));
	}
}
