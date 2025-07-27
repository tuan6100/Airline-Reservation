package vn.edu.hust.bookingmanagement.infrastructure.orm;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "seat_classes")
public class SeatClassOrm {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "seat_class_id", nullable = false, insertable = false, updatable = false)
    private Long seatClassId;

    @Column(name = "seat_class_name", nullable = false, length = Integer.MAX_VALUE, insertable = false, updatable = false)
    private String seatClassName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "airline_id")
    private AirlineOrm airlineEntity;

    @NotNull
    @Column(name = "price", nullable = false, insertable = false, updatable = false)
    private Long price;

}