package config;

import org.aeonbits.owner.Config;
@Config.Sources("classpath:${mode}.properties")
public interface TestsConfig extends Config {
    @Key("browserName")
    String getBrowserName();

    @Key("browserVersion")
    String getBrowserVersion();

    @Key("remoteMode")
    Boolean isRemote();

    @Key("webDriverUrl")
    String getWebDriverUrl();
}
