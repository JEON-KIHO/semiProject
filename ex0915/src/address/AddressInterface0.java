package address;

import java.util.ArrayList;

public interface AddressInterface0 {
	public ArrayList<AddressVO0> list() throws Exception;
	public AddressVO0 read(int seq) throws Exception;
	public void insert(AddressVO0 vo) throws Exception;
	public void delete(int seq) throws Exception;
	public void update(AddressVO0 vo) throws Exception;
	
}
