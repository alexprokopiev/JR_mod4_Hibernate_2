package repository;

import entity.Inventory;
import entity.Rental;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

public class RentalRepository extends Repository<Rental> {

    public RentalRepository(SessionFactory sessionFactory) {
        super(Rental.class, sessionFactory);
    }

    public Rental getByReturnInventory(Inventory inventory) {
        String hql = "from Rental r where r.inventory = :inventory and r.returnDate is null";
        Query<Rental> query = getCurrentSession().createQuery(hql, Rental.class);
        query.setParameter("inventory", inventory);
        return query.getSingleResult();
    }
}
