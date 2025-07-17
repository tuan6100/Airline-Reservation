package vn.edu.hust.bookingmanagement.infrastructure.orm;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@Entity
@Table(name = "flights")
public class FlightOrm {

    @EmbeddedId
    private FlightEntityId id;

    @OneToMany(mappedBy = "flight", fetch = FetchType.LAZY)
    private Set<AirplaneOrm> airplanes;

    @Column(name = "route")
    private String route;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "departure_airport", nullable = false, insertable = false, updatable = false)
    private AirportOrm departureAirport;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "arrival_airport", nullable = false, insertable = false, updatable = false)
    private AirportOrm arrivalAirport;

    @Column(name = "estimated_flight_duration")
    private LocalTime estimatedFlightDuration;

    @OneToMany(mappedBy = "flight", fetch = FetchType.LAZY)
    private Set<TicketOrm> ticketOrmSet = new HashSet<>();

    @Getter
    @Setter
    @Embeddable
    @EqualsAndHashCode
    public static class FlightEntityId {

        @Column(name = "flight_id", insertable = false, updatable = false)
        private Long flightId;

        @Column(name = "departure_time", insertable = false, updatable = false)
        private LocalDateTime departureTime;
    }
}
