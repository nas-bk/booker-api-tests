package helpers;

import com.fasterxml.jackson.databind.ObjectMapper;
import models.BookingBodyModel;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Objects;

public class FileReader {

    private static final ClassLoader cl = FileReader.class.getClassLoader();
    private static final ObjectMapper dataJson = new ObjectMapper();

    public static BookingBodyModel readBookingDataJson(String fileJson) {
        try (Reader reader = new InputStreamReader(Objects.requireNonNull(cl.getResourceAsStream(fileJson), "Файл Json не может быть пустым"))
        ) {
            return dataJson.readValue(reader, BookingBodyModel.class);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
