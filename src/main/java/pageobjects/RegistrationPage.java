package pageobjects;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.locators.RelativeLocator;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class RegistrationPage {
    private final WebDriver driver;

    public RegistrationPage(WebDriver driver) {
        this.driver = driver;
    }

    //Локатор для заголовка Регистрация
    private By titleRegistration = By.xpath(".//h2[text()='Регистрация']");

    //Локатор поля Имя
    private By inputFieldName = RelativeLocator.with(By.tagName("input")).above(By.xpath(".//label[text()='Email']"));

    //Локатор поля Email
    private By inputFieldEmail = RelativeLocator.with(By.tagName("input")).above(By.xpath(".//label[text()='Пароль']"));

    //Локатор поля Пароль
    private By inputFieldPassword = RelativeLocator.with(By.tagName("input")).below(By.xpath(".//label[text()='Email']"));

    //Локатор кнопки Зарегистрироваться
    private By registerButton = By.xpath(".//button[text()='Зарегистрироваться']");

    //Локатор текста "Некорректный пароль"
    private By textIncorrectPassword = RelativeLocator.with(By.xpath(".//p[text()='Некорректный пароль']")).below(inputFieldPassword);

    private By textLogin = By.xpath(".//a[text()='Войти']");

    @Step("Заполнить поля формы Регистрации")
    public void setTextInRegistrationForm(String name, String email, String password){
        driver.findElement(inputFieldName).click();
        driver.findElement(inputFieldName).sendKeys(name);
        driver.findElement(inputFieldEmail).click();
        driver.findElement(inputFieldEmail).sendKeys(email);
        driver.findElement(inputFieldPassword).click();
        driver.findElement(inputFieldPassword).sendKeys(password);
    }

    @Step("Клик Зарегистрироваться")
    public void clickInRegisterButton(){
        driver.findElement(registerButton).click();
    }

    @Step("Клик Войти")
    public void clickTextLogin(){
        driver.findElement(textLogin).click();
    }

    @Step("Ввести пароль из 5 символов, нажать Зарегистрироваться и проверить текст ошибки")
    public boolean setIncorrectPassAndCheckMessage(String incorrectPass){
        driver.findElement(inputFieldPassword).click();
        driver.findElement(inputFieldPassword).sendKeys(incorrectPass);
        clickInRegisterButton();

        return driver.findElement(textIncorrectPassword).isDisplayed();
    }

    @Step("waitLoadRegistrationPage")
    public void waitLoadRegistrationPage(){
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(driver -> (driver.findElement(titleRegistration).getText() != null
                && !driver.findElement(titleRegistration).getText().isEmpty()
        ));
    }



}
