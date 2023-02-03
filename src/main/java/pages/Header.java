package pages;

import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

@Getter
public class Header {

    //кнопка "заказать" в header
    private final By ZAKAZ_BUTTON_HEADER = By.xpath("//button[@class = 'Button_Button__ra12g']");
    //кнопка "Статус Заказа"
    private final By STATUS_BUTTON_HEADER = By.xpath("//button[@class='Header_Link__1TAG7']");
    //кнопка "Самокат" для перехода на главную страницу Самоката
    private final By SAMOKAT_BUTTON_HEADER = By.xpath("//a[@class='Header_LogoScooter__3lsAR']");
    WebDriver driver;
    public Header(WebDriver driver) {
        this.driver = driver;
    }

    //клик по кнопке "Заказать"
    public void clickZakazButtonHeader() {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(ZAKAZ_BUTTON_HEADER));
        driver.findElement(ZAKAZ_BUTTON_HEADER).click();
    }

    //клик по кнопке "Статус заказа"
    public void clickStatusButtonHeader() {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(STATUS_BUTTON_HEADER));
        driver.findElement(STATUS_BUTTON_HEADER).click();
    }

    //клик по кнопке "Самокат"
    public void clickSamokatButtonHeader() {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(SAMOKAT_BUTTON_HEADER));
        driver.findElement(SAMOKAT_BUTTON_HEADER).click();
    }

}



