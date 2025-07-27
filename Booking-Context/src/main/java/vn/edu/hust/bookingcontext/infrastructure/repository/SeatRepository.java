package vn.edu.hust.bookingmanagement.infrastructure.repository;

import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;
import vn.edu.hust.bookingmanagement.infrastructure.orm.SeatOrm;

import java.util.List;
import java.util.Optional;

@Repository
public interface SeatRepository extends JpaRepository<SeatOrm, Long> {

    @Lock(value = LockModeType.OPTIMISTIC_FORCE_INCREMENT)
    @Query("SELECT s FROM SeatOrm s WHERE s.seatId = ?1")
    Optional<SeatOrm> findByIdWithOptimisticLock(@NonNull Long seatId);

    @Query("""
    SELECT new vn.edu.hust.bookingservice.dto.SeatDetailsDto(s.seatId, s.seatNumber, sc.seatClassName, s.state, sc.price)
    FROM SeatOrm s
    INNER JOIN FETCH SeatClassEntity sc ON s.seatClass = sc
    WHERE s.seatId = ?1
""")
    Optional<SeatDetailsDto> findDetailsById(@NonNull Long seatId);

    @Query("""
    SELECT new vn.edu.hust.bookingservice.dto.SeatDetailsDto(s.seatId, s.seatNumber, sc.seatClassName, s.state, sc.price)
    FROM SeatOrm s
    INNER JOIN FETCH SeatClassEntity sc ON s.seatClass = sc
    WHERE s.airplane.airplaneId = ?1
""")
    Optional<List<SeatDetailsDto>> findDetailsByAirplaneId(@NonNull Long airplaneId);

    @Query("""
    SELECT COUNT(*) > 0 FROM SeatOrm s
    WHERE s.seatId = ?1 AND s.state = vn.edu.hust.bookingservice.enumeration.SeatState.AVAILABLE
""")
    Boolean isAvailable(@NonNull Long seatId);
}