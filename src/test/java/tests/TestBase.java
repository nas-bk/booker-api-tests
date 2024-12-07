package tests;

import com.github.javafaker.Faker;
import config.ProjectConfiguration;
import helpers.FileReader;
import io.qameta.allure.Step;
import models.BookingBodyModel;
import models.BookingDatesModel;
import models.BookingResponseModel;
import org.junit.jupiter.api.BeforeAll;

import java.util.Locale;

import static io.restassured.RestAssured.given;
import static specs.Spec.*;

public class TestBase {
    static final ProjectConfiguration config = new ProjectConfiguration();
    public static BookingBodyModel bookingData = new BookingBodyModel();
    public static String token;

    @BeforeAll
    static void beforeAll() {
        config.setConfig();
        token = config.getToken();
        bookingData = FileReader.readBookingDataJson("bookingData.json");
    }

    @Step("Создать бронирование")
    public Integer createBooking(BookingBodyModel body) {
        BookingResponseModel response =
                given(requestSpec)
                        .body(body)
                        .cookie("token", token)
                        .when()
                        .post("/booking")
                        .then()
                        .spec(responseSpec200)
                        .extract().as(BookingResponseModel.class);
        return response.getBookingid();
    }

    @Step("Удалить бронирование")
    public void deleteBooking(Integer id) {
        given(requestSpec)
                .cookie("token", token)
                .when()
                .delete("/booking/" + id)
                .then()
                .spec(responseSpec201);
    }

    @Step("Генерация новых данных бронирования")
    public BookingBodyModel updateBookingData() {
        Faker faker = new Faker(new Locale("en-GB"));
        String firstname = faker.name().firstName();
        String lastname = faker.name().lastName();
        int totalprice = faker.number().numberBetween(100, 500);
        String additionalneeds = "Breakfast";
        boolean depositpaid = true;
        BookingDatesModel bookingdates = new BookingDatesModel("2024-12-20", "2025-01-10");

        return new BookingBodyModel(firstname, lastname, totalprice, depositpaid, bookingdates, additionalneeds);
    }
}
