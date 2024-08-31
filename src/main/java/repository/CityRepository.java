package repository;

import entity.City;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class CityRepository extends Repository<City> {

    public CityRepository(SessionFactory sessionFactory) {
        super(City.class, sessionFactory);
    }
}
