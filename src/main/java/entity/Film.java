package entity;

import converter.RatingConverter;
import jakarta.persistence.*;
import lombok.*;
import model.Feature;
import model.Rating;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.proxy.HibernateProxy;

import java.time.LocalDateTime;
import java.time.Year;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Builder
@Entity
@AllArgsConstructor
@Table(name = "film")
public class Film {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "film_id")
    private Long filmId;
    @Column(nullable = false, length = 128)
    private String title;
    private String description;
    @Column(name = "release_year")
    private Year releaseYear;
    @Column(name = "rental_duration", nullable = false)
    @Builder.Default
    private int rentalDuration = 3;
    @Column(name = "rental_rate", nullable = false)
    @Builder.Default
    private double rentalRate = 4.99;
    private int length;
    @Column(name = "replacement_cost", nullable = false)
    @Builder.Default
    private double replacementCost = 19.99;
    @Column(columnDefinition = "enum('G', 'PG', 'PG-13', 'R', 'NC-17')")
    @Convert(converter = RatingConverter.class)
    @Builder.Default
    private Rating rating = Rating.G;
    @Column(name = "special_features", columnDefinition = "set('Trailers', 'Commentaries', 'Deleted Scenes', 'Behind the Scenes')")
    private String specialFeatures;
    @Column(name = "last_update", nullable = false)
    @UpdateTimestamp
    private LocalDateTime lastUpdate;
    @ManyToOne
    @JoinColumn(name = "language_id")
    private Language language;
    @ManyToOne
    @JoinColumn(name = "original_language_id")
    private Language originalLanguage;
    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(name ="film_category",
            joinColumns =  @JoinColumn(name="film_id"),
            inverseJoinColumns = @JoinColumn(name="category_id"))
    private Category category;
    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(name ="film_actor",
            joinColumns =  @JoinColumn(name="film_id"),
            inverseJoinColumns = @JoinColumn(name="actor_id"))
    @ToString.Exclude
    @Builder.Default
    private Set<Actor> actors = new HashSet<>();
    @OneToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}, mappedBy = "film")
    private FilmText filmText;
    @OneToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "film_id")
    @ToString.Exclude
    @Builder.Default
    private Set<Inventory> inventories = new HashSet<>();

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        Film film = (Film) o;
        return getFilmId() != null && Objects.equals(getFilmId(), film.getFilmId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }

    public Set<Feature> getSpecialFeatures() {
        if (isNull(specialFeatures) || specialFeatures.isEmpty()) return null;
        Set<Feature> result = new HashSet<>();
        String[] features = specialFeatures.split(",");
        for (String feature : features) {
            result.add(Feature.getFeatureByValue(feature));
        }
        result.remove(null);
        return result;
    }

    public void setSpecialFeatures(Set<Feature> features) {
        if (isNull(features)) {
            specialFeatures = null;
        } else {
            specialFeatures = features.stream().map(Feature::getLabel).collect(Collectors.joining(","));
        }
    }
}

