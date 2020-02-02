package Dao;

import DataModel.Customer;
import javafx.collections.ObservableList;

public interface CustomerDao {
    ObservableList<Customer> getAllCustomers();
    Customer getCustomerById(int customerId);
    boolean updateCustomer(int customerId, String customerName, int addressId, boolean active);
    boolean deleteCustomer(Customer customer);
    Customer addCustomer(String customerName, int addressId, boolean active);
}
