import constans.Constants;
import net.datafaker.Faker;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

import org.junit.Before;
import org.junit.After;
import org.junit.Test;

public class RegistrationTest {
    Faker faker;

    private WebDriver driver;

    private MainPage mainPage;
    private LoginPage loginPage;
    private RegistrationPage registrationPage;

    private String name;
    private String email;
    private String password;
    private String password5;

    @Before
    public void setUp() {
        faker = new Faker();
        name = faker.name().firstName();
        email = faker.internet().emailAddress();
        password5 = faker.internet().password(5,5);
        password = faker.internet().password(6,10);

        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        //driver = new FirefoxDriver();

        driver.get(Constants.URLFORTESTS);

        mainPage = new MainPage(driver);
        mainPage.waitLoadMainPage();
        mainPage.clickLogInButton();

        loginPage = new LoginPage(driver);
        loginPage.waitLoadLogInPage();
        loginPage.clickRegistration();

        registrationPage = new RegistrationPage(driver);
        registrationPage.waitLoadRegistrationPage();
    }

    @Test
    public void checkRegistration(){
        registrationPage.setTextInRegistrationForm(name,email,password);

        loginPage.waitLoadLogInPage();
    }

    @Test
    public void checkIncorrectPassInput(){
        registrationPage.setIncorrectPassAndCheckMessage(password5);

    }

    @After
    public void closeWindow(){
        driver.quit();
    }
}
