package repository;

import entity.Payment;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class PaymentRepository extends Repository<Payment> {

    public PaymentRepository(SessionFactory sessionFactory) {
        super(Payment.class, sessionFactory);
    }
}
