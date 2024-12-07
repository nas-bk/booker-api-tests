package tests;

import models.BookingBodyModel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static specs.Spec.requestSpec;
import static specs.Spec.responseSpec200;

public class GetBookingTests extends TestBase {

    @Test
    @DisplayName("Получение информации существующего бронирования")
    void successfulGetBookingTest() {
        int idBooking = createBooking(bookingData);

        BookingBodyModel response =
                step("Отправить запрос на получение данных бронирования по его id", () ->
                        given(requestSpec)
                                .cookie("token", token)
                                .when()
                                .get("/booking/" + idBooking)
                                .then()
                                .spec(responseSpec200)
                                .extract().as(BookingBodyModel.class));

        step("Проверить, что запрос вернул данные соответствующие созданному бронированию", () ->
                assertThat(response).isEqualTo(bookingData));

        step("Удалить тестовые данные", () ->
                deleteBooking(idBooking));
    }
}
