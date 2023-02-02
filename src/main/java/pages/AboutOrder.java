package pages;

import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.time.Duration;
import java.util.List;

@Getter
public class AboutOrder {
    private WebDriver driver;

    //Поле ввода даты
    private final By ORDER_DATE_FIELD = By.xpath("//input[@placeholder = '* Когда привезти самокат']");

    //Поле ввода срока аренды
    private final By ORDER_PERIOD_FIELD = By.xpath("//div[@class = 'Dropdown-placeholder']");

    //Список дней для ввода в поле ввода срока аренды
    private final By ORDER_DAYS_COUNT = By.xpath("//div[@class = 'Dropdown-menu']//div[@class='Dropdown-option']");

    //Поле ввода срока аренды с уже выбранным значением
    private final By ORDER_PERION_CHOSEN_FIELD = By.xpath("//div[@class = 'Dropdown-placeholder is-selected']");

    //Поле выбора цвета самоката (цвет не выбран)
    private final By SCOOTER_COLOR = By.xpath("//div[@class = 'Order_Checkboxes__3lWSI']");

    //Поле выбора цвета самоката (выбран)
    private final By SCOOTER_COLOR_SET = By.xpath("//div[@class = 'Order_Checkboxes__3lWSI Order_FilledContainer__2MKAk']");

    //локатор чекбокса выбора цвета: черный жемчуг
    private final By BLACK_PEARL_COLOR = By.xpath("//input[@id='black']");

    //локатор чекбокса выбора цвета: серая безысходность
    private final By GREY_COLOR = By.xpath("//input[@id='grey']");

    //Поле ввода комментариев курьеру
    private final By COMMENTS_TO_COURIER = By.xpath("//input[@placeholder = 'Комментарий для курьера']");

    //кнопка окончательного  Заказа . Называется "Заказать"
    private final By BUTTON_FINAL_ZAKAZ = By.xpath("//button[@class='Button_Button__ra12g Button_Middle__1CSJM']");

    //кнопка "Да" в окончательном вопросе заказа
    private final By BUTTON_YES = By.xpath("//button[text()= 'Да']");

    //кнопка "Нет" в окончательном вопросе заказа
    private final By BUTTON_NO = By.xpath("//button[text()= 'Нет']");

    private final By BUTTON_POSMOTRET_STATUS = By.xpath("//button[text() = 'Посмотреть статус']");






    //конструктор
    public AboutOrder(WebDriver driver){
        this.driver = driver;

    }





    //    метод выбора даты, когда необходимо привезти самокат
    public void chooseOrderDate (int day, int month, int year){
        String date = day + "." + month + "." + year;

        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(ORDER_DATE_FIELD));
        driver.findElement(ORDER_DATE_FIELD).sendKeys(date, Keys.ENTER);



    }



    //метод выбора срока аренды самоката
    public void chooseOrderDaysCount (int daysCount){
        daysCount = daysCount -1;
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(ORDER_PERIOD_FIELD));
        driver.findElement(ORDER_PERIOD_FIELD).click();
        List <WebElement> orderDaysList = driver.findElements(ORDER_DAYS_COUNT);
        String s1 = orderDaysList.get(daysCount).getText();
        String s2 = orderDaysList.get(1).getText();


        if (daysCount <= orderDaysList.size()) {
            orderDaysList.get(daysCount).click();
            new WebDriverWait(driver, Duration.ofSeconds(5))
                    .until(ExpectedConditions.textToBe(ORDER_PERION_CHOSEN_FIELD, s1));
        }else {
            orderDaysList.get(1).click();
            new WebDriverWait(driver, Duration.ofSeconds(5))
                    .until(ExpectedConditions.textToBe(ORDER_PERION_CHOSEN_FIELD, s2));
        }
    }


    //        метод выбора цвета самоката
    public void chooseSamokatColor(By color){
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(color));


        boolean checkBox = driver.findElement(color).isSelected();

        if(checkBox == true){

            driver.findElement(color).click();
            new WebDriverWait(driver, Duration.ofSeconds(5))
                    .until(ExpectedConditions.elementSelectionStateToBe(color, false));

        }else {
            driver.findElement(color).click();
            new WebDriverWait(driver, Duration.ofSeconds(5))
                    .until(ExpectedConditions.elementSelectionStateToBe(color, true));

        }
    }



    //метод смены цвета самоката на случай, если driver в классе OrderFlowTest нужен не static

//    public void chooseSamokatColor(String cvet){
//
//       By color = greyColor;
//
//       if (cvet.equalsIgnoreCase( "Чёрный")){
//           color = blackPearlColor;
//       }
//        new WebDriverWait(driver, Duration.ofSeconds(5))
//                .until(ExpectedConditions.visibilityOfElementLocated(color));
//
//
//        boolean checkBox = driver.findElement(color).isSelected();
//
//        if(checkBox == true){
//
//            driver.findElement(color).click();
//            new WebDriverWait(driver, Duration.ofSeconds(5))
//                    .until(ExpectedConditions.elementSelectionStateToBe(color, false));
//
//        }else {
//            driver.findElement(color).click();
//            new WebDriverWait(driver, Duration.ofSeconds(5))
//                    .until(ExpectedConditions.elementSelectionStateToBe(color, true));
//
//        }
//    }

    public void setCOMMENTS_TO_COURIER(String textComment){

        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(COMMENTS_TO_COURIER));
        driver.findElement(COMMENTS_TO_COURIER).clear();
        driver.findElement(COMMENTS_TO_COURIER).sendKeys(textComment);

        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.textToBePresentInElementValue(COMMENTS_TO_COURIER, textComment));

    }

    public void clickButtonFinalOrder(){
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(BUTTON_FINAL_ZAKAZ));
        driver.findElement(BUTTON_FINAL_ZAKAZ).click();

    }



    //клик по кнопке "Да" или "Нет" в МО "Хотите оформить заказ
    public void click_Yes_No_Button(String yes_no){


        if (yes_no.equalsIgnoreCase("yes")) {
            new WebDriverWait(driver, Duration.ofSeconds(5))
                    .until(ExpectedConditions.elementToBeClickable(BUTTON_YES));
            driver.findElement(BUTTON_YES).click();

            new WebDriverWait(driver, Duration.ofSeconds(5))
                    .until(ExpectedConditions.invisibilityOfElementLocated(BUTTON_YES));
        }else {
            new WebDriverWait(driver, Duration.ofSeconds(5))
                    .until(ExpectedConditions.elementToBeClickable(BUTTON_NO));
            driver.findElement(BUTTON_NO).click();

            new WebDriverWait(driver, Duration.ofSeconds(5))
                    .until(ExpectedConditions.invisibilityOfElementLocated(BUTTON_NO));

        }

    }





    public void clickButtonNo(){
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(BUTTON_NO));
        driver.findElement(BUTTON_NO).click();

        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.invisibilityOfElementLocated(BUTTON_NO));
    }



    //метод заполнения всей формы о заказе и нажатие кнопки далее, без подтверждения
    public void fillFullAboutOrderForm(int day, int month, int year, int daysCount, By color, String textComment){

        chooseOrderDate(day,month,year);
        chooseOrderDaysCount(daysCount);
        chooseSamokatColor(color);
        setCOMMENTS_TO_COURIER(textComment);
        clickButtonFinalOrder();

    }

}


