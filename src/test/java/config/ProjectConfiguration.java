package config;

import io.restassured.RestAssured;
import org.aeonbits.owner.ConfigFactory;

import static helpers.Authorization.getAuthToken;

public class ProjectConfiguration {

    static final BaseConfig config = ConfigFactory.create(BaseConfig.class, System.getProperties());
    static final BookerAuthConfig authConfig = ConfigFactory.create(BookerAuthConfig.class, System.getProperties());

    public void setConfig() {
        RestAssured.baseURI = config.baseUrl();
    }

    public String getToken() {
        return getAuthToken(authConfig.username(), authConfig.password());
    }
}
