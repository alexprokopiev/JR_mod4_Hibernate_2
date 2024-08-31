package repository;

import entity.Store;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class StoreRepository extends Repository<Store> {

    public StoreRepository(SessionFactory sessionFactory) {
        super(Store.class, sessionFactory);
    }
}
