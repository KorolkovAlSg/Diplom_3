import constans.Constants;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

import org.junit.Before;
import org.junit.After;
import org.junit.Test;

public class LoginTest {

    private WebDriver driver;

    private MainPage mainPage;
    private LoginPage loginPage;
    private RegistrationPage registrationPage;
    private RecoverPassPage recoverPassPage;

    private String email;
    private String password;

    @Before
    public void setUp() {
        email = "korolkov.al.sg@yandex.ru";
        password = "123456";

        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        //driver = new FirefoxDriver();

        mainPage = new MainPage(driver);
        loginPage = new LoginPage(driver);
        recoverPassPage = new RecoverPassPage(driver);
        registrationPage = new RegistrationPage(driver);

        driver.get(Constants.URLFORTESTS);
    }

    @Test
    public void checkLoginInMainPage(){
        mainPage.clickLogInButton();
        loginPage.waitLoadLogInPage();
        loginPage.setTextInLogInForm(email,password);
        mainPage.waitLoadMainPage();
    }

    @Test
    public void checkLoginWithPersonalAccount(){
        mainPage.clickPersonalAccount();
        loginPage.waitLoadLogInPage();
        loginPage.setTextInLogInForm(email,password);
        mainPage.waitLoadMainPage();
    }

    @Test
    public void checkLoginWithRegistrationPage(){
        mainPage.clickLogInButton();
        loginPage.waitLoadLogInPage();
        loginPage.clickRegistration();
        registrationPage.waitLoadRegistrationPage();
        registrationPage.clickTextLogin();
        loginPage.waitLoadLogInPage();
        loginPage.setTextInLogInForm(email,password);
        mainPage.waitLoadMainPage();
    }
    @Test
    public void checkLoginWithRecoverPassPage(){
        mainPage.clickLogInButton();
        loginPage.waitLoadLogInPage();
        loginPage.clickRecoverPass();
        recoverPassPage.waitLoadRecoverPassPage();
        recoverPassPage.clickLogIn();
        loginPage.setTextInLogInForm(email,password);
        mainPage.waitLoadMainPage();
    }

    @After
    public void closeWindow(){
        driver.quit();
    }

}
