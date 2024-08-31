package entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.proxy.HibernateProxy;

import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Builder
@Entity
@AllArgsConstructor
@Table(name = "address")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "address_id")
    private Long addressId;
    @Column(length = 50, nullable = false)
    private String address;
    @Column(length = 50)
    private String address2;
    @Column(length = 20, nullable = false)
    private String district;
    @Column(name = "postal_code", length = 10)
    private String postalCode;
    @Column(length = 20, nullable = false)
    private String phone;
    @Column(name = "last_update", nullable = false)
    @UpdateTimestamp
    private LocalDateTime lastUpdate;
    @ManyToOne
    @JoinColumn(name = "city_id")
    private City city;

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        Address address = (Address) o;
        return getAddressId() != null && Objects.equals(getAddressId(), address.getAddressId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}
