package config;

import com.codeborne.selenide.Configuration;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.Map;

public class ConfigRunner {

    private final WebDriverConfig config;

    public ConfigRunner() {
        this.config = ConfigFactory.create(WebDriverConfig.class, System.getProperties());
    }

    public void createWebDriver() {
        if (config.isRemote().equals(true)) {
            Configuration.remote = config.getRemoteUrl();

            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability("selenoid:options", Map.<String, Object>of(
                    "enableVNC", true,
                    "enableVideo", true
            ));
            Configuration.browserCapabilities = capabilities;
        }

        switch (config.getBrowser()) {
            case CHROME, FIREFOX: {
                Configuration.browser = config.getBrowser().browserToLowerCase();
                break;
            }
            default:
                throw new RuntimeException("No such driver");
        }

        Configuration.pageLoadStrategy = "eager";
        Configuration.browserVersion = config.getBrowserVersion();
        Configuration.browserSize = config.getBrowserSize();


    }

}
