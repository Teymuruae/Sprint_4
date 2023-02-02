package pages;

import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

@Getter
public class MainPage {

    private WebDriver driver;

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }


    //кнопка "заказать" в footer
    private final By ZAKAZ_BUTTON_FOOTER = By.xpath("//div[@class='Home_FinishButton__1_cWm']//button[text()='Заказать']");

    //список выпадашек - вопросов
    private final By VYPADASHKA_LIST = By.xpath("//div[@class = 'accordion']//div[@class = 'accordion__button']");

    //выпадашка текст
    private final By VYPADASHKA_TEXT_LIST = By.xpath("//div[@class='accordion__panel']");

    //кнопка куки
    private final By COOKIE_BUTTON = By.cssSelector(".App_CookieButton__3cvqF");




    //клик по кнопке "Заказать" в футере
    public void clickZakazButtonFooter() {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(ZAKAZ_BUTTON_FOOTER));
        driver.findElement(ZAKAZ_BUTTON_FOOTER).click();
    }

    //клик на выпадающее меню
    public String clickVypadashkaButton(int number) {
        number = number - 1;
        List<WebElement> buttonList = driver.findElements(VYPADASHKA_LIST);
        List<WebElement> textList = driver.findElements(VYPADASHKA_TEXT_LIST);
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(buttonList.get(number)));
        buttonList.get(number).click();

        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.attributeContains(buttonList.get(number), "aria-disabled", "true"));

        String s = textList.get(number).getText();
        System.out.println("Real text is:" + s);
        return s;

    }

    //клик по кнопке куки
    public void clickCookieButton() {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(COOKIE_BUTTON));
        driver.findElement(COOKIE_BUTTON).click();


    }
}

