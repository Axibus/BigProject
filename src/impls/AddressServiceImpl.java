package impls;

import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;

import entity.Address;
import service.AddressService;

@Service
public class AddressServiceImpl implements AddressService{
	
	@Resource
	private AddressDaoImpl adi;

	@Override
	public void addAddress(Address address) {
		this.adi.addAddress(address);
		
	}

	@Override
	public void deleteAddress(int id) {
		// TODO Auto-generated method stub
		this.adi.deleteAddress(id);
	}

	@Override
	public void updateAddress(Address address) {
		this.adi.updateAddress(address);
		
	}

	@Override
	public Address findAddressById(int id) {
		Address a = this.adi.findAddressById(id);
		return a;
	}

	@Override
	public Set findAllAddress(HttpSession session) {
		Set s =adi.findAllAddress(session);
		return s ;
	}

}
