package vn.edu.hust.bookingmanagement.infrastructure.orm;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "countries")
public class CountryOrm {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "country_id", nullable = false)
    private Long countryId;

    @NotNull
    @Column(name = "country_name", nullable = false, length = Integer.MAX_VALUE)
    private String countryName;

    @Size(max = 2)
    @NotNull
    @Column(name = "abbreviation", nullable = false, length = 2)
    private String abbreviation;

    @NotNull
    @Column(name = "continent", nullable = false, length = Integer.MAX_VALUE)
    private String continent;

    @NotNull
    @Column(name = "gmt", nullable = false)
    private Integer gmt;

    @OneToMany(mappedBy = "country")
    private Set<CityOrm> cities = new LinkedHashSet<>();

}