package repository;

import entity.Category;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class CategoryRepository extends Repository<Category> {

    public CategoryRepository(SessionFactory sessionFactory) {
        super(Category.class, sessionFactory);
    }
}
