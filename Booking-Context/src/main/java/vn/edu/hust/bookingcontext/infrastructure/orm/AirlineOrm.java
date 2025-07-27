package vn.edu.hust.bookingmanagement.infrastructure.orm;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "airlines")
public class AirlineOrm {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "airline_id", nullable = false)
  private Long airlineId;

  @NotNull
  @Column(name = "airline_name", nullable = false, length = Integer.MAX_VALUE)
  private String airlineName;

  @NotNull
  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "city_id", nullable = false)
  private CityOrm cityOrm;

  @NotNull
  @Column(name = "address", nullable = false, length = Integer.MAX_VALUE)
  private String address;

  @NotNull
  @Column(name = "website", nullable = false, length = Integer.MAX_VALUE)
  private String website;

  @Column(name = "max_seats_per_flight")
  private Integer maxSeatPerFlight;

}