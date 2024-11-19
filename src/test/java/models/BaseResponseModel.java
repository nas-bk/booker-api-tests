package models;

import lombok.Data;

@Data
public class BaseResponseModel {
    Integer bookingid;
    BaseRequestModel booking;
}
