package service;

import dao.CustomerDAO;
import model.Customer;

/**
 * Created by QQZhao on 3/4/17.
 */
public class CustomerService {


    public boolean isCustomerLogin(String username, String password){

        return false;
    }

    public boolean isCustomerExists(String username){

        CustomerDAO customerDAO = new CustomerDAO();

        Customer customer = customerDAO.getCustomer(username);

        if (customer == null){
            return false;
        }

        return true;
    }

    public boolean isPasswordMatchingUsername(String username, String password){

        CustomerDAO customerDAO = new CustomerDAO();

        Customer customer = customerDAO.getCustomer(username);

        if(password.equals(customer.getPassword())){
            return true;
        }
        return false;

    }


    public void registerNewCustomer(Customer customer){

        CustomerDAO customerDAO = new CustomerDAO();
        customerDAO.saveCustomer(customer);

    }


}
