package drivers;

import com.codeborne.selenide.WebDriverProvider;
import config.AppLocal;
import lombok.SneakyThrows;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;

import static utils.FileUtils.getAbsolutePath;

public class LocalMobileDriver implements WebDriverProvider {

    @SneakyThrows
    @Override
    public WebDriver createDriver(Capabilities capabilities) {

        MutableCapabilities mutableCapabilities = new MutableCapabilities();
        mutableCapabilities.merge(capabilities);

        mutableCapabilities.setCapability("platformName", "android");
        mutableCapabilities.setCapability("deviceName", "Google Pixel 3");
        mutableCapabilities.setCapability("version", "9.0");
        mutableCapabilities.setCapability("locale", "en");
        mutableCapabilities.setCapability("language", "en");
        mutableCapabilities.setCapability("appPackage", "org.wikipedia.alpha");
        mutableCapabilities.setCapability("appActivity", "org.wikipedia.main.MainActivity");
        mutableCapabilities.setCapability("app", getAbsolutePath(AppLocal.config.LocalAppPath()));

        return new RemoteWebDriver(new URL(AppLocal.config.LocalAppUrl()), mutableCapabilities);
    }
}
