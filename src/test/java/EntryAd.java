import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import java.time.Duration;

public class EntryAd extends BaseTest{
    @Test
    public void entryAdTest() {
        String url = "https://the-internet.herokuapp.com/entry_ad";
        driver.get(url);

        // Wait for the modal to appear
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement modal = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("modal")));

        // Find and click the Close button
        WebElement closeButton = driver.findElement(By.xpath("//*[@class='modal-footer']/p"));
        closeButton.click();

        // Optional: Print confirmation
        System.out.println("Modal closed successfully");
    }
}
