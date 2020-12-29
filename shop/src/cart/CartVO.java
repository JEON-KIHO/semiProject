package cart;

import product.ProductVO;

public class CartVO extends ProductVO{
	private int quantity; 	
	private int sum;
	
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public int getSum() {
		return sum;
	}
	public void setSum(int sum) {
		this.sum = sum;
	}
	@Override
	public String toString() {
		return "CartVO [quantity=" + quantity + ", sum=" + sum + ", getProd_id()=" + getProd_id() + ", getProd_name()="
				+ getProd_name() + ", getPrice1()=" + getPrice1() + "]";
	}
}
