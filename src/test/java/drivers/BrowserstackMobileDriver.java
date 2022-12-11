package drivers;

import com.codeborne.selenide.WebDriverProvider;
import config.AppBrowserstack;
import lombok.SneakyThrows;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;

public class BrowserstackMobileDriver implements WebDriverProvider {

    @SneakyThrows
    @Override
    public WebDriver createDriver(Capabilities capabilities) {

        MutableCapabilities mutableCapabilities = new MutableCapabilities();
        mutableCapabilities.merge(capabilities);

        // Set your access credentials
        mutableCapabilities.setCapability("browserstack.user", AppBrowserstack.config.browserstackLogin());
        mutableCapabilities.setCapability("browserstack.key", AppBrowserstack.config.browserstackPassword());

        // Set URL of the application under test
        mutableCapabilities.setCapability("app", AppBrowserstack.config.browserstackAppUrl());

        // Specify device and os_version for testing
        mutableCapabilities.setCapability("device", "Google Pixel 3");
        mutableCapabilities.setCapability("os_version", "9.0");

        // Set other BrowserStack capabilities
        mutableCapabilities.setCapability("project", "QA guru Project");
        mutableCapabilities.setCapability("build", "browserstack-build-wiki");
        mutableCapabilities.setCapability("name", "wiki mobile tests");

        // Initialise the remote Webdriver using BrowserStack remote URL
        // and desired capabilities defined above
        return new RemoteWebDriver(new URL(AppBrowserstack.config.browserstackUrl()), mutableCapabilities);
    }
}
