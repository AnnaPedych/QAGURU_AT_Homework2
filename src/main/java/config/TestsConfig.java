package config;

import org.aeonbits.owner.Config;

@Config.Sources("classpath:${mode}.properties")
public interface TestsConfig extends Config {
    @Key("browserName")
    String browserName();

    @Key("browserVersion")
    String browserVersion();

    @Key("remoteMode")
    Boolean isRemote();

    @Key("webDriverUrl")
    String webDriverUrl();
}
