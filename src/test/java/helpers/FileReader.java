package helpers;

import com.fasterxml.jackson.databind.ObjectMapper;
import models.BaseRequestModel;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

public class FileReader {

    private static ClassLoader cl = FileReader.class.getClassLoader();
    private static ObjectMapper dataJson = new ObjectMapper();

    public static BaseRequestModel readJson(){
        try (Reader reader = new InputStreamReader(cl.getResourceAsStream("data.json"))
        ) {
            return dataJson.readValue(reader, BaseRequestModel.class);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
