package dao;

import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpSession;

import entity.Address;

public interface AddressDao {
	//增
	public void addAddress(Address address);
	//删
	public Boolean deleteAddress(int id);
	//改
	public void updateAddress(Address address);
	//查
	public Address findAddressById(int id);
	public Set findAllAddress(HttpSession session);
}
