package vn.edu.hust.bookingmanagement.domain.message.query;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

public record GetCustomerQuery(
        @TargetAggregateIdentifier
        Long customerId
){
}
