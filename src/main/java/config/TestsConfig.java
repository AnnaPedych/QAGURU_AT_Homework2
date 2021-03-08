package config;

import org.aeonbits.owner.Config;

public interface TestsConfig extends Config {
    @Key("browserName")
    String getBrowserName();

    @Key("browserVersion")
    String getBrowserVersion();

    @Key("mode")
    Boolean isRemote();

    @Key("webDriverUrl")
    String getWebDriverUrl();
}
