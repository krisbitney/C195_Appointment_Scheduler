package Dao;

import DataModel.Customer;
import DataModel.DetailedCustomer;

public interface DetailedCustomerDao {
    DetailedCustomer getCustomerDetail(Customer customer);
}
