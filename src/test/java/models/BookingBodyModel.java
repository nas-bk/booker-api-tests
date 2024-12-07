package models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class BookingBodyModel {
    String firstname;
    String lastname;
    int totalprice;
    Boolean depositpaid;
    BookingDatesModel bookingdates;
    String additionalneeds;
}
