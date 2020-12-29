package oil;

import java.sql.Date;

public class OILVO {
	private String code;
	private String name;
	private String addres;
	private int price;
	private String self;
	private Date wdate;
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddres() {
		return addres;
	}
	public void setAddres(String addres) {
		this.addres = addres;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getSelf() {
		return self;
	}
	public void setSelf(String self) {
		this.self = self;
	}
	public Date getWdate() {
		return wdate;
	}
	public void setWdate(Date wdate) {
		this.wdate = wdate;
	}
	
	@Override
	public String toString() {
		return "OILVO [code=" + code + ", name=" + name + ", addres=" + addres + ", price=" + price + ", self=" + self
				+ ", wdate=" + wdate + "]";
	}
}
