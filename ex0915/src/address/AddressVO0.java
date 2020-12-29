package address;

public class AddressVO0 {
	private int seq;
	private String name;
	private String tel;
	private String address;
	
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
		return "AddressVO [seq=" + seq + ", name=" + name + ", tel=" + tel + ", address=" + address + "]";
	}
	
	
	
}
