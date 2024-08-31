package repository;

import entity.Actor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class ActorRepository extends Repository<Actor> {

    public ActorRepository(SessionFactory sessionFactory) {
        super(Actor.class, sessionFactory);
    }
}
