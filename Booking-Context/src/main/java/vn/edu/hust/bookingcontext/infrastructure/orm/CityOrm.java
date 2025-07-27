package vn.edu.hust.bookingmanagement.infrastructure.orm;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "cities")
public class CityOrm {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "city_id", nullable = false)
    private Long cityId;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "country_id", nullable = false)
    private CountryOrm country;

    @NotNull
    @Column(name = "city_name", nullable = false, length = Integer.MAX_VALUE)
    private String cityName;

    @OneToMany(mappedBy = "cityOrm")
    private Set<AirlineOrm> airlines = new HashSet<>();

    @OneToMany(mappedBy = "city")
    private Set<AirportOrm> airports = new HashSet<>();
}