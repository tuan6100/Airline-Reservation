package vn.edu.hust.bookingmanagement.infrastructure.orm;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "tickets")
public class TicketOrm {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ticket_id", nullable = false, insertable = false, updatable = false)
    private Long ticketId;

    @Column(name = "created_at", nullable = false, insertable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "state")
    private String state;

    @Column(name = "ticket_code", insertable = false, updatable = false)
    private UUID ticketCode;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "sales_start_at")
    private LocalDateTime salesStartAt;

    @Column(name = "sales_end_at")
    private LocalDateTime salesEndAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "seat_id", insertable = false, updatable = false)
    private SeatOrm seat;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({
            @JoinColumn(name = "flight_id", referencedColumnName = "flight_id", insertable = false, updatable = false),
            @JoinColumn(name = "flight_departure_time", referencedColumnName = "departure_time", insertable = false, updatable = false)
    })
    private FlightOrm flight;

}