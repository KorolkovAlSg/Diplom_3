import api.User;
import constans.Constants;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import net.datafaker.Faker;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;

import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import pageobjects.LoginPage;
import pageobjects.MainPage;
import pageobjects.RegistrationPage;
import serialization.ProfileUser;
import webdriver.Browsers;

import static org.apache.http.HttpStatus.SC_OK;
import static org.hamcrest.Matchers.*;

public class RegistrationTest {
    private Faker faker;

    private ProfileUser profile;
    private User user;

    private Browsers browser;
    private WebDriver driver;

    private MainPage mainPage;
    private LoginPage loginPage;
    private RegistrationPage registrationPage;

    @Before
    public void setUp() throws Exception {
        faker = new Faker();
        profile = new ProfileUser(faker.internet().emailAddress(), faker.internet().password(6,6), faker.name().username());
        user = new User(profile);

        browser = new Browsers();
        //Аргументы метода setUpBrowser(browser - название браузера, в котором будут проведены тесты, URL - стартовое окно браузера)
        //доступные на выбор аргументы:
        // browser: yandex, firefox, chrome
        // URL: MAIN_PAGE - главная страница , REGISTRATION_PAGE - страница регистрации
        driver = browser.setUpBrowser("yandex", Constants.REGISTRATION_PAGE);

        registrationPage = new RegistrationPage(driver);
        loginPage = new LoginPage(driver);
    }

    @DisplayName("Регистрация пользователя на странице регистрации")
    @Description("Тест начинается на странице регистрации \"https://stellarburgers.education-services.ru/register\"," +
            "вводятся валидные значения имени в поле Имя, email в поле Email и пароль в поле Пароль, нажимается кнопка \"Зарегистрироваться\" и" +
            " проверяется, что после клика загружена страница авторизации и пользователь создан с введенными пользователем значениями")
    @Test
    public void checkRegistration(){
        registrationPage.setTextInRegistrationForm(profile.getName(),profile.getEmail(),profile.getPassword());
        registrationPage.clickInRegisterButton();
        loginPage.waitLoadLogInPage();

        RestAssured.baseURI = Constants.MAIN_PAGE;
        user.logInUser()
                .then().statusCode(SC_OK)
                .and()
                .assertThat()
                .body("success", equalTo(true))
                .body("user.email", equalTo(profile.getEmail()))
                .body("user.name", equalTo(profile.getName()))
                .body("accessToken", not(emptyOrNullString()))
                .body("user.name", not(emptyOrNullString()));
    }

    @DisplayName("Ошибка для некорректного пароля из 5 символов")
    @Description("Тест начинается на странице регистрации \"https://stellarburgers.education-services.ru/register\"," +
            "вводится невалидное значение пароля в поле Пароль, нажимается кнопка \"Зарегистрироваться\" и" +
            " проверяется, что после клика появился текст Некорректный пароль под полем Пароль")
    @Test
    public void checkIncorrectPassInput(){
       Assert.assertTrue(registrationPage.setIncorrectPassAndCheckMessage(faker.internet().password(5,5)));
    }

    @After
    public void closeWindow() {
        driver.quit();
        if (user.logInUser().body().path("success")) {
            user.deleteUser();
        }
    }
}
