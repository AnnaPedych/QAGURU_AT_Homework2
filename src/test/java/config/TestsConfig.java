package config;

import org.aeonbits.owner.Config;
@Config.Sources({
        "system:properties",
        "classpath:${mode}.properties"})
public interface TestsConfig extends Config {
    @Key("browserName")
    String browserName();

    @Key("browserVersion")
    String browserVersion();

    @Key("isRemote")
    Boolean isRemote();

    @Key("webDriverUrl")
    String webDriverUrl();
}
