package pages;


import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.List;
import java.time.Duration;

@Getter
public class AboutUserForm {

    WebDriver driver;

    //Поле имя пользователя
    private final By USER_FIRST_NAME_FIELD = By.xpath("//input[@placeholder = '* Имя']");

    //Поле фамилия пользователя
    private final By USER_SECOND_NAME_FIELD = By.xpath("//input[@placeholder = '* Фамилия']");

    //Поле адрес пользователя
    private final By ADDRESS_FIELD = By.xpath("//input[@placeholder = '* Адрес: куда привезти заказ']");

    //Поле станция метро
    private final By METRO_STATION_FIELD = By.xpath("//input[@placeholder = '* Станция метро']");

    //список станций метро
    private final By METRO_STATIONS_LIST = By.xpath("//ul[@class='select-search__options']//li");

    //Поле номер телефона
    private final By PHONE_NUMBER_FIELD = By.xpath("//input[@placeholder = '* Телефон: на него позвонит курьер']");

    //Кнопка "далее"
    private final By NEXT_BUTTON = By.xpath("//button[@class='Button_Button__ra12g Button_Middle__1CSJM']");




    public AboutUserForm(WebDriver driver) {
        this.driver = driver;
    }

    //метод заполнения поля имя пользователя
    public void editUserFirstNameField(String textUserFirstName) {
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.visibilityOfElementLocated(USER_FIRST_NAME_FIELD));
        driver.findElement(USER_FIRST_NAME_FIELD).clear();
        driver.findElement(USER_FIRST_NAME_FIELD).sendKeys(textUserFirstName);
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.textToBePresentInElementValue(USER_FIRST_NAME_FIELD, textUserFirstName));

    }


    //метод заполнения поля фамилия пользователя
    public void editUserSecondNameField(String textUserSecondName) {
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.visibilityOfElementLocated(USER_SECOND_NAME_FIELD));
        driver.findElement(USER_SECOND_NAME_FIELD).clear();
        driver.findElement(USER_SECOND_NAME_FIELD).sendKeys(textUserSecondName);
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.textToBePresentInElementValue(USER_SECOND_NAME_FIELD, textUserSecondName));

    }

    //метод заполнения поля адрес
    public void editAddressField(String textAddress) {
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.visibilityOfElementLocated(ADDRESS_FIELD));
        driver.findElement(ADDRESS_FIELD).clear();
        driver.findElement(ADDRESS_FIELD).sendKeys(textAddress);
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.textToBePresentInElementValue(ADDRESS_FIELD, textAddress));

    }

    //метод заполнения поля метро по вводу текста
    public void editMetroStationField(String textMetroStation) {
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.visibilityOfElementLocated(METRO_STATION_FIELD));

        driver.findElement(METRO_STATION_FIELD).sendKeys(textMetroStation);
        driver.findElement(METRO_STATIONS_LIST).click();

        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.textToBePresentInElementValue(METRO_STATION_FIELD, textMetroStation));

    }


    //метод заполнения поля метро по выбору из списка
    public void editMetroStationField(int number) {
        number = number-1;
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.visibilityOfElementLocated(METRO_STATION_FIELD));

        driver.findElement(METRO_STATION_FIELD).click();
        List<WebElement> list = driver.findElements(METRO_STATIONS_LIST);
        String s1 = list.get(number).getText();
        String s2 = list.get(1).getText();
        if(number <= list.size() && number > -1) {
            list.get(number).click();
            new WebDriverWait(driver, Duration.ofSeconds(3))
                    .until(ExpectedConditions.textToBePresentInElementValue(METRO_STATION_FIELD, s1));
        }
        else
        {list.get(1).click();
            new WebDriverWait(driver, Duration.ofSeconds(3))
                    .until(ExpectedConditions.textToBePresentInElementValue(METRO_STATION_FIELD, s2));}


    }


    //метод заполнения поля номер телефона
    public void editPhoneNumberField(String textPhoneNumber) {
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.visibilityOfElementLocated(PHONE_NUMBER_FIELD));
        driver.findElement(PHONE_NUMBER_FIELD).clear();
        driver.findElement(PHONE_NUMBER_FIELD).sendKeys(textPhoneNumber);
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.textToBePresentInElementValue(PHONE_NUMBER_FIELD, textPhoneNumber));

    }

    //клик по кнопке "Далее"
    public void clickNextButton(){
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.elementToBeClickable(NEXT_BUTTON));
        driver.findElement(NEXT_BUTTON).click();


    }

    //полный процесс заполнения формы 1 . На случай, если не надо проверять поля по отдельности
    public void fillFullAboutUserForm(String userFirstName, String userSecondName, String userAddress, int metroStationNumber,
                                      String phoneNumber){
        editUserFirstNameField(userFirstName);
        editUserSecondNameField(userSecondName);
        editAddressField(userAddress);
        editMetroStationField(metroStationNumber);
        editPhoneNumberField(phoneNumber);
        clickNextButton();
    }

}