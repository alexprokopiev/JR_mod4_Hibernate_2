package repository;

import entity.Address;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class AddressRepository extends Repository<Address> {

    public AddressRepository(SessionFactory sessionFactory) {
        super(Address.class, sessionFactory);
    }
}
