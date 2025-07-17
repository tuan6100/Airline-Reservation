package vn.edu.hust.bookingmanagement.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import vn.edu.hust.bookingmanagement.infrastructure.orm.CustomerReservedSeatOrm;

@Repository
public interface CustomerReservedSeatRepository extends JpaRepository<CustomerReservedSeatOrm, CustomerReservedSeatOrm.CustomerReservedSeatId> {

    @Query("""
    SELECT cs.id.customerId FROM CustomerReservedSeatOrm cs
    WHERE cs.id.seatId = ?1
""")
    Long findCustomerBySeatId(Long seatId);
}