package tests;

import com.github.javafaker.Faker;
import models.BaseRequestModel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Locale;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static specs.Spec.*;

public class UpdateBookingTests extends TestBase {
    BaseRequestModel newBookingData = new BaseRequestModel();


    @Test
    @DisplayName("Обновление текущего бронирования")
    void successfulUpdateBookingTest() {

        step("Подготовить тестовые данные", () -> {
            idBooking = createBooking(bookingData);
            updateBookingData();
        });


        BaseRequestModel response = step("Отправить запрос на обновление бронирования", () ->
                given(requestSpec)
                        .body(newBookingData)
                        .cookie("token", System.getProperty("token"))
                        .when()
                        .put("/booking/" + idBooking)
                        .then()
                        .spec(responseSpec200)
                        .extract().as(BaseRequestModel.class));


        step("Проверить, что данные бронирования обновлены. Данные не соответствуют изначальным.", () -> {
            assertThat(response.getLastname()).isNotEqualTo(bookingData.getLastname());
            assertThat(response.getFirstname()).isNotEqualTo(bookingData.getFirstname());
        });

        step("Удалить тестовые данные", () ->
                deleteBooking(idBooking));
    }

    @Test
    @DisplayName("Обновление текущего бронирования без токена авторизации")
    void updateBookingWithoutTokenTest() {

        step("Подготовить тестовые данные", () -> {
            idBooking = createBooking(bookingData);
            updateBookingData();
        });

        step("Отправить запрос на обновление бронирования без токена. Проверить, что вернулся код ответа 403", () ->
                given(requestSpec)
                        .body(newBookingData)
                        .when()
                        .put("/booking/" + idBooking)
                        .then()
                        .spec(responseSpec403));

        step("Удалить тестовые данные", () ->
                deleteBooking(idBooking));
    }

    private void updateBookingData() {
        Faker faker = new Faker(new Locale("en-GB"));
        newBookingData.setFirstname(faker.name().firstName());
        newBookingData.setLastname(faker.name().lastName());
        newBookingData.setTotalprice(bookingData.getTotalprice());
        newBookingData.setAdditionalneeds(bookingData.getAdditionalneeds());
        newBookingData.setDepositpaid(bookingData.getDepositpaid());
        newBookingData.setBookingdates(bookingData.getBookingdates());
    }

}
