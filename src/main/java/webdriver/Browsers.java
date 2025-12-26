package webdriver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.*;
import java.util.Properties;

public class Browsers {
    private WebDriver driver;

    public WebDriver setUpBrowser(String browser, String URL) throws Exception {
        Properties props = new Properties();
        props.load(new FileInputStream("src/main/resources/config.properties"));

        switch (browser.toLowerCase()) {
            case "yandex":
                System.setProperty("webdriver.chrome.driver", props.getProperty("yandexdriver"));

                driver = new ChromeDriver();
                System.out.println("Запущен Яндекс.Браузер");
                driver.get(URL);
                return driver;
            case "firefox":
                System.setProperty("webdriver.firefox.driver", props.getProperty("firefoxdriver"));

                driver = new FirefoxDriver();
                System.out.println("Запущен Mozilla Firefox");
                driver.get(URL);
                return driver;
            case "chrome":
                System.setProperty("webdriver.chrome.driver", props.getProperty("chromedriver"));

                driver = new ChromeDriver();
                System.out.println("Запущен Google Chrome");
                driver.get(URL);
                return driver;
            default:
                throw new Exception("Incorrect browser selected");
        }
    }
}
