package config;

import org.aeonbits.owner.Config;

@Config.Sources({
        "classpath:base.properties"
})

public interface BaseConfig extends Config {

    @Key("userName")
    String username();

    @Key("password")
    String password();

    @Key("baseUrl")
    String baseUrl();
}
