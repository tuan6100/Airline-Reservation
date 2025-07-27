package vn.edu.hust.bookingmanagement.infrastructure.orm;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "customer_reserved_seat")
public class CustomerReservedSeatOrm {

    @EmbeddedId
    private CustomerReservedSeatId id;

    @Column(name = "departure_time")
    private LocalDateTime departureTime;

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Embeddable
    @EqualsAndHashCode
    public static class CustomerReservedSeatId implements Serializable {

        @NotNull
        @Column(name = "customer_id", nullable = false)
        private Long customerId;

        @NotNull
        @Column(name = "seat_id", nullable = false)
        private Long seatId;

        @NotNull
        @Column(name = "flight_id", nullable = false)
        private Long flightId;
    }

}
