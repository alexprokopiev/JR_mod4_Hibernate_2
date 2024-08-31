package service;

import entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import repository.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Service {

    private final SessionFactory factory;

    private final CityRepository cityRepo;
    private final StoreRepository storeRepo;
    private final AddressRepository addressRepo;
    private final CustomerRepository customerRepo;
    private final InventoryRepository inventoryRepo;
    private final RentalRepository rentalRepo;
    private final PaymentRepository paymentRepo;
    private final FilmRepository filmRepo;
    private final LanguageRepository languageRepo;
    private final CategoryRepository categoryRepo;
    private final ActorRepository actorRepo;

    public Service(SessionFactory factory) {
        this.factory = factory;
        cityRepo = new CityRepository(factory);
        storeRepo = new StoreRepository(factory);
        addressRepo = new AddressRepository(factory);
        customerRepo = new CustomerRepository(factory);
        inventoryRepo = new InventoryRepository(factory);
        rentalRepo = new RentalRepository(factory);
        paymentRepo = new PaymentRepository(factory);
        filmRepo = new FilmRepository(factory);
        languageRepo = new LanguageRepository(factory);
        categoryRepo = new CategoryRepository(factory);
        actorRepo = new ActorRepository(factory);
    }

    public void createCustomer(List<String> addressData, List<String> customerData, long storeId) {
        try (Session session = factory.getCurrentSession()) {
            Transaction transaction = session.beginTransaction();
            try {
                Address address = Address.builder()
                        .address(addressData.get(0))
                        .district(addressData.get(1))
                        .phone(addressData.get(2))
                        .city(cityRepo.getById(Long.parseLong(addressData.get(3))))
                        .build();

                Customer customer = Customer.builder()
                        .firstName(customerData.get(0)).lastName(customerData.get(1))
                        .email(customerData.get(2)).address(addressRepo.create(address))
                        .store(storeRepo.getById(storeId))
                        .build();
                customerRepo.create(customer);

                transaction.commit();
            } catch (Exception e) {
                e.printStackTrace(System.out);
                transaction.rollback();
            }
        }
    }

    public void returnInventory(long inventoryId) {
        try (Session session = factory.getCurrentSession()) {
            Transaction transaction = session.beginTransaction();
            try {
                Inventory inventory = inventoryRepo.getById(inventoryId);
                Rental rental = rentalRepo.getByReturnInventory(inventory);
                rental.setReturnDate(LocalDateTime.now());
                rentalRepo.update(rental);

                transaction.commit();
            } catch (Exception e) {
                e.printStackTrace(System.out);
                transaction.rollback();
            }
        }
    }

    public void rentInventory(List<Long> rentalData, double amount) {
        try (Session session = factory.getCurrentSession()) {
            Transaction transaction = session.beginTransaction();
            try {
                Customer customer = customerRepo.getById(rentalData.get(0));
                Inventory inventory = inventoryRepo.getById(rentalData.get(1));
                Store store = storeRepo.getById(rentalData.get(2));
                Staff staff = store.getStaff();

                Rental rental = Rental.builder()
                        .rentalDate(LocalDateTime.now()).customer(customer)
                        .inventory(inventory).staff(staff)
                        .build();

                Payment payment = Payment.builder()
                        .amount(amount).paymentDate(LocalDateTime.now())
                        .customer(customer).staff(staff)
                        .rental(rentalRepo.create(rental))
                        .build();
                paymentRepo.create(payment);
                rental.setPayment(payment);

                transaction.commit();
            } catch (Exception e) {
                e.printStackTrace(System.out);
                transaction.rollback();
            }
        }
    }

    public void addNewFilmToRental(List<String> filmData) {
        try (Session session = factory.getCurrentSession()) {
            Transaction transaction = session.beginTransaction();
            try {
                Language language = languageRepo.getById(Long.parseLong(filmData.get(1)));
                Category category = categoryRepo.getById(Long.parseLong(filmData.get(2)));

                Set<Actor> actors = new HashSet<>();
                for (int i = 3; i < 8; i++) {
                    actors.add(actorRepo.getById(Long.parseLong(filmData.get(i))));
                }

                Film film = Film.builder()
                        .title(filmData.getFirst()).language(language)
                        .category(category).actors(actors)
                        .build();
                filmRepo.create(film);

                Set<Inventory> inventories = new HashSet<>();
                for (int i = 0; i < 6; i++) {
                    Inventory inventory = Inventory.builder()
                            .film(film)
                            .store(i < 3 ? storeRepo.getById(1L) : storeRepo.getById(2L))
                            .build();
                    inventories.add(inventoryRepo.create(inventory));
                }
                film.setInventories(inventories);

                transaction.commit();
            } catch (Exception e) {
                e.printStackTrace(System.out);
                transaction.rollback();
            }
        }
    }
}
