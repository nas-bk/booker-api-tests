package helpers;

import models.BookingIdsResponseModel;

import java.util.Arrays;

public class UtilMath {

    public static BookingIdsResponseModel getBookingWithMaxId(BookingIdsResponseModel[] bookingIdArray) {

        return Arrays.stream(bookingIdArray).reduce((prev, cur) -> {
            if (prev.getBookingid() > cur.getBookingid()) {
                return prev;
            }
            return cur;
        }).orElse(null);
    }
}
