package config;

import org.aeonbits.owner.Config;

@Config.Sources({
        "classpath:bookerAuth.properties",
        "system:properties"
})
public interface BookerAuthConfig extends Config {
    @Key("userName")
    String username();

    @Key("password")
    String password();
}
