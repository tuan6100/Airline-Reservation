package vn.edu.hust.bookingmanagement.infrastructure.orm;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Slf4j
@Getter
@Setter
@Entity
@Table(name = "airplanes")
public class AirplaneOrm implements Versionable<Timestamp> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "airplane_id", nullable = false, insertable = false, updatable = false)
    private Long airplaneId;

    @Column(name = "airplane_name", nullable = false, length = Integer.MAX_VALUE, insertable = false, updatable = false)
    private String airplaneName;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "airline_id", nullable = false, insertable = false, updatable = false)
    private AirlineOrm airline;

    @Column(name = "total_number_seats", nullable = false, insertable = false, updatable = false)
    private Integer totalNumberSeats;

    @Column(name = "number_empty_seats")
    private Integer numberEmptySeats;

    @OneToMany(mappedBy = "airplane", fetch = FetchType.LAZY)
    private Set<SeatOrm> seats = new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "flight_id")
    private FlightOrm flight;

    @Version
    @Column(name = "version")
    private Timestamp version = Timestamp.valueOf(LocalDateTime.now());


}