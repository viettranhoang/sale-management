package vn.vit.GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.swing.DefaultCellEditor;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableColumn;

import vn.vit.common.BillIO;
import vn.vit.common.CustomerType;
import vn.vit.controller.BillTable;
import vn.vit.controller.CustomerList;
import vn.vit.controller.CustomerTable;
import vn.vit.controller.ProductTable;
import vn.vit.model.Bill;
import vn.vit.model.BuyProduct;
import vn.vit.model.Customer;
import vn.vit.model.Product;
import java.awt.Color;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.border.LineBorder;
import javax.swing.JTextField;

public class MainFrame extends JFrame {

	private JPanel contentPane;
	private JTabbedPane tabbedPane;
	private JPanel panel;
	private JPanel panel_1;
	private JPanel panel_2;
	private JTable tblCustomer;
	private JButton btnAdd;
	private JScrollPane scrollPane;
	private JTable tblProduct;
	private JButton btnAddProduct;
	private JScrollPane scrollPane_1;
	
	private JList lstCustomer;
	private JList lstProduct;
	private JButton btnBuy;
	private JSpinner spQuantity;
	private JLabel lblSLng;
	private JTable tblBill;
	private JButton btnBilling;
	private JScrollPane scrollPane_2;
	private JScrollPane scrollPane_3;
	private JScrollPane scrollPane_4;
	private JLabel lbTotalPrice;
	private JLabel lblTngTin;
	private JButton btnDelCustomer;
	private JLabel lblTmKim;
	private JTextField tfSearchCustomer;
	private JButton btnDelProduct;
	private JLabel label;
	private JTextField tfSearchProduct;
	
	private CustomerTable cusTableModel = new CustomerTable();
	private ProductTable proTableModel = new ProductTable();
	private BillTable billTableModel = new BillTable();
	private ArrayList<Bill> lstBill = BillIO.readFile();
	private JComboBox cbSearchCustomer;
	

	/**
	 * Create the frame.
	 */
	public MainFrame() {
		setTitle("Quản lý bán hàng");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, 434, 261);
		contentPane.add(tabbedPane);
		
//**************************** Tab Customer **********************
		
		panel = new JPanel();
		tabbedPane.addTab("Khách hàng", null, panel, null);
		panel.setLayout(null);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 409, 185);
		panel.add(scrollPane);
		
		tblCustomer = new JTable();
		tblCustomer.setModel(cusTableModel);
		//---Add ComboBox to Table-----
		JComboBox cbType = new JComboBox();
		cbType.setModel(new DefaultComboBoxModel(CustomerType.values()));
		TableColumn col = tblCustomer.getColumnModel().getColumn(3);
		col.setCellEditor(new DefaultCellEditor(cbType));
		//-------------------------
		
		//--------Sort By Header Click Listener---------------------
		tblCustomer.getTableHeader().addMouseListener(new MouseAdapter() {
		    @Override
		    public void mouseClicked(MouseEvent e) {
		        int col = tblCustomer.columnAtPoint(e.getPoint());
		        if(col == 0) cusTableModel.sortById();
		        if(col == 1) cusTableModel.sortByName();
		    }
		});
		//----------------------------------
		
		scrollPane.setViewportView(tblCustomer);
		
		btnAdd = new JButton("Thêm");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				addCustomer();
			}
		});
		btnAdd.setBounds(360, 207, 59, 23);
		panel.add(btnAdd);
		
		btnDelCustomer = new JButton("Xóa");
		btnDelCustomer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				delCustomer();
			}
		});
		btnDelCustomer.setBounds(291, 207, 59, 23);
		panel.add(btnDelCustomer);
		
		lblTmKim = new JLabel("Tìm kiếm:"); 
		lblTmKim.setBounds(10, 211, 46, 14);
		panel.add(lblTmKim);
		
		tfSearchCustomer = new JTextField();
		tfSearchCustomer.setBounds(66, 208, 141, 20);
		panel.add(tfSearchCustomer);
		tfSearchCustomer.setColumns(10);
		//----- Search -----
		tfSearchCustomer.getDocument().addDocumentListener(new DocumentListener() {
			public void changedUpdate(DocumentEvent e) {
				searchCustomer();
			}

			public void removeUpdate(DocumentEvent e) {
				searchCustomer();
			}

			public void insertUpdate(DocumentEvent e) {
				searchCustomer();
			}

		});
		
		cbSearchCustomer = new JComboBox();
		cbSearchCustomer.setModel(new DefaultComboBoxModel(new String[] {"Mã", "Tên", "Địa chỉ", "Tất cả"}));
		cbSearchCustomer.setBounds(216, 207, 65, 22);
		panel.add(cbSearchCustomer);
		
//************************ Tab Product *************************		
		
		panel_1 = new JPanel();
		tabbedPane.addTab("Mặt hàng", null, panel_1, null);
		panel_1.setLayout(null);
		
		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 11, 409, 185);
		panel_1.add(scrollPane_1);
		
		tblProduct = new JTable();
		tblProduct.setModel(proTableModel);
		scrollPane_1.setViewportView(tblProduct);
		//--------Sort By Header Click Listener---------------------
		tblProduct.getTableHeader().addMouseListener(new MouseAdapter() {
		    @Override
		    public void mouseClicked(MouseEvent e) {
		        int col = tblProduct.columnAtPoint(e.getPoint());
		        if(col == 0) proTableModel.sortById();
		        if(col == 1) proTableModel.sortByName();
		    }
		});
		//----------------------------------
		
		btnAddProduct = new JButton("Thêm");
		btnAddProduct.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				addProduct();
			}
		});
		btnAddProduct.setBounds(360, 207, 59, 23); 
		panel_1.add(btnAddProduct);
		
		btnDelProduct = new JButton("Xóa");
		btnDelProduct.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				delProduct();
			}
		});
		btnDelProduct.setBounds(291, 207, 59, 23);
		panel_1.add(btnDelProduct);
		
		label = new JLabel("Tìm kiếm:");
		label.setBounds(10, 213, 46, 14);
		panel_1.add(label);
		
		tfSearchProduct = new JTextField();
		tfSearchProduct.setColumns(10);
		tfSearchProduct.setBounds(66, 210, 172, 20);
		panel_1.add(tfSearchProduct);
		
		panel_2 = new JPanel();
		tabbedPane.addTab("Hóa đơn", null, panel_2, null);
		panel_2.setLayout(null);
		
//****************************** Tab Bill *********************************************		
		
		scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(10, 11, 178, 105);
		panel_2.add(scrollPane_2);
		
		lstCustomer = new JList();
		lstCustomer.setModel(cusTableModel.getCusListModel());
		lstCustomer.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent arg0) {
				showInfoBill();
				
			}
		});
		scrollPane_2.setViewportView(lstCustomer);
		
		scrollPane_3 = new JScrollPane();
		scrollPane_3.setBounds(10, 127, 178, 95);
		panel_2.add(scrollPane_3);
		
		lstProduct = new JList();
		lstProduct.setModel(proTableModel.getProListModel());
		scrollPane_3.setViewportView(lstProduct);
		
		btnBuy = new JButton("Mua");
		btnBuy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				buy();
			}  
		});
		btnBuy.setBounds(198, 199, 53, 23);
		panel_2.add(btnBuy);  
		
		spQuantity = new JSpinner();
		spQuantity.setModel(new SpinnerNumberModel(1, 1, 100, 1));
		spQuantity.setBounds(198, 173, 53, 20);
		panel_2.add(spQuantity);
		
		lblSLng = new JLabel("Số lượng:");
		lblSLng.setBounds(198, 148, 53, 14);
		panel_2.add(lblSLng);
		
		scrollPane_4 = new JScrollPane();
		scrollPane_4.setBounds(198, 11, 221, 105);
		panel_2.add(scrollPane_4);
		
		tblBill = new JTable();
		tblBill.setModel(billTableModel);
		tblBill.getTableHeader().addMouseListener(new MouseAdapter() {
		    @Override
		    public void mouseClicked(MouseEvent e) {
		        int col = tblBill.columnAtPoint(e.getPoint());
		        System.out.println(col);
		        if(col == 0) sortByName();
		        if(col == 1) sortByQuantity();
		    }
		});
		
		scrollPane_4.setViewportView(tblBill);
		
		btnBilling = new JButton("Tính tiền");
		btnBilling.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Customer cus = cusTableModel.getCusListModel().getInfo(lstCustomer.getSelectedIndex());
				getTotalPrice(cus);
			}
		});
		btnBilling.setBounds(330, 125, 89, 23);
		panel_2.add(btnBilling);
		
		lbTotalPrice = new JLabel("");
		lbTotalPrice.setBorder(new LineBorder(Color.LIGHT_GRAY));
		lbTotalPrice.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lbTotalPrice.setHorizontalAlignment(SwingConstants.CENTER);
		lbTotalPrice.setForeground(Color.RED);
		lbTotalPrice.setBounds(330, 173, 89, 49);
		panel_2.add(lbTotalPrice);
		
		lblTngTin = new JLabel("Tổng tiền:");
		lblTngTin.setBounds(330, 148, 53, 14);
		panel_2.add(lblTngTin);
	}
	     
//************************* Methods ***************************************

	protected void sortByQuantity() {
		Collections.sort(lstBill.get(lstCustomer.getSelectedIndex()).getList(), new Comparator<BuyProduct>() {
			@Override
			public int compare(BuyProduct b1, BuyProduct b2) {
				return b1.getQuantity() > b2.getQuantity() ? -1 : b1.getQuantity() < b2.getQuantity() ? 1 : 0;
			}
		});
		
	}

	protected void sortByName() {
		Collections.sort(lstBill.get(lstCustomer.getSelectedIndex()).getList(), new Comparator<BuyProduct>() {
			@Override
			public int compare(BuyProduct b1, BuyProduct b2) {
				return b1.getName().toLowerCase().compareTo(b2.getName().toLowerCase());
			}
		});
	}

	protected void getTotalPrice(Customer cus) {
		long totalPrice = 0;
		for (Bill bill : lstBill) {
			if(bill.getID() == cus.getID()) {
				for (BuyProduct pro : bill.getList()) {
					totalPrice += pro.getQuantity() * pro.getPrice();
				}
				lbTotalPrice.setText("" + totalPrice);
			}
		}
	}

	protected void buy() {
		int proSelected = lstProduct.getSelectedIndex();
		int cusSelected = lstCustomer.getSelectedIndex();
		Product p = new Product();
		Customer c = new Customer();
		try {
			p = proTableModel.getProListModel().getInfo(proSelected);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "Bạn hãy chọn mặt hàng cần mua");
			return;
		}
		try {
			c = cusTableModel.getCusListModel().getInfo(cusSelected);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "Bạn hãy chọn khách hàng");
			return;
		}
		
		BuyProduct buyProduct = new BuyProduct(p.getID(), p.getName(), p.getPrice(), (int) spQuantity.getValue());
		Bill bill = checkInListBill(c.getID());
		if(bill == null) {
			bill = new Bill(c.getID(), c.getName(), c.getAddress(), c.getType());
			lstBill.add(bill);
			bill.add(buyProduct);
		}
		else {
			if(!isBought(bill, buyProduct)) bill.add(buyProduct);
		}
		
		billTableModel.setList(bill.getList());
		tblBill.updateUI();
		BillIO.writeFile(lstBill);
		
		
	}

	protected void showInfoBill() {
		int selected = lstCustomer.getSelectedIndex();
		Customer c = new Customer();
		try {
			c = cusTableModel.getCusListModel().getInfo(selected);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "Bạn hãy chọn khách hàng");
		}
		Bill bill = checkInListBill(c.getID());
		if(bill == null) {
			billTableModel.setList(new ArrayList());
		}
		else billTableModel.setList(bill.getList());
		tblBill.updateUI();
	}
	
	public Bill checkInListBill(int idBill) {
		for (Bill b : lstBill) {
			if(b.getID() == idBill) return b;
		}
		return null;
	}

	public boolean isBought(Bill bill, BuyProduct pro) {
		for (BuyProduct b : bill.getList()) {
			if(b.getID() == pro.getID()) {
				b.setQuantity(b.getQuantity() + pro.getQuantity());
				return true;
			}
		}
		return false;
		
	}
	
	protected void delProduct() {
		try {
			proTableModel.delete(tblProduct.getSelectedRow());
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Bạn hãy chọn 1 mặt hàng");
		}
	}
	
	protected void delCustomer() {
		try {
			cusTableModel.delete(tblCustomer.getSelectedRow());
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Bạn hãy chọn 1 khách hàng");
		}
	}
	
	protected void addProduct() {
		new ProductInfo(proTableModel).setVisible(true);
		
	}

	protected void addCustomer() {
		new CustomerInfo(cusTableModel).setVisible(true);
	}
	
	protected void searchCustomer() {
		int searchTypeIndex = cbSearchCustomer.getSelectedIndex();
		String key = tfSearchCustomer.getText();
		cusTableModel.search(key, searchTypeIndex);
			
		
	}
}
