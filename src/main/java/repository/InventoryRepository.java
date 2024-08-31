package repository;

import entity.Inventory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class InventoryRepository extends Repository<Inventory> {

    public InventoryRepository(SessionFactory sessionFactory) {
        super(Inventory.class, sessionFactory);
    }
}
