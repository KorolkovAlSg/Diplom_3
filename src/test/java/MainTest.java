import constans.Constants;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

import org.junit.Before;
import org.junit.After;
import org.junit.Test;

public class MainTest {

    private WebDriver driver;

    private MainPage mainPage;

    @Before
    public void setUp() {

        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        //driver = new FirefoxDriver();

        mainPage = new MainPage(driver);

        driver.get(Constants.URLFORTESTS);
    }

    @Test
    public void checkSection(){
        mainPage.checkSectionTransitionsWorking();
    }

    @After
    public void closeWindow(){
        driver.quit();
    }


}
