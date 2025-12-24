import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {

    private final WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    //Локатор текстовой ссылки Зарегистрироваться
    private By linkTextRegistration = By.xpath(".//a[text()='Зарегистрироваться']");

    private By linkTextPasswordRecover = By.xpath(".//a[text()='Восстановить пароль']");

    //Локатор для заголовка Вход страницы входа
    private By titleEntry = By.xpath(".//h2[text()='Вход']");

    //Локатор поля Email
    private By inputFieldEmail = By.xpath(".//fieldset[1]/div/div/input");

    //Локатор поля Пароль
    private By inputFieldPassword = By.xpath(".//fieldset[2]/div/div/input");

    private By logInButton = By.xpath(".//button[text()='Войти']");

    @Step("Клик по полю ввода Email -> ввод email -> клик по полю Password -> ввод пароля")
    public void setTextInLogInForm(String email, String password){
        driver.findElement(inputFieldEmail).click();
        driver.findElement(inputFieldEmail).sendKeys(email);
        driver.findElement(inputFieldPassword).click();
        driver.findElement(inputFieldPassword).sendKeys(password);
        clickLogInButton();
    }

    @Step("Клик Зарегистрироваться")
    public void clickRegistration(){
        driver.findElement(linkTextRegistration).click();
    }

    @Step("Клик Войти")
    public void clickLogInButton(){
        driver.findElement(logInButton).click();
    }

    @Step("Клик Восстановить пароль")
    public void clickRecoverPass(){
        driver.findElement(linkTextPasswordRecover).click();
    }

    @Step("waitLoadLogInPage")
    public void waitLoadLogInPage(){
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(driver -> (driver.findElement(titleEntry).getText() != null
                && !driver.findElement(titleEntry).getText().isEmpty()
        ));
    }
}
