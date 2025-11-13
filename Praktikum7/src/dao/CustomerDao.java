package dao;

import java.util.List;
import model.Customer;

public interface CustomerDao {
	public void save(Customer cs);
	public void update(Customer cs);
	public void delete(String id);
	public List<Customer> show();

}
