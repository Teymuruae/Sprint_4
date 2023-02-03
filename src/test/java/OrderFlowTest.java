import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import pages.AboutOrder;
import pages.AboutUserForm;
import pages.Header;
import pages.MainPage;

import java.time.Duration;

@RunWith(Parameterized.class)
public class OrderFlowTest {

    private static WebDriver driver1;
    public static AboutOrder aboutOrder = new AboutOrder(driver1);
    //Список переменных
    private final String url = "https://qa-scooter.praktikum-services.ru/";
    private final String userFirstName;
    private final String userSecondName;
    private final String userAddress;
    private final String userPhoneNumber;
    private final int metroStation;
    private final int orderDateDay;
    private final int orderDateMonth;
    private final int orderDateYear;
    private final int orderDayCount;
    private final String commentToCourier;
    private final By samokatColor;
    private final boolean result;
    private final String yes_no_button;

    //конструктор
    public OrderFlowTest(String browser, String userFirstName, String userSecondName, String userAddress,
                         int metroStation, String userPhoneNumber, int orderDateDay, int orderDateMonth, int orderDateYear,
                         int orderDayCount, By samokatColor, String commentToCourier, boolean result, String yes_no_button) {

        // инициализация метода выбора браузера
        chooseBrowser(browser);
        this.userFirstName = userFirstName;
        this.userSecondName = userSecondName;
        this.userAddress = userAddress;
        this.metroStation = metroStation;
        this.userPhoneNumber = userPhoneNumber;
        this.orderDateDay = orderDateDay;
        this.orderDateMonth = orderDateMonth;
        this.orderDateYear = orderDateYear;
        this.orderDayCount = orderDayCount;
        this.samokatColor = samokatColor;
        this.commentToCourier = commentToCourier;
        this.result = result;
        this.yes_no_button = yes_no_button;

    }

    @Parameterized.Parameters (name = "Тестовые данные: {0}{1}{2}")
    public static Object[][] getData() {

        return new Object[][]{
                {"Chrome", "Петр", "Иванов", "Бульвар имени Ленина 1, деревня Кукушкино", 20, "+78889993225",
                        20, 02, 2023, 5, aboutOrder.getBLACK_PEARL_COLOR(), "Аккуратнее на дорогах", true, "yes"},

                {"Chrome", "Пётр", "Иванов", "Бульвар имени Ленина 1, деревня Кукушкино", 20, "+78889993225",
                        20, 02, 2023, 5, aboutOrder.getBLACK_PEARL_COLOR(), "Аккуратнее на дорогах", false, "no"},
                {"Chrome", "Пётр", "Иванов", "Бульвар имени Ленина 1, деревня Кукушкино", 20, "+78889993225",
                        20, 02, 2023, 5, aboutOrder.getGREY_COLOR(), "Аккуратнее на дорогах", false, "no"}
        };
    }

    // метод выбора браузера
    public void chooseBrowser(String browser) {
        if (browser.equalsIgnoreCase("Chrome")) {
            driver1 = new ChromeDriver();
        } else driver1 = new FirefoxDriver();

    }

    //тест с подтверждением заказа. Начинаем с кнопки заказать в Footer
    @Test
    public void fullOrderFlowTest1() {
        WebDriver driver = driver1;
        driver.get(url);

        MainPage mainPage = new MainPage(driver);
        AboutUserForm aboutUserForm = new AboutUserForm(driver);
        AboutOrder aboutOrder = new AboutOrder(driver);

        //прокрутка вниз главной страницы
        WebElement element = driver.findElement(mainPage.getZAKAZ_BUTTON_FOOTER());
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);

        //кликаем кнопку согласие с куки сайтом
        mainPage.clickCookieButton();
        mainPage.clickZakazButtonFooter();
        //Первая страница заказа
        aboutUserForm.editUserFirstNameField(userFirstName);
        aboutUserForm.editUserSecondNameField(userSecondName);
        aboutUserForm.editAddressField(userAddress);
        aboutUserForm.editMetroStationField(metroStation);
        aboutUserForm.editPhoneNumberField(userPhoneNumber);
        aboutUserForm.clickNextButton();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        //вторая страница заказа
        aboutOrder.chooseOrderDate(orderDateDay, orderDateMonth, orderDateYear);
        aboutOrder.chooseOrderDaysCount(orderDayCount);
        aboutOrder.chooseSamokatColor(samokatColor);
        aboutOrder.setCOMMENTS_TO_COURIER(commentToCourier);
        aboutOrder.clickButtonFinalOrder();
        aboutOrder.click_Yes_No_Button(yes_no_button);

        Assert.assertEquals(result, driver.findElements(aboutOrder.getBUTTON_POSMOTRET_STATUS()).size() != 0);

    }

    //тест с подтверждением заказа. Начинаем с кнопки заказать в Header
    @Test
    public void fullOrderFlowTest2() {
        WebDriver driver = driver1;
        driver.get(url);

        MainPage mainPage = new MainPage(driver);
        AboutUserForm aboutUserForm = new AboutUserForm(driver);
        AboutOrder aboutOrder = new AboutOrder(driver);
        Header header = new Header(driver);

        //кликаем кнопку согласие с куки сайтом
        mainPage.clickCookieButton();
        header.clickZakazButtonHeader();
        //Первая страница заказа
        aboutUserForm.fillFullAboutUserForm(userFirstName, userSecondName, userAddress, metroStation, userPhoneNumber);

        //ввести название станции:
//        aboutUserForm.editMetroStationField("Черкизовская");

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        //вторая страница заказа
        aboutOrder.fillFullAboutOrderForm(orderDateDay, orderDateMonth, orderDateYear, orderDayCount, samokatColor, commentToCourier);
        aboutOrder.click_Yes_No_Button(yes_no_button);

        Assert.assertEquals(result, driver.findElements(aboutOrder.getBUTTON_POSMOTRET_STATUS()).size() != 0);
    }

    @After
    public void quitDriver() {
        driver1.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver1.quit();
    }


}
