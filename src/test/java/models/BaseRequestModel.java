package models;

import lombok.Data;

@Data
public class BaseRequestModel {
    String firstname;
    String lastname;
    Integer totalprice;
    Boolean depositpaid;
    BookingDatesModel bookingdates;
    String additionalneeds;
}
