package vn.edu.hust.bookingmanagement.infrastructure.repository;

import jakarta.persistence.LockModeType;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;
import vn.edu.hust.bookingmanagement.infrastructure.orm.AirplaneOrm;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface AirplaneRepository extends JpaRepository<AirplaneOrm, Integer> {

    @Query("""
    SELECT ap.airplaneId FROM FlightOrm f
    INNER JOIN FETCH AirplaneOrm ap ON f.airplane = ap
    WHERE f.id.flightId = ?1 AND f.id.departureTime = ?2
""")
    Optional<Long> findAirplaneIdByFlightId(@NonNull Long flightId, @NonNull LocalDateTime flightDepartureTime);

    @Query("""
    SELECT ap.totalNumberSeats > 0 FROM AirplaneOrm ap
    WHERE ap.airplaneId = ?1
""")
    boolean isFullSlot(@NonNull Long airplaneId);


    @Query("""
    SELECT ap FROM AirplaneOrm ap
    INNER JOIN FETCH SeatOrm s ON s.airplane = ap
    WHERE ap.airplaneId = ?1
""")
    Optional<AirplaneOrm> findById(@NonNull Long airplaneId);

    @Query("""
    SELECT al.maxTicketsPerOrder
    FROM AirplaneOrm ap
    INNER JOIN  FETCH AirlineOrm al ON ap.airline = al
    WHERE ap.airplaneId = ?1
""")
    Optional<Integer> findMaxReservableSeats(@NonNull Long airplaneId);

    @Lock(value = LockModeType.OPTIMISTIC_FORCE_INCREMENT)
    @Query("""
    SELECT ap FROM AirplaneOrm ap WHERE ap.airplaneId = ?1
""")
    Optional<AirplaneOrm> findByIdWithOptimisticLock(Long airplaneId);


    @Deprecated
    @Modifying
    @Query("""
    UPDATE AirplaneOrm ap SET ap.numberEmptySeats = ap.numberEmptySeats + ?2 WHERE ap.airplaneId = ?1
""")
    int updateNumberEmptySeats(@NonNull Long airplaneId, @NonNull int delta) throws DataIntegrityViolationException;
}