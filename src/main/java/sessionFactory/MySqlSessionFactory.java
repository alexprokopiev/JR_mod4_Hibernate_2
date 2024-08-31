package sessionFactory;

import entity.City;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class MySqlSessionFactory {
    public static SessionFactory createSessionFactory() {
        return new Configuration()
                .setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect")
                .setProperty("hibernate.connection.driver_class", "com.mysql.jdbc.Driver")
                .setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/movie")
                .setProperty("hibernate.connection.username", "root")
                .setProperty("hibernate.connection.password", "admin")
                .setProperty("hbm2ddl", "validate")
                .setProperty("hibernate.show_sql", "true")
                .setProperty("hibernate.format_sql", "true")
                .setProperty("hibernate.highlight_sql", "true")
                .setProperty("hibernate.current_session_context_class", "thread")
                .addAnnotatedClass(entity.Actor.class)
                .addAnnotatedClass(entity.Address.class)
                .addAnnotatedClass(entity.Category.class)
                .addAnnotatedClass(entity.City.class)
                .addAnnotatedClass(entity.Country.class)
                .addAnnotatedClass(entity.Customer.class)
                .addAnnotatedClass(entity.Film.class)
                .addAnnotatedClass(entity.FilmText.class)
                .addAnnotatedClass(entity.Inventory.class)
                .addAnnotatedClass(entity.Language.class)
                .addAnnotatedClass(entity.Payment.class)
                .addAnnotatedClass(entity.Rental.class)
                .addAnnotatedClass(entity.Staff.class)
                .addAnnotatedClass(entity.Store.class)
                .buildSessionFactory();
    }
}
