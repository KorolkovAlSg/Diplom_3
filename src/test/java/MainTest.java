import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;

import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import pageobjects.MainPage;
import webdriver.Browsers;

import static constans.Constants.MAIN_PAGE;

public class MainTest {

    private WebDriver driver;
    private Browsers browser;

    private MainPage mainPage;

    @Before
    public void setUp() throws Exception {
        browser = new Browsers();

        //Аргумент метода setUpBrowser - название браузера, в котором будут проведены тесты
        //доступные на выбор аргументы: yandex, firefox, chrome
        driver = browser.setUpBrowser("yandex", MAIN_PAGE);

        mainPage = new MainPage(driver);
    }

    @DisplayName("Переход к разделу Булки")
    @Description("Клик Соусы -> клик Булки -> проверь, содержит ли аттрибут class значение tab_tab_type_current__2BEPc")
    @Test
    public void checkBunSection(){
        mainPage.waitLoadMainPage();
        Assert.assertTrue(mainPage.checkBunSection());
    }

    @DisplayName("Переход к разделу Соусы")
    @Description("Клик Соусы -> проверь, содержит ли аттрибут class значение tab_tab_type_current__2BEPc")
    @Test
    public void checkSouceSection(){
        mainPage.waitLoadMainPage();
        Assert.assertTrue(mainPage.checkSouceSection());
    }

    @DisplayName("Переход к разделу Начинки")
    @Description("Клик Начинки -> проверь, содержит ли аттрибут class значение tab_tab_type_current__2BEPc")
    @Test
    public void checkMainSection(){
        mainPage.waitLoadMainPage();
        Assert.assertTrue(mainPage.checkMainSection());
    }

    @After
    public void closeWindow(){
        driver.quit();
    }
}
