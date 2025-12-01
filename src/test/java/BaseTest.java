import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest {
    WebDriver driver; // shared driver for each test
    @BeforeMethod
    public void Startdriver() throws InterruptedException {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        Thread.sleep(500); // small pause to allow window initialization
    }
    @AfterMethod
    public void quitdriver()
    {
        driver.quit(); // close browser after each test
    }
}
