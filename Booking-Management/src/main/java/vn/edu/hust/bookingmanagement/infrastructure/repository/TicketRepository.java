package vn.edu.hust.bookingmanagement.infrastructure.repository;

import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;
import vn.edu.hust.bookingmanagement.infrastructure.orm.TicketOrm;

import java.util.Optional;

@Repository
public interface TicketRepository extends JpaRepository<TicketOrm, Long> {

    @Lock(value = LockModeType.PESSIMISTIC_READ)
    @Query("SELECT t FROM TicketOrm t WHERE t.ticketId = ?1")
    Optional<TicketOrm> findByIdWithPessimisticLock(@NonNull Long ticketId);

    @Query("""
    SELECT t FROM TicketOrm t WHERE t.seat.seatId = ?1 AND t.flight.id.flightId = ?2
""")
    Optional<TicketOrm> findBySeatAndFlight(@NonNull Long seatId, @NonNull Long flightId);


}