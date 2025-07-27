package vn.edu.hust.bookingmanagement.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;
import vn.edu.hust.bookingmanagement.infrastructure.orm.FlightOrm;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface FlightRepository extends JpaRepository<FlightOrm, FlightOrm.FlightEntityId> {

    @Query("""
    SELECT f FROM FlightOrm f WHERE f.id.flightId = ?1
""")
    Optional<FlightOrm> findByFlightId(@NonNull Long flightId);

    @Query("""
    SELECT f FROM FlightOrm f
    INNER JOIN FETCH RouteEntity r ON f.route = r
    INNER JOIN FETCH AirportEntity a1 ON r.departureAirport = a1
    WHERE a1.airportName LIKE CONCAT(?1, '%')
    INTERSECT
    SELECT f FROM FlightOrm f
    INNER JOIN FETCH RouteEntity r ON f.route = r
    INNER JOIN FETCH AirportEntity a2 ON r.arrivalAirport = a2
    WHERE a2.airportName LIKE CONCAT(?2, '%')
""")
    Optional<List<FlightOrm>> findFlightByRoute(String departureAirport, String arrivalAirport);

    @Query("""
    SELECT f FROM FlightOrm f
    WHERE f.id.departureTime BETWEEN ?1 AND ?2
""")
    Optional<List<FlightOrm>> findFlightByDepartureTime(LocalDateTime fromTime, LocalDateTime toTime);
}