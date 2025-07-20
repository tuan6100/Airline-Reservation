package vn.edu.hust.airplanemanagement.domain.message.command.airplane;

import com.fasterxml.uuid.Generators;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.util.List;
import java.util.UUID;

public record RegisterNewAirplaneCommand(
        @TargetAggregateIdentifier
        UUID airplaneId,
        String airplaneName,
        List<SeatData> seatDataList,

        String airlineId
) {
    public record SeatData(
       String seatNumber,
       String seatClassId
    ) {}

    public RegisterNewAirplaneCommand(String airplaneName,
                                      List<SeatData> seatDataList,
                                      String airlineId) {
        var airplaneId = Generators.timeBasedEpochGenerator().generate();
        this(airplaneId, airplaneName, seatDataList, airlineId);
    }
}
