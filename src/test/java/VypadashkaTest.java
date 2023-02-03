import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.MainPage;

import java.time.Duration;

@RunWith(Parameterized.class)
public class VypadashkaTest {

    private static final String text1 = "Сутки — 400 рублей. Оплата курьеру — наличными или картой.";
    private static final String text2 = "Пока что у нас так: один заказ — один самокат. Если хотите покататься " +
            "с друзьями, можете просто сделать несколько заказов — один за другим.";
    private final String url = "https://qa-scooter.praktikum-services.ru/";
    private final String textAfterClickVypadasha;
    private final WebDriver driver;
    private final int numberOfVypadashka;

    public VypadashkaTest(WebDriver driver, int numberOfVypadashka, String textAfterClickVypadasha) {
        this.driver = driver;
        this.numberOfVypadashka = numberOfVypadashka;
        this.textAfterClickVypadasha = textAfterClickVypadasha;
    }

    @Parameterized.Parameters
    public static Object[][] getData() {
        return new Object[][]{

                {new ChromeDriver(), 1, text1},
                // {new FirefoxDriver(),2, text2 }
        };
    }

    @Test
    public void checkVypadashkaText() {
        WebDriver driver1 = driver;
        driver1.get(url);
        MainPage mainPage = new MainPage(driver);

        //прокрутка вниз главной страницы
        WebElement element = driver1.findElement(mainPage.getZAKAZ_BUTTON_FOOTER());
        ((JavascriptExecutor) driver1).executeScript("arguments[0].scrollIntoView();", element);

        mainPage.clickCookieButton();
        String s = mainPage.clickVypadashkaButton(numberOfVypadashka);

        Assert.assertEquals(textAfterClickVypadasha, s);

    }

    @After
    public void quitDriver() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        driver.quit();
    }
}
