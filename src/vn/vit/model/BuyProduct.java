package vn.vit.model;

public class BuyProduct extends Product {
	private int quantity;

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public BuyProduct() {
	}

	public BuyProduct(int iD, String name, long price) {
		super(iD, name, price);
	}

	public BuyProduct(int iD, String name, long price, int quantity) {
		super(iD, name, price);
		this.quantity = quantity;
	}

}
