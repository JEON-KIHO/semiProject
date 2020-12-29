package purchase;

import java.sql.Date;

public class PurchaseVO {
	private String order_id;
	private String name;
	private String address;
	private String email;
	private String tel;
	private Date pdate;
	private int payType;
	private int status;
	public String getOrder_id() {
		return order_id;
	}
	public void setOrder_id(String order_id) {
		this.order_id = order_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public Date getPdate() {
		return pdate;
	}
	public void setPdate(Date pdate) {
		this.pdate = pdate;
	}
	public int getPayType() {
		return payType;
	}
	public void setPayType(int payType) {
		this.payType = payType;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "PurchaseVO [order_id=" + order_id + ", name=" + name + ", address=" + address + ", email=" + email
				+ ", tel=" + tel + ", pdate=" + pdate + ", payType=" + payType + ", status=" + status + "]";
	}
}
