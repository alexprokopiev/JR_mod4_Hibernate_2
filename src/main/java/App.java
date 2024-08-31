import entity.City;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import service.Service;
import sessionFactory.MySqlSessionFactory;

import java.util.ArrayList;
import java.util.List;

public class App {
    public static void main(String[] args) {
        List<String> addressData = new ArrayList<>(
                List.of("21 82 Bagration street", "Kaliningrad region", "89091409294", "254"));
        List<String> customerData = new ArrayList<>(
                List.of("Alex", "Prokopiev", "alexprokopiev86@gmail.com"));
        long storeId = 1L;
        long inventoryId = 925L;
        double amount = 9.99;
        List<Long> rentalData = new ArrayList<>(List.of(42L, 4441L, 1L));
        List<String> filmData = new ArrayList<>(
                List.of("THE LORD OF THE RING", "1", "4", "10", "20", "30", "40", "50"));
        try (SessionFactory factory = MySqlSessionFactory.createSessionFactory()) {
            Service service = new Service(factory);
            service.createCustomer(addressData, customerData, storeId);
            service.returnInventory(inventoryId);
            service.rentInventory(rentalData, amount);
            service.addNewFilmToRental(filmData);
        }
    }
}
