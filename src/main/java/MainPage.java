import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MainPage {
    private final WebDriver driver;

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    //Локатор для кнопки "Войти в аккаунт"
    private By logInButton = By.xpath(".//button[text()='Войти в аккаунт']");

    //Локатор Личный кабинет
    private By personalAccount = By.xpath(".//p[text()='Личный Кабинет']");

    //Локатор Соберите бургер
    private By textCreateBurger = By.xpath(".//section[1]/h1[text()='Соберите бургер']");

    private By main = By.xpath(".//span[text()='Начинки']");
    private By mainTitle = By.xpath(".//h2[text()='Начинки']");

    private By souce = By.xpath(".//span[text()='Соусы']");
    private By souceTitle = By.xpath(".//h2[text()='Соусы']");

    private By buns = By.xpath(".//span[text()='Булки']");
    private By bunsTitle = By.xpath(".//h2[text()='Булки']");

    @Step("Клик Войти в аккаунт")
    public void clickLogInButton(){
        driver.findElement(logInButton).click();
    }

    @Step("Клик Личный кабинет")
    public void clickPersonalAccount(){
        driver.findElement(personalAccount).click();
    }

    @Step("Клик Начинки -> подождать появления Начинки -> клик Соусы -> подождать появления Соусы -> клик Булки -> Подождать появления Булки")
    public void checkSectionTransitionsWorking(){
        driver.findElement(main).click();
        waitLoadElement(driver.findElement(mainTitle));
        driver.findElement(souce).click();
        waitLoadElement(driver.findElement(souceTitle));
        driver.findElement(buns).click();
        waitLoadElement(driver.findElement(bunsTitle));
    }

    @Step("waitLoadElement")
    public void waitLoadElement(WebElement element){
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(driver -> (element.getText() != null
                && !element.getText().isEmpty()
        ));
    }

    @Step("waitLoadMainPage")
    public void waitLoadMainPage(){
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(driver -> (driver.findElement(textCreateBurger).getText() != null
                && !driver.findElement(textCreateBurger).getText().isEmpty()
        ));
    }
}
