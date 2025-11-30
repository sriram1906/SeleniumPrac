import org.testng.annotations.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;

public class Slider extends BaseTest {
    @Test
    public void SliderTest() {
        String url = "https://testautomationpractice.blogspot.com/";
        driver.get(url);
        JavascriptExecutor js = (JavascriptExecutor) driver;

        // Wait for the slider to be present
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("slider-range")));

        // Set the slider range to 30 to 100
        js.executeScript("$('#slider-range').slider('option', 'min', 30);");
        js.executeScript("$('#slider-range').slider('option', 'max', 100);");
        js.executeScript("$('#slider-range').slider('values', [30, 100]);");

        // Update the display (optional, to reflect the new range)
        js.executeScript("$('#amount').val('$30 - $100');");

        try {
            Thread.sleep(2000); // Wait to observe the change
            WebElement amountDisplay = driver.findElement(By.id("amount"));
            System.out.println("Slider range set to: " + amountDisplay.getAttribute("value"));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
