package address;

public class AddressVO {
	
	private int row;
	private int seq;
	private String name;
	private String tel;
	private String address;
	
	public int getRow() {
		return row;
	}
	public void setRow(int row) {
		this.row = row;
	}
	public int getSeq() {
		return seq;
	}
	public void setSeq(int seq) {
		this.seq = seq;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	@Override
	public String toString() {
		return "AddressVO [row=" + row + ", seq=" + seq + ", name=" + name + ", tel=" + tel + ", address=" + address
				+ "]";
	}
}
