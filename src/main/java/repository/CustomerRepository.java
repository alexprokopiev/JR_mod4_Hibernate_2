package repository;

import entity.Customer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class CustomerRepository extends Repository<Customer> {
    public CustomerRepository(SessionFactory sessionFactory) {
        super(Customer.class, sessionFactory);
    }
}
