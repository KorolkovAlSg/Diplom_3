package pageobjects;

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

    //Локатор для кнопки "Оформить заказ"
    private By getOrderButton = By.xpath(".//button[text()='Оформить заказ']");

    //Локатор Личный кабинет
    private By personalAccount = By.xpath(".//p[text()='Личный Кабинет']");

    //Локатор Соберите бургер
    private By textCreateBurger = By.xpath(".//h1[text()='Соберите бургер']");

    private By main = By.xpath(".//span[text()='Начинки']/parent::div[starts-with(@class, 'tab_tab__1SPyG')]");

    private By souce = By.xpath(".//span[text()='Соусы']/parent::div[starts-with(@class, 'tab_tab__1SPyG')]");

    private By buns = By.xpath(".//span[text()='Булки']/parent::div[starts-with(@class, 'tab_tab__1SPyG')]");

    @Step("Клик Войти в аккаунт")
    public void clickLogInButton(){
        driver.findElement(logInButton).click();
    }

    @Step("Клик Личный кабинет")
    public void clickPersonalAccount(){
        driver.findElement(personalAccount).click();
    }

    @Step("Клик Соусы -> клик Булки -> проверь, содержит ли аттрибут class значение tab_tab_type_current__2BEPc")
    public boolean checkBunSection(){
        driver.findElement(souce).click();
        driver.findElement(buns).click();
        return driver.findElement(buns).getAttribute("class").contains("tab_tab_type_current__2BEPc");
    }

    @Step("Клик Соусы -> проверь, содержит ли аттрибут class значение tab_tab_type_current__2BEPc")
    public boolean checkSouceSection(){
        driver.findElement(souce).click();
        return driver.findElement(souce).getAttribute("class").contains("tab_tab_type_current__2BEPc");
    }

    @Step("Клик Начинки -> проверь, содержит ли аттрибут class значение tab_tab_type_current__2BEPc")
    public boolean checkMainSection(){
        driver.findElement(main).click();
        return driver.findElement(main).getAttribute("class").contains("tab_tab_type_current__2BEPc");
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

    @Step("Кнопка Оформить заказ видна")
    public boolean isDisplayed(){
        return driver.findElement(getOrderButton).isDisplayed();
    }
}
