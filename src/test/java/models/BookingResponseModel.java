package models;

import lombok.Data;

@Data
public class BookingResponseModel {
    int bookingid;
    BookingBodyModel booking;
}
