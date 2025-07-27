package vn.edu.hust.bookingmanagement.infrastructure.orm;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Slf4j
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "seats")
public class SeatOrm implements Versionable<Timestamp> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "seat_id", insertable = false, updatable = false)
    private Long seatId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "seat_class_id", insertable = false, updatable = false)
    private SeatClassOrm seatClass;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "airplane_id", insertable = false, updatable = false)
    private AirplaneOrm airplane;

    @Column(name = "seat_number", nullable = false, insertable = false, updatable = false)
    private String seatNumber;

    @Column(name = "state")
    private String state;

    public void setState(@NotNull String state) {
        this.state = state.toUpperCase();
    }

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Version
    @Column(name = "version")
    private Timestamp version = Timestamp.valueOf(LocalDateTime.now());


}
