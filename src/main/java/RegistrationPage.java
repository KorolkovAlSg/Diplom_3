import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.Assert.assertEquals;

public class RegistrationPage {
    private final WebDriver driver;

    public RegistrationPage(WebDriver driver) {
        this.driver = driver;
    }

    //Локатор для заголовка Регистрация
    private By titleRegistration = By.xpath(".//h2[text()='Регистрация']");

    //Локатор поля Имя
    private By inputFieldName = By.xpath(".//fieldset[1]/div/div/input");

    //Локатор поля Email
    private By inputFieldEmail = By.xpath(".//fieldset[2]/div/div/input");

    //Локатор поля Пароль
    private By inputFieldPassword = By.xpath(".//fieldset[3]/div/div/input");

    //Локатор кнопки Зарегистрироваться
    private By registerButton = By.xpath(".//button[text()='Зарегистрироваться']");

    //Локатор текста "Некорректный пароль"
    private By textIncorrectPassword = By.xpath(".//p[text()='Некорректный пароль']");

    private By textLogin = By.xpath(".//a[text()='Войти']");

    @Step("Заполнить поля формы Регистрации и нажать кнопку Зарегистрироваться")
    public void setTextInRegistrationForm(String name, String email, String password){
        driver.findElement(inputFieldName).click();
        driver.findElement(inputFieldName).sendKeys(name);
        driver.findElement(inputFieldEmail).click();
        driver.findElement(inputFieldEmail).sendKeys(email);
        driver.findElement(inputFieldPassword).click();
        driver.findElement(inputFieldPassword).sendKeys(password);
        clickInRegisterButton();
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
    public void setIncorrectPassAndCheckMessage(String incorrectPass){
        driver.findElement(inputFieldPassword).click();
        driver.findElement(inputFieldPassword).sendKeys(incorrectPass);
        clickInRegisterButton();

        assertEquals("Текст ошибки: \"Некорректный пароль\"","Некорректный пароль",driver.findElement(textIncorrectPassword).getText());
    }

    @Step("waitLoadRegistrationPage")
    public void waitLoadRegistrationPage(){
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(driver -> (driver.findElement(titleRegistration).getText() != null
                && !driver.findElement(titleRegistration).getText().isEmpty()
        ));
    }



}
