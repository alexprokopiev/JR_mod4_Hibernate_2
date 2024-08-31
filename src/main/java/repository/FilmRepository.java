package repository;

import entity.Film;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class FilmRepository extends Repository<Film> {

    public FilmRepository(SessionFactory sessionFactory) {
        super(Film.class, sessionFactory);
    }
}
