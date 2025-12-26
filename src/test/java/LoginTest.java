import api.User;
import constans.Constants;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import net.datafaker.Faker;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;

import org.junit.Before;
import org.junit.After;
import org.junit.Test;

import pageobjects.LoginPage;
import pageobjects.MainPage;
import pageobjects.RecoverPassPage;
import pageobjects.RegistrationPage;
import serialization.ProfileUser;
import webdriver.Browsers;

public class LoginTest {
    private Faker faker;

    private ProfileUser profile;
    private User user;

    private Browsers browser;
    private WebDriver driver;

    private MainPage mainPage;
    private LoginPage loginPage;
    private RegistrationPage registrationPage;
    private RecoverPassPage recoverPassPage;

    @Before
    public void setUp() throws Exception {
        faker = new Faker();
        profile = new ProfileUser(faker.internet().emailAddress(), faker.internet().password(6,6), faker.name().username());
        user = new User(profile);
        user.createUser();

        browser = new Browsers();

        //Аргументы метода setUpBrowser(browser - название браузера, в котором будут проведены тесты, URL - стартовое окно браузера)
        //доступные на выбор аргументы:
        // browser: yandex, firefox, chrome
        // URL: MAIN_PAGE - главная страница , REGISTRATION_PAGE - страница регистрации
        driver = browser.setUpBrowser("yandex", Constants.MAIN_PAGE);

        mainPage = new MainPage(driver);
        loginPage = new LoginPage(driver);
        recoverPassPage = new RecoverPassPage(driver);
        registrationPage = new RegistrationPage(driver);
    }

    @DisplayName("Авторизация пользователя через кнопку \"Войти в аккаунт\"")
    @Description("Тест начинается на главной странице \"https://stellarburgers.education-services.ru/\"," +
            " после клика по \"Войти в аккаунт\" открывается страница авторизации \"https://stellarburgers.education-services.ru/login\", " +
            "вводятся валидные email в поле Email и пароль в поле Пароль, нажимается кнопка \"Войти\" и" +
            " проверяется, что после клика загружена главная страница и пользователь авторизован")
    @Test
    public void checkLoginInMainPage(){
        mainPage.clickLogInButton();
        loginPage.waitLoadLogInPage();
        loginPage.setTextInLogInForm(profile.getEmail(), profile.getPassword());
        loginPage.clickLogInButton();
        mainPage.waitLoadMainPage();

        Assert.assertTrue(mainPage.isDisplayed());
    }

    @DisplayName("Авторизация пользователя через кнопку \"Личный кабинет\"")
    @Description("Тест начинается на главной странице \"https://stellarburgers.education-services.ru/\"," +
            " после клика по \"Личный кабинет\" открывается страница авторизации \"https://stellarburgers.education-services.ru/login\", " +
            "вводятся валидные email в поле Email и пароль в поле Пароль, нажимается кнопка \"Войти\" и" +
            " проверяется, что после клика загружена главная страница c кнопкой оформить заказ")
    @Test
    public void checkLoginWithPersonalAccount(){
        mainPage.clickPersonalAccount();
        loginPage.waitLoadLogInPage();
        loginPage.setTextInLogInForm(profile.getEmail(), profile.getPassword());
        loginPage.clickLogInButton();
        mainPage.waitLoadMainPage();

        Assert.assertTrue(mainPage.isDisplayed());
    }

    @DisplayName("Авторизация пользователя через линк Войти страницы регистрации")
    @Description("Тест начинается на главной странице \"https://stellarburgers.education-services.ru/\"," +
            " кликается кнопка \"Войти в аккаунт\" открывается страница авторизации \"https://stellarburgers.education-services.ru/login\", " +
            "кликается линк \"Зарегистрироваться \" открывается страница регистрации \"https://stellarburgers.education-services.ru/register\"," +
            "кликается линк \"Войти\" открывается страница авторизации \"https://stellarburgers.education-services.ru/login\"," +
            " вводятся валидные email в поле Email и пароль в поле Пароль, нажимается кнопка \"Войти\" и" +
            " проверяется, что после клика загружена главная страница c кнопкой оформить заказ")
    @Test
    public void checkLoginWithRegistrationPage(){
        mainPage.clickLogInButton();
        loginPage.waitLoadLogInPage();
        loginPage.clickRegistration();
        registrationPage.waitLoadRegistrationPage();
        registrationPage.clickTextLogin();
        loginPage.waitLoadLogInPage();
        loginPage.setTextInLogInForm(profile.getEmail(), profile.getPassword());
        loginPage.clickLogInButton();
        mainPage.waitLoadMainPage();

        Assert.assertTrue(mainPage.isDisplayed());
    }

    @DisplayName("Авторизация пользователя через линк Войти страницы восстановления пароля")
    @Description("Тест начинается на главной странице \"https://stellarburgers.education-services.ru/\"," +
            " кликается кнопка \"Войти в аккаунт\" открывается страница авторизации \"https://stellarburgers.education-services.ru/login\", " +
            "кликается линк \"Восстановить пароль\" открывается страница восстановления пароля \"https://stellarburgers.education-services.ru/forgot-password\"," +
            "кликается линк \"Войти\" открывается страница авторизации \"https://stellarburgers.education-services.ru/login\"," +
            " вводятся валидные email в поле Email и пароль в поле Пароль, нажимается кнопка \"Войти\" и" +
            " проверяется, что после клика загружена главная страница c кнопкой оформить заказ")
    @Test
    public void checkLoginWithRecoverPassPage(){
        mainPage.clickLogInButton();
        loginPage.waitLoadLogInPage();
        loginPage.clickRecoverPass();
        recoverPassPage.waitLoadRecoverPassPage();
        recoverPassPage.clickLogIn();
        loginPage.setTextInLogInForm(profile.getEmail(), profile.getPassword());
        loginPage.clickLogInButton();
        mainPage.waitLoadMainPage();

        Assert.assertTrue(mainPage.isDisplayed());
    }

    @After
    public void closeWindowAnd(){
        driver.quit();
        user.deleteUser();
    }

}
