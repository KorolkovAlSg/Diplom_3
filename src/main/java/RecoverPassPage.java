import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class RecoverPassPage {
    private final WebDriver driver;

    public RecoverPassPage(WebDriver driver) {
        this.driver = driver;
    }

    private By titleRecoverPass = By.xpath(".//h2[text()='Восстановление пароля']");

    private By textLinkLogin = By.xpath(".//a[text()='Войти']");

    @Step("Клик Войти")
    public void clickLogIn(){
        driver.findElement(textLinkLogin).click();
    }

    @Step("waitLoadRecoverPassPage")
    public void waitLoadRecoverPassPage(){
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(driver -> (driver.findElement(titleRecoverPass).getText() != null
                && !driver.findElement(titleRecoverPass).getText().isEmpty()
        ));
    }
}
