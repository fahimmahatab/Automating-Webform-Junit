import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import utils.Utils;

import java.time.Duration;
import java.util.List;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class WebFormJunit {
    WebDriver driver;

    @BeforeAll
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    }

    @DisplayName("Check if Submitted properly")
    @Test
    public void automatedForm() throws InterruptedException {
        driver.get("https://www.digitalunite.com/practice-webform-learners");
        driver.findElement(By.id("onetrust-reject-all-handler")).click();

        driver.findElement(By.id("edit-name")).sendKeys("Md.Fahim Mahatab");
        Thread.sleep(1500);

        driver.findElement(By.id("edit-number")).sendKeys("01712345678");
        Thread.sleep(1500);

        List<WebElement> radiobox = driver.findElements(By.className("ui-checkboxradio-radio-label"));
        radiobox.get(0).click();
        Thread.sleep(1500);
        Utils.scroll(driver, 0, 250);

        WebElement calendar = driver.findElement(By.id("edit-date"));
        calendar.sendKeys(Keys.SPACE);
        calendar.sendKeys(Keys.ENTER);
        Thread.sleep(1500);


        driver.findElement(By.id("edit-email")).sendKeys("fahim@gmail.com");
        Thread.sleep(1500);

        driver.findElement(By.id("edit-tell-us-a-bit-about-yourself-")).sendKeys("I am Fahim Mahatab. From Mohammadpur,Dhaka.");
        Thread.sleep(1500);

        driver.findElement(By.id("edit-uploadocument-upload")).sendKeys(System.getProperty("user.dir") + "./src/test/resources/logo.png");
        Thread.sleep(3000);
        Utils.scroll(driver, 0, 250);


        List<WebElement> checkbox = driver.findElements(By.cssSelector("[type=checkbox]"));
        checkbox.get(0).click();
        Thread.sleep(1500);

        List<WebElement> buttonElement = driver.findElements(By.cssSelector("[type=submit]"));
        buttonElement.get(1).click();
        Thread.sleep(5000);

        String nameActual = driver.findElement(By.id("block-pagetitle-2")).getText();
        Assertions.assertTrue(nameActual.contains("Thank you for your submission!"));
    }
}
