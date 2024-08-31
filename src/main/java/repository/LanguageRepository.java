package repository;

import entity.Language;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class LanguageRepository extends Repository<Language> {

    public LanguageRepository(SessionFactory sessionFactory) {
        super(Language.class, sessionFactory);
    }
}
